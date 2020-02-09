package com.cts.rabobank.controller;

import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.exceptionhandling.ResourceNotFoundException;
import com.cts.rabobank.factory.FileValidationFactory;
import com.cts.rabobank.model.ValidationRequest;
import com.cts.rabobank.model.Response;
import com.cts.rabobank.service.CustomerStatementProcessorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerStatementControllerTest {
    @InjectMocks
    CustomerStatementController customerStatementController;
    @Mock
    FileValidationFactory fileValidationFactory;
    @Mock
    CustomerStatementProcessorService customerStatementProcessorService;
    @Mock
    MultipartFile multipartFile;
    @LocalServerPort
    private int port;
    @Mock
    private TestRestTemplate restTemplate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processTest() throws IOException, RecordParseException, ResourceNotFoundException {
        restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/v1/rabobank").toString(), String.class);
        File csvFile = new File(this.getClass().getResource("/records.csv").getFile());
        InputStream is = new FileInputStream(csvFile);
        List<ValidationRequest> records = new ArrayList<>();
        ValidationRequest record = new ValidationRequest();
        record.setAccountNumber("NL27SNSB0917829871");
        record.setTransactionRef(112806);
        record.setDescription("Clothes for Willem Dekker");
        record.setStartBalance(91.23);
        record.setMutation(15.57);
        record.setEndBalance(33.5);
        records.add(record);
        MockMultipartFile multipartFile = new MockMultipartFile("csv", "records.csv", "text/csv", is);
        is.close();
        when(customerStatementProcessorService.process(any(), any())).thenReturn(records);
        ResponseEntity<Response> list = customerStatementController.validateFile(multipartFile);
        Assert.assertNotNull(list.getBody());
        Assert.assertNotNull(list.getBody().getData());
        Assert.assertEquals(200, list.getStatusCodeValue());
        Assert.assertEquals(112806, ((List<ValidationRequest>)(list.getBody().getData())).get(0).getTransactionRef());
    }
    @Test(expected = ResourceNotFoundException.class)
    public void processTestWithResourceNotFoundException() throws IOException, RecordParseException, ResourceNotFoundException {
        restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/v1/rabobank").toString(), String.class);
        File csvFile = new File(this.getClass().getResource("/records.csv").getFile());
        InputStream is = new FileInputStream(csvFile);
        MockMultipartFile multipartFile = new MockMultipartFile("csv", "records.csv", "text/csv", is);
        is.close();
        when(customerStatementProcessorService.process(any(), any())).thenThrow(new ResourceNotFoundException("should not be empty"));
        customerStatementController.validateFile(multipartFile);
    }
    @Test(expected = RecordParseException.class)
    public void processTestWithRecordParseException() throws IOException, RecordParseException, ResourceNotFoundException {
        restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/v1/rabobank").toString(), String.class);
        File csvFile = new File(this.getClass().getResource("/htmltestfile.html").getFile());
        InputStream is = new FileInputStream(csvFile);
        MockMultipartFile multipartFile = new MockMultipartFile("html", "htmltestfile.html", "text/html", is);
        is.close();
        customerStatementController.validateFile(multipartFile);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void invalidFileException() throws Exception {
        restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/v1/rabobank").toString(), String.class);
        File csvFile = new File(this.getClass().getResource("/records.txt").getFile());
        InputStream is = new FileInputStream(csvFile);
        MockMultipartFile multipartFile = new MockMultipartFile("txt", "records.txt", "text", is);
        is.close();
        customerStatementController.validateFile(multipartFile);


    }

}