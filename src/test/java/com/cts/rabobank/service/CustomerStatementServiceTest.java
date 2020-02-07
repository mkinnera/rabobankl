package com.cts.rabobank.service;

import com.cts.rabobank.factory.FileValidationFactory;
import com.cts.rabobank.model.RequestRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
    public void processTest() {
        String contentType = "text/csv";
        String fileName = "test.csv";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName, "text/csv", "test data".getBytes());
        when(fileValidationFactory.processFile(mockMultipartFile, contentType)).thenReturn(null);
        List<RequestRecord> requestRecords = customerStatementProcessorService.process(mockMultipartFile, contentType);
    }
}