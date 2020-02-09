package com.cts.rabobank.factory;

import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.exceptionhandling.ResourceNotFoundException;
import com.cts.rabobank.model.ValidationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Component
@Slf4j
public class FileValidationFactory {

    public List<ValidationRequest> validateFile(MultipartFile multipartFile, String contentType) throws RecordParseException {
        FileValidation fileValidation;
        List<ValidationRequest> recordList = null;
        try {
            if (contentType.equals("text/csv")) {
                fileValidation = new CSVFileValidation();
            } else {
                fileValidation = new XMLValidation();
            }
            recordList = fileValidation.processFile(multipartFile);
            if (recordList != null && !recordList.isEmpty()) {
                return recordList;
            } else {
                throw new ResourceNotFoundException("File should have records");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RecordParseException(e.getMessage());
        }
    }




}
