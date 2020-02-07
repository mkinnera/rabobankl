package com.cts.rabobank.factory;


import com.cts.rabobank.exception.RecordParseException;
import com.cts.rabobank.model.RequestRecord;
import com.cts.rabobank.model.RequestRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.List;

@Component
@Slf4j
public class XMLValidation implements FileValidation {
    public List<RequestRecord> processFile(MultipartFile multipartFile) throws RecordParseException {
        List<RequestRecord> recordList=null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(RequestRecords.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            RequestRecords records = (RequestRecords) unmarshaller.unmarshal(multipartFile.getInputStream());
            recordList=records.getRecords();
            log.debug("XMLValidation ::recordList::{}",recordList);

        }catch (Exception e){
            log.error("XMLValidation::{}",e.getMessage());
            e.printStackTrace();
            throw new RecordParseException(e.getMessage());

        }
        return recordList;
    }
}

