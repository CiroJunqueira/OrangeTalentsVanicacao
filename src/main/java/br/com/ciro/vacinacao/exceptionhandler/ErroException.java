package br.com.ciro.vacinacao.exceptionhandler;

import org.springframework.http.HttpStatus;


public class ErroException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public ErroException(String message) {
		super(message);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(cause);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}