package com.cts.rabobank.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationRecords {

    @XmlElement(name = "record")
    private List<ValidationRequest> records = null;

    public List<ValidationRequest> getRecords() {

        for (ValidationRequest record:records) {
            record.checkBalanceValidation();
        }        return records;
    }

}
