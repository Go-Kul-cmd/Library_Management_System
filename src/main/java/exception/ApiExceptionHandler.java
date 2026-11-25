package com.example.library_management_system.exception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
    @ExceptionHandler(java.util.NoSuchElementException.class)
    public ResponseEntity<Map<String,String>> notFound(Exception e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException e){
        String msg=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Invalid input");
        return ResponseEntity.badRequest().body(Map.of("error",msg));
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> conflict(Exception e){return ResponseEntity.status(409).body(Map.of("error","Duplicate value or record is referenced by borrowing history."));}
}