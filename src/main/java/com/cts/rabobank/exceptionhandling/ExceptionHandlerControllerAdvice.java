package com.cts.rabobank.exceptionhandling;

import com.cts.rabobank.model.Response;
import com.cts.rabobank.model.StatusBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody
    ResponseEntity<Response> handleResourceNotFound(final ResourceNotFoundException exception,
													final HttpServletRequest request) {
		Response response = new Response();
		StatusBean status = new StatusBean();
		status.setCode(HttpStatus.NOT_FOUND.value());
		status.setMessage(exception.getMessage());
		response.setStatus(status);
		return Response.responseEntity(response);
	}

	@ExceptionHandler(RecordParseException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody
    ResponseEntity<Response> handleRecordParseException(final Exception exception,
                                             final HttpServletRequest request) {

		Response response = new Response();
		StatusBean status = new StatusBean();
		status.setCode(HttpStatus.BAD_REQUEST.value());
		status.setMessage(exception.getMessage());
		response.setStatus(status);
		return Response.responseEntity(response);
	}
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ResponseEntity<Response> handleException(final Exception exception,
																  final HttpServletRequest request) {
		Response response = new Response();
		StatusBean status = new StatusBean();
		status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		status.setMessage(exception.getMessage());
		response.setStatus(status);
		return Response.responseEntity(response);
	}

}
