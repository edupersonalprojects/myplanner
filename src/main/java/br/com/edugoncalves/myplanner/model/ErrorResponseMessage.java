package br.com.edugoncalves.myplanner.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseMessage {
    private int status;
    private String message;

    public static ErrorResponseMessage invalidArgumentsError(FieldError fieldError) {
      return   ErrorResponseMessage.builder()
              .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .message(fieldError.getDefaultMessage()).build();
    }

    public static ErrorResponseMessage internalError(RuntimeException ex) {
        return   ErrorResponseMessage.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage()).build();
    }
}
