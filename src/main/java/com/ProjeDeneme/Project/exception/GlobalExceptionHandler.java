package com.ProjeDeneme.Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler, uygulamadaki tüm istisnaları merkezi bir yerde ele almak için kullanılan bir sınıftır.
 * Bu sınıf, doğrulama hatalarını ele alır ve uygun HTTP yanıtları döndürür.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * MethodArgumentNotValidException türündeki istisnaları yakalar.
     *
     * @param ex Yakalanan MethodArgumentNotValidException(geçersiz) istisnası
     * @return Hatalı alanlar ve hata mesajları içeren bir yanıt
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
