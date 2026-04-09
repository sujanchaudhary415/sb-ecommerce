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
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValidException (MethodArgumentNotValidException e)
    {
      Map<String,String>response=new HashMap<>();
      e.getBindingResult().getAllErrors().forEach(err->{
          String fieldName=((FieldError)err).getField();
          String message=err.getDefaultMessage();

          response.put("timestamp", String.valueOf(LocalDateTime.now()));
          response.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
          response.put(fieldName,message);
      });
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String,String>> myResourceNotFoundException (ResourceNotFoundException e)
  {
      Map<String,String>response=new HashMap<>();

      // ✅ Use YOUR custom exception fields
      response.put("timestamp", String.valueOf(LocalDateTime.now()));
      response.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
      response.put("message", e.getMessage());
      response.put("resourceName", e.getResourceName());
      response.put("field", e.getField());
      response.put("fieldName", e.getFieldName());

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // ✅ Added return
  }

}
