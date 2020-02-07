package com.cts.rabobank.controller;

import com.cts.rabobank.exception.RecordException;
import com.cts.rabobank.model.ResponseRecord;
import com.cts.rabobank.service.CustomerStatementProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
public class CustomerStatementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerStatementController.class);
    @Autowired
    CustomerStatementProcessorService customerStatementProcessorService;

    @PostMapping(value = "/rabobank")
    public ResponseRecord customerStatementProcessor(@RequestParam("file") MultipartFile multipartFile) throws Exception{

        if (multipartFile != null) {
            String contentType = multipartFile.getContentType();
            LOGGER.info("contentType{}", contentType);
            if (contentType != null && (contentType.equals("text/xml") || contentType.equals("text/csv"))) {
               return customerStatementProcessorService.process(multipartFile, contentType);
            } else {
                throw new RecordException("invalid formate exception");
            }
        }
        return null;
    }

    @GetMapping(value = "/dummy")
    public String customerStatementProcessor() {
        return "testing";
    }
}
