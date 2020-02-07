package com.cts.rabobank.factory;


import com.cts.rabobank.model.RequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class FileValidationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileValidationFactory.class);

  public  List<RequestRecord>  processFile(MultipartFile multipartFile, String contentType ){
        FileValidation fileValidation=null;
        List<RequestRecord> recordList=null;
        if(contentType.equals("text/csv")){
            fileValidation=new CSVFileValidation();
        }else{
            fileValidation=new XMLValidation();
        }
        if(fileValidation!=null) {
            recordList = fileValidation.processFile(multipartFile);
        }
        return recordList;
    }




}
