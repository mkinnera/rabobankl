package com.cts.rabobank.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rabobank")

public class CustomerStatementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerStatementController.class);

    @GetMapping("/upload")
    public String customerStatementProcessor(@RequestParam("file") MultipartFile multipartFile){

        if(multipartFile!=null){
            String contentType= multipartFile.getContentType();
            LOGGER.info("contentType{}",contentType);
            //if(contentType.)
        }

      return "testing";
    }
}
