package com.cts.rabobank.factory;


import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.model.ValidationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileValidation {
   List<ValidationRequest> processFile(MultipartFile multipartFile) throws RecordParseException;
}
