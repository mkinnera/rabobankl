package com.cts.rabobank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
public class Response {
    private StatusBean status;
    private Object data;

    public static ResponseEntity<Response> responseEntity(Response response) {
        if (response != null && response.getStatus()!=null) {
            if (response.getStatus().getCode() == 200) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

    }
}