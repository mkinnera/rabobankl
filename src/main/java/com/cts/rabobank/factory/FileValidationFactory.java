package com.cts.rabobank.factory;


import com.cts.rabobank.exception.RecordParseException;
import com.cts.rabobank.model.RequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileValidationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileValidationFactory.class);

  public  List<RequestRecord>  processFile(MultipartFile multipartFile, String contentType ){
        FileValidation fileValidation=null;
        List<RequestRecord> recordList=null;
        try {
            if (contentType.equals("text/csv")) {
                fileValidation = new CSVFileValidation();
            } else {
                fileValidation = new XMLValidation();
            }
            if (fileValidation != null) {
                recordList = fileValidation.processFile(multipartFile);
                if (recordList != null && recordList.size() > 0) {
                    return generateReport(recordList);
                } else {
                    throw new RecordParseException(" Invalid data in the file");
                }
            }
        }catch(Exception e){
           e.printStackTrace();
        }
        return recordList;
    }


    private static List<RequestRecord> generateReport(List<RequestRecord> faildeRecordList){
        Set deptSet = new HashSet();
        faildeRecordList.removeIf(record -> !deptSet.add(record.getTransactionRef()));
        return faildeRecordList.stream().filter(record-> !record.isValid()).collect(Collectors.toList());
    }

}
