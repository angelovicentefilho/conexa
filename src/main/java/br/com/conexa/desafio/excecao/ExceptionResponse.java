package br.com.conexa.desafio.excecao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private List<ErrorMessageInfo> errors; 
    
    private Map<String, Object> dataReturn;
    
    public ExceptionResponse() {
        errors = new ArrayList<>();
        dataReturn = new HashMap<>();
    }
    
    public static ExceptionResponse instance() {
        return new ExceptionResponse();
    }
    
    public ExceptionResponse(String message) {
        this.errors = new ArrayList<>();
        this.dataReturn = new HashMap<>();
        addErrorMessage(message);
    }
    
    public ExceptionResponse(String message, Map<String, Object> dataReturn) {
        this.errors = new ArrayList<>();
        this.dataReturn = dataReturn;
        addErrorMessage(message);
    }
    
    public ExceptionResponse(Map<String, Object> dataReturn) {
        this.errors = new ArrayList<>();
        this.dataReturn = dataReturn;
    }
    
    public void addErrorMessage(String message) {
        ErrorMessageInfo em = new ErrorMessageInfo(message);
        errors.add(em);
    }
    
    @Getter
    class ErrorMessageInfo{
        
        private String message;
        
        public ErrorMessageInfo(String message) {
            this.message = message;
        }
    }
        
}
