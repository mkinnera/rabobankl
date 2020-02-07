package com.cts.rabobank.factory;


import com.cts.rabobank.model.RequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class CSVFileValidation implements FileValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileValidation.class);

    public List<RequestRecord> processFile(MultipartFile multipartFile){
        LOGGER.debug("Processing CSVFileValidation Inside");
        List<RequestRecord> recordList=null;


        return recordList;
    }
}


