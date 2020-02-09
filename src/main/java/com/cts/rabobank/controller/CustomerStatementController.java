package com.cts.rabobank.controller;

import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.exceptionhandling.ResourceNotFoundException;
import com.cts.rabobank.model.ValidationRequest;
import com.cts.rabobank.model.Response;
import com.cts.rabobank.model.StatusBean;
import com.cts.rabobank.service.CustomerStatementProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/v1")
@Slf4j
public class CustomerStatementController {
    private static final String INVALID_FILE = "Invalid file format exception";
    private static final String EMPTY_FILE = "File should not be empty";
    @Autowired
    CustomerStatementProcessorService customerStatementProcessorService;

    @PostMapping(value = "/rabobank")
    public ResponseEntity<Response> validateFile(@RequestParam("file") MultipartFile file) throws RecordParseException, ResourceNotFoundException {
        Response response = new Response();
        StatusBean status = new StatusBean();
        if (file.isEmpty()) {
            throw new ResourceNotFoundException(EMPTY_FILE);
        }
        try {
            String contentType = file.getContentType();
            log.info("contentType{}::", contentType);
            if (contentType != null && (contentType.equals("text/xml") || contentType.equals("text/csv"))) {
                List<ValidationRequest> recordList = customerStatementProcessorService.process(file, contentType);
                status.setCode(200);
                status.setMessage("Validation successfully done");
                response.setStatus(status);
                if (recordList != null) {
                    response.setData(recordList);
                }
                return Response.responseEntity(response);
            } else {
                throw new RecordParseException(INVALID_FILE);
            }
        } catch (RecordParseException e) {
            throw new RecordParseException(INVALID_FILE);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(EMPTY_FILE);
        }
    }
}
