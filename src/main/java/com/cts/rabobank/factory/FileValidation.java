package com.cts.rabobank.factory;


import com.cts.rabobank.exception.RecordParseException;
import com.cts.rabobank.model.RequestRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileValidation {
   public List<RequestRecord> processFile(MultipartFile multipartFile) throws Exception;
}
