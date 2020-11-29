package com.code.pagamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	static final long serialVersionUID = 3100949472978578614L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}

}
