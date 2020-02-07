package com.cts.rabobank.controller;

import static org.mockito.Mockito.when;

import com.cts.rabobank.factory.FileValidationFactory;
import com.cts.rabobank.model.RequestRecord;
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
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerStatementControllerTest {
    @InjectMocks
    CustomerStatementController customerStatementController;
    @Mock
    FileValidationFactory fileValidationFactory;
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
    public void processTest() throws MalformedURLException {
        restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/v1/rabobank").toString(), String.class);
        try {
            File csvFile = new File(this.getClass().getResource("/records.csv").getFile());
            InputStream is = new FileInputStream(csvFile);
            MockMultipartFile multipartFile = new MockMultipartFile("csv", "records.csv", "text/csv", is);
            is.close();

            List<RequestRecord> list=customerStatementController.customerStatementProcessor(multipartFile);


        }catch (Exception e) {

        }
    }
}