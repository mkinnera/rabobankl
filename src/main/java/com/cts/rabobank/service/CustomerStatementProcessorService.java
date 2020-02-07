package com.cts.rabobank.service;

import com.cts.rabobank.factory.FileValidationFactory;
import com.cts.rabobank.model.RequestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CustomerStatementProcessorService {
    @Autowired
    FileValidationFactory fileValidationFactory;
    public List<RequestRecord> process(MultipartFile multipartFile, String contentType){
        return fileValidationFactory.processFile(multipartFile,contentType);
    }
}
