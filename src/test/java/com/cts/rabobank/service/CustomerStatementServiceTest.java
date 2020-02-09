package com.cts.rabobank.service;

import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.exceptionhandling.ResourceNotFoundException;
import com.cts.rabobank.factory.FileValidationFactory;
import com.cts.rabobank.model.ValidationRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerStatementServiceTest {
    @InjectMocks
    CustomerStatementProcessorService customerStatementProcessorService;
    @Mock
    FileValidationFactory fileValidationFactory;
    @Mock
    MultipartFile multipartFile;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processTest() throws RecordParseException, ResourceNotFoundException {
        String contentType = "text/csv";
        String fileName = "test.csv";
        List<ValidationRequest> records = new ArrayList<>();
        ValidationRequest record = new ValidationRequest();
        record.setAccountNumber("NL27SNSB0917829871");
        record.setTransactionRef(112806);
        record.setDescription("Clothes for Willem Dekker");
        record.setStartBalance(91.23);
        record.setMutation(15.57);
        record.setEndBalance(33.5);
        records.add(record);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName, "text/csv", "test data".getBytes());
        when(fileValidationFactory.validateFile(mockMultipartFile, contentType)).thenReturn(records);
        List<ValidationRequest> response = customerStatementProcessorService.process(mockMultipartFile, contentType);
        Assert.assertNotNull(response);
        Assert.assertEquals(112806, response.get(0).getTransactionRef());
    }
}