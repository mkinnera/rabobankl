package com.cts.rabobank.factory;

import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.model.ValidationRequest;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CSVFileValidation implements FileValidation {

    public List<ValidationRequest> processFile(MultipartFile multipartFile) throws RecordParseException {
        log.debug("Processing CSVFileValidation Inside");
        List<ValidationRequest> recordList = null;
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(multipartFile.getInputStream()));
            HeaderColumnNameTranslateMappingStrategy<ValidationRequest> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<ValidationRequest>();
            beanStrategy.setType(ValidationRequest.class);

            Map<String, String> columnMapping = new HashMap<>();
            columnMapping.put("Reference", "transactionRef");
            columnMapping.put("Account Number", "accountNumber");
            columnMapping.put("Description", "description");
            columnMapping.put("Start Balance", "startBalance");
            columnMapping.put("Mutation", "mutation");
            columnMapping.put("End Balance", "endBalance");
            beanStrategy.setColumnMapping(columnMapping);
            CsvToBean<ValidationRequest> csvToBean = new CsvToBean<ValidationRequest>();
            csvToBean.setMappingStrategy(beanStrategy);
            csvToBean.setCsvReader(reader);
            List<ValidationRequest> records = csvToBean.parse();
            recordList = new ArrayList<>();
            for (ValidationRequest validationRequest : records) {
                validationRequest.checkBalanceValidation();
                recordList.add(validationRequest);
            }

        } catch (Exception e) {
            throw new RecordParseException(e.getMessage());
        }
        return recordList;
    }
}


