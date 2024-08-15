package com.one.care.config;

import com.one.care.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (like @NotBlank, @Size)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder ers = new StringBuilder();
        ResponseMessage responseMessage = new ResponseMessage();
        ers.append(ex.getBindingResult().getFieldError().getDefaultMessage());
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            if(!ers.isEmpty())
//                ers.append(", ");
//            ers.append(error.getDefaultMessage());
//        });
        responseMessage.setResponseMessage(ers.toString());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle other specific exceptions
//    @ExceptionHandler(SomeSpecificException.class)
//    public ResponseEntity<String> handleSpecificException(SomeSpecificException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGlobalException(Exception ex) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage(ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

