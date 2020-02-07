package com.cts.rabobank.util;

import com.cts.rabobank.model.RequestRecord;
import com.cts.rabobank.model.ResponseRecord;

import ch.qos.logback.classic.Logger;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RecordValidationHeper {
    @Autowired
    RequestRecord reqRecord;
    
	public boolean validatEndBalance(RequestRecord reqRecord){
		boolean status=false;
		long i = (long) (reqRecord.getStartBalance()+reqRecord.getMutation());
		if(i == reqRecord.getEndBalance() ){
			status = true;
		}
		 return status;
		
	}
	
	public boolean ValidateReference(ArrayList list){
		
		 return list.size() == list.stream().distinct().count();
		
	}
}
