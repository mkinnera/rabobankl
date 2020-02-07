package com.cts.rabobank.controller;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cts.rabobank.factory.FileValidationFactory;
import com.cts.rabobank.service.CustomerStatementProcessorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

class TestCustomerStatementProcessorService {

    @InjectMocks
    CustomerStatementProcessorService customerStatementProcessorService;

    @Mock
    FileValidationFactory fileValidationFactory;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
//        MultipartFile multipartFile=null;
//        String contentType = "text/csv"
//        when(fileValidationFactory.processFile(multipartFile,contentType)).thenReturn(null);
//        process
//        //test
//        List<EmployeeVO> empList = manager.getEmployeeList();
//
//        assertEquals(3, empList.size());
//        verify(dao, times(1)).getEmployeeList();
    }
}