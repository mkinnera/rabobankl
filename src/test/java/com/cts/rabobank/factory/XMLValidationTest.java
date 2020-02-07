package com.cts.rabobank.factory;

import com.cts.rabobank.exception.RecordParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class XMLValidationTest {
    @InjectMocks
    XMLValidation xmlValidation;
    @Mock
    MultipartFile multipartFile;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processTest() throws RecordParseException {
        String contentType = "text/csv";
        xmlValidation.processFile(multipartFile);

    }
}