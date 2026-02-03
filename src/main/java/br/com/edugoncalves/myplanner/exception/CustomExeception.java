package br.com.edugoncalves.myplanner.exception;

import br.com.edugoncalves.myplanner.model.ErrorResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CustomExeception {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseMessage> handleValidationException(MethodArgumentNotValidException ex){
        FieldError fieldError= Objects.requireNonNull(ex.getBindingResult().getFieldError());
        ErrorResponseMessage errorResponseMessage=ErrorResponseMessage.invalidArgumentsError(fieldError);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseMessage> handleRuntimeExeception(RuntimeException ex){
        ErrorResponseMessage errorResponseMessage=ErrorResponseMessage.internalError(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseMessage);

    }


}
