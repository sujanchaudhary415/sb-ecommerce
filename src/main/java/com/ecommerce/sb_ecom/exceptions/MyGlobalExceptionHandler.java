package com.ecommerce.sb_ecom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> myMethodArgumentNotValidException (MethodArgumentNotValidException e)
    {
          Map<String,Object>response=new HashMap<>();
          response.put("timestamp", String.valueOf(LocalDateTime.now()));
          response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));

        Map<String,String>errors=new HashMap<>();
         e.getBindingResult().getAllErrors().forEach(err->{
          String fieldName=((FieldError)err).getField();
          String message=err.getDefaultMessage();

          errors.put(fieldName,message);
      });
        response.put("errors", errors); // nest all field errors under "errors"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String,String>> myResourceNotFoundException (ResourceNotFoundException e)
  {
      Map<String,String>response=new HashMap<>();


      response.put("timestamp", String.valueOf(LocalDateTime.now()));
      response.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
      response.put("message", e.getMessage());
      response.put("resourceName", e.getResourceName());
      response.put("fieldName", e.getFieldName());

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

    // APIException (like duplicates or custom bad request)
    @ExceptionHandler(APIException.class)
    public ResponseEntity<Map<String, Object>> handleAPIException(APIException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
