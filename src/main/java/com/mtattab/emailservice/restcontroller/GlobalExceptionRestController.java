package com.mtattab.emailservice.restcontroller;

import com.mtattab.emailservice.model.ResponseRestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionRestController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseRestModel> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        // Construct a concise error message
        String errorMessage = bindingResult.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed.");

        // Create an ErrorResponse object
        ResponseRestModel errorResponse = new ResponseRestModel();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatusMessage(errorMessage);

        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseRestModel> exceptionHandler(Exception exception){
        exception.printStackTrace();
        ResponseRestModel response = new ResponseRestModel(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
