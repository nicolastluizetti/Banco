package exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.conta.poupanca.Exception.EntidadeNaoEncontradaExcption;
import com.conta.poupanca.Exception.NegocioException;

@ControllerAdvice
public class ExceptionHandler {
	
	
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaExcption e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}
	

	public ResponseEntity<?> tratarNegocioException(NegocioException e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problema);
	}
	
	
	public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem("O tipo de mídia não é aceito.").build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(problema);

}}
