package com.cts.rabobank.factory;

import com.cts.rabobank.exception.RecordParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
    public void processTestForxml() throws Exception{
        String contentType = "text/xml";
        File csvFile = new File(this.getClass().getResource("/records.xml").getFile());
        InputStream is = new FileInputStream(csvFile);
        MockMultipartFile multipartFile = new MockMultipartFile("xml", "records.xml", "text/xml", is);
        is.close();

        fileValidationFactory.processFile(multipartFile, contentType);
    }
    @Test
    public void processTestForcsv() throws RecordParseException {
        String contentType = "text/csv";
        try {
            fileValidationFactory.processFile(multipartFile, contentType);
        }catch (Exception e) {

        }
    }
}