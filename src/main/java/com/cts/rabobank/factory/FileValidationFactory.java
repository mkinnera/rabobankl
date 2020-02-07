package com.cts.rabobank.factory;


import com.cts.rabobank.exception.RecordParseException;
import com.cts.rabobank.model.RequestRecord;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FileValidationFactory {

  public  List<RequestRecord>  processFile(MultipartFile multipartFile, String contentType ) throws RecordParseException {
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
                if (recordList != null && !recordList.isEmpty()) {
                    return generateReport(recordList);
                } else {
                    throw new RecordParseException(" Invalid data in the file");
                }
            }
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RecordParseException(e.getMessage());
        }
        return recordList;
    }


    private static List<RequestRecord> generateReport(List<RequestRecord> faildeRecordList){
        Set deptSet = new HashSet();
        faildeRecordList.removeIf(record -> !deptSet.add(record.getTransactionRef()));
        return faildeRecordList.stream().filter(record-> !record.isValid()).collect(Collectors.toList());
    }

}
