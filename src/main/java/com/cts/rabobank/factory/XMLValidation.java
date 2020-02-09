package com.cts.rabobank.factory;


import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.model.ValidationRequest;
import com.cts.rabobank.model.ValidationRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.List;

@Component
@Slf4j
public class XMLValidation implements FileValidation {
    public List<ValidationRequest> processFile(MultipartFile multipartFile) throws RecordParseException {
        List<ValidationRequest> recordList;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ValidationRecords.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ValidationRecords records = (ValidationRecords) unmarshaller.unmarshal(multipartFile.getInputStream());
            recordList=records.getRecords();
            log.debug("XMLValidation ::recordList::{}",recordList);
        }catch (Exception e){
            log.error("XMLValidation::{}",e.getMessage());
            throw new RecordParseException(e.getMessage());

        }
        return recordList;
    }
}

