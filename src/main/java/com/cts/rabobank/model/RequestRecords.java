package com.cts.rabobank.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestRecords {

    @XmlElement(name = "record")
    private List<RequestRecord> records = null;

    public List<RequestRecord> getRecords() {
        return records;
    }

    public void setRecords(List<RequestRecord> records) {
        this.records = records;
    }
}
