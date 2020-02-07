package com.cts.rabobank.factory;


import com.cts.rabobank.model.RequestRecord;
import com.cts.rabobank.model.RequestRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.List;

@Component
public class XMLValidation implements FileValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileValidation.class);

    public List<RequestRecord> processFile(MultipartFile multipartFile){
        List<RequestRecord> recordList=null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(RequestRecords.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            RequestRecords records = (RequestRecords) unmarshaller.unmarshal(multipartFile.getInputStream());
            recordList=records.getRecords();
            LOGGER.debug("XMLValidation ::recordList::{}",recordList);

        }catch (Exception e){
            LOGGER.error("XMLValidation::{}",e.getMessage());
            e.printStackTrace();

        }
        return recordList;
    }
}

