package com.cts.rabobank.service;

import com.cts.rabobank.model.ResponseRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerStatementProcessorService {
    public ResponseRecord process(MultipartFile multipartFile, String contentType) {
        ResponseRecord responseRecord = new ResponseRecord();
        responseRecord.setDescription("need to write logic");
        return responseRecord;
    }
}
