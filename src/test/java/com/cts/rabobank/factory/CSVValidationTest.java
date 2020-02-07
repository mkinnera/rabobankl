package com.cts.rabobank.factory;

import com.cts.rabobank.exception.RecordParseException;
import com.cts.rabobank.model.RequestRecord;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CSVValidationTest {
    @InjectMocks
    CSVFileValidation csvFileValidation;
    @Mock
    MultipartFile multipartFile;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processTest() throws Exception {
        String contentType = "text/csv";
        File csvFile = new File(this.getClass().getResource("/records.csv").getFile());
        InputStream is = new FileInputStream(csvFile);
        MockMultipartFile multipartFile = new MockMultipartFile("csv", "records.csv", "text/csv", is);
        is.close();

        List<RequestRecord> list=csvFileValidation.processFile(multipartFile);
        Assert.assertEquals(10, list.size());
    }
}