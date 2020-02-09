package com.cts.rabobank.service;

import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.exceptionhandling.ResourceNotFoundException;
import com.cts.rabobank.factory.FileValidationFactory;
import com.cts.rabobank.model.ValidationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerStatementProcessorService {
    @Autowired
    FileValidationFactory fileValidationFactory;

    public List<ValidationRequest> process(MultipartFile multipartFile, String contentType) throws RecordParseException, ResourceNotFoundException {
        List<ValidationRequest> recordList = fileValidationFactory.validateFile(multipartFile, contentType);
        return generateFailedReport(recordList);
    }

    private static List<ValidationRequest> generateFailedReport(List<ValidationRequest> failedRecordList) {
        Set<Integer> deptSet = new HashSet<>();
        failedRecordList.removeIf(record -> !deptSet.add(record.getTransactionRef()));
        return failedRecordList.stream().filter(record -> !record.isValid()).collect(Collectors.toList());
    }
}
