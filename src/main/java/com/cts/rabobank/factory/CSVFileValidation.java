package com.cts.rabobank.factory;


import com.cts.rabobank.model.RequestRecord;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CSVFileValidation implements FileValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileValidation.class);

    public List<RequestRecord> processFile(MultipartFile multipartFile){
        LOGGER.debug("Processing CSVFileValidation Inside");
        List<RequestRecord> recordList=null;
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(multipartFile.getInputStream()), ',');
            ColumnPositionMappingStrategy<RequestRecord> beanStrategy = new ColumnPositionMappingStrategy<RequestRecord>();
            beanStrategy.setType(RequestRecord.class);
            beanStrategy.setColumnMapping(new String[]{"Reference", "Account Number", "Description", "Start Balance", "Mutation", "End Balance"});

            CsvToBean<RequestRecord> csvToBean = new CsvToBean<RequestRecord>();

            recordList = csvToBean.parse(beanStrategy, reader);

            System.out.println(recordList);
        }catch(Exception e){

        }
        return recordList;
    }
}


