package com.cts.rabobank.controller;

import com.cts.rabobank.exception.RecordException;
import com.cts.rabobank.model.RequestRecord;
import com.cts.rabobank.model.ResponseRecord;
import com.cts.rabobank.service.CustomerStatementProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class CustomerStatementController {
    @Autowired
    CustomerStatementProcessorService customerStatementProcessorService;

    @PostMapping(value = "/rabobank")
    public List<RequestRecord> customerStatementProcessor(@RequestParam("file") MultipartFile multipartFile) throws Exception{

        if (multipartFile != null) {
            String contentType = multipartFile.getContentType();
            log.info("contentType{}", contentType);
            if (contentType != null && (contentType.equals("text/xml") || contentType.equals("text/csv"))) {
               return customerStatementProcessorService.process(multipartFile, contentType);
            } else {
                throw new RecordException("Invalid file formate exception");
            }
        }
        return null;
    }
    @GetMapping(value = "/test")
    public String customerStatementProcessor() {
        return "testing";
    }
}
