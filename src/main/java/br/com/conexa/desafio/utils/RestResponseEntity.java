package br.com.conexa.desafio.utils;

import org.springframework.http.ResponseEntity;

import br.com.conexa.desafio.excecao.ExceptionHandler;
import br.com.conexa.desafio.excecao.ExceptionResponse;
import br.com.conexa.desafio.excecao.InternalException;
import br.com.conexa.desafio.excecao.RestException;

public class RestResponseEntity {

    private RestResponseEntity() {
    }


    public static <T extends ProtocolResponse> ResponseEntity<T> success(T t) {
        return ResponseEntity.ok().body(t);
    }
        

    public static ResponseEntity<ExceptionResponse> exceptionFail(Integer code, Throwable e) {
        try {
            return ResponseEntity.status(code).body(ExceptionHandler.handleException((RestException) e));
        }catch (Exception exception) {
            return ResponseEntity.status(code).body(ExceptionHandler.handleException(new InternalException("Ocorreu um erro, contate o administrador!")));
        }
    }
    

    public static <T extends ProtocolResponse> ResponseEntity<T> noContent() {
        return ResponseEntity.noContent().build();
    }
        

    public static <T extends ProtocolResponse> ResponseEntity<T> accepted(T t) {
        return ResponseEntity.accepted().body(t);
    }
    

    public static ResponseEntity<ExceptionResponse> fail(Integer code, ExceptionResponse exceptionResponse) {
        return ResponseEntity.status(code).body(exceptionResponse);
    }
    
   
    public static ResponseEntity<ExceptionResponse> fail(Integer code, String errorMessage) {
        ExceptionResponse er = new ExceptionResponse(errorMessage);
        return ResponseEntity.status(code).body(er);
    }    
}
