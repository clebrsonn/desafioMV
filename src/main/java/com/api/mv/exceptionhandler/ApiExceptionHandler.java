package com.api.mv.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Ser executado em toda aplicação spring
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        String messageUser = messageSource.getMessage("mensagem.invalida", null,
                LocaleContextHolder.getLocale());
        String messageDev = ex.getCause().toString();
        return handleExceptionInternal(ex, new Erro(messageDev, messageUser), headers, status, request);
    }

    public class Erro {
        private String messageDeveloper;
        private String messageUser;


        Erro(String messageDeveloper, String messageUser) {
            this.messageDeveloper = messageDeveloper;
            this.messageUser = messageUser;
        }

        public String getMessageDeveloper() {
            return messageDeveloper;
        }

        public String getMessageUser() {
            return messageUser;
        }
    }
}
