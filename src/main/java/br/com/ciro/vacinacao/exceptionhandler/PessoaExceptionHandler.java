package br.com.ciro.vacinacao.exceptionhandler;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PessoaExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ErroException.class)
	public final ResponseEntity<Erro> negocioException(ErroException ex, WebRequest request) {
		String mensagemUsuario = (ex.getMessage());
		String mensagemDesenvolvedor = ex.toString();
		Erro erro = new Erro(mensagemUsuario, mensagemDesenvolvedor);
		return new ResponseEntity<Erro>(erro, ex.getHttpStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> erros = (ex.getAllErrors());
		StringBuilder sb = new StringBuilder();

		erros.forEach(e -> {
			FieldError error = (FieldError) e;
			sb.append("Campo: " + error.getField() + " - " + error.getDefaultMessage() + " - ");

		});
		String mensagemUsuario = sb.toString();
		String mensagemDesenvolvedor = ex.toString();
		Erro erro = new Erro(mensagemUsuario, mensagemDesenvolvedor);
		return handleExceptionInternal(ex, erro, headers, HttpStatus.BAD_REQUEST, request);
	}

	public static class Erro {

		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}
}
