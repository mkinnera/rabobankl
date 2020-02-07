package com.cts.rabobank.factory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class FileValidationFactoryTest {
    @InjectMocks
    FileValidationFactory fileValidationFactory;
    @Mock
    MultipartFile multipartFile;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processTestForxml() {
        String contentType = "text/xml";
        fileValidationFactory.processFile(multipartFile, contentType);
    }
    @Test
    public void processTestForcsv() {
        String contentType = "text/csv";
        fileValidationFactory.processFile(multipartFile, contentType);
    }
}