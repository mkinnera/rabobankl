package com.cts.rabobank.factory;


import com.cts.rabobank.model.RequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileValidationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileValidationFactory.class);

  public static List<RequestRecord>  processFile(MultipartFile multipartFile, String contentType ){
        FileValidation fileValidation=null;
        List<RequestRecord> recordList=null;
        return recordList;
    }




}
