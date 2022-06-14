package br.com.santanderbr.ib.contacorrente.api.controller.handler;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.santanderbr.ib.contacorrente.api.service.exception.NumeroContaInexistenteException;
import br.com.santanderbr.ib.contacorrente.api.service.exception.NumeroContaJaCadastradoException;
import br.com.santanderbr.ib.contacorrente.api.service.exception.ValorDepositoInvalidoException;
import br.com.santanderbr.ib.contacorrente.api.service.exception.ValorInsuficienteException;


@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		
		return ResponseEntity.badRequest().body("Ocorreu um erro no sistema: "+e.getRootCause().getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
		
		return ResponseEntity.badRequest().body(iterator.next().getMessageTemplate());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ObjectError objectError = e.getAllErrors().get(0);
		return ResponseEntity.badRequest().body(objectError.getDefaultMessage());
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(NumeroContaJaCadastradoException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(NumeroContaJaCadastradoException e) {
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(NumeroContaInexistenteException.class)
	public ResponseEntity<String> handleNumeroContaInexistenteException(NumeroContaInexistenteException e) {
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValorInsuficienteException.class)
	public ResponseEntity<String> handleValorInsuficienteException(ValorInsuficienteException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValorDepositoInvalidoException.class)
	public ResponseEntity<String> handleValorDepositoInvalidoException(ValorDepositoInvalidoException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
