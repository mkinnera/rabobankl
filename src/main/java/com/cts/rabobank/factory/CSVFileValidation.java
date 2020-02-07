package com.cts.rabobank.factory;

import com.cts.rabobank.exception.RecordParseException;
import com.cts.rabobank.model.RequestRecord;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CSVFileValidation implements FileValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileValidation.class);

    public List<RequestRecord> processFile(MultipartFile multipartFile) throws RecordParseException {
        LOGGER.debug("Processing CSVFileValidation Inside");
        List<RequestRecord> recordList=null;
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(multipartFile.getInputStream()));
            HeaderColumnNameTranslateMappingStrategy<RequestRecord> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<RequestRecord>();
             beanStrategy.setType(RequestRecord.class);

            Map<String, String> columnMapping = new HashMap<>();
            columnMapping.put("Reference", "transactionRef");
            columnMapping.put("Account Number", "accountNumber");
            columnMapping.put("Description", "description");
            columnMapping.put("Start Balance", "startBalance");
            columnMapping.put("Mutation", "mutation");
            columnMapping.put("End Balance", "endBalance");


            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<RequestRecord> csvToBean = new CsvToBean<RequestRecord>();

            csvToBean.setMappingStrategy(beanStrategy);
            csvToBean.setCsvReader(reader);
            List<RequestRecord> records = csvToBean.parse();
            recordList=new ArrayList<>();
            for(RequestRecord requestRecord:records){
                requestRecord.checkBalanceValidation();
                recordList.add(requestRecord);

            }

        }catch(Exception e){

            throw new RecordParseException(e.getMessage());
        }
        return recordList;
    }
}


