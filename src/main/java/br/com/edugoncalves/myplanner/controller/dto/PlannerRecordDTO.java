package br.com.edugoncalves.myplanner.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PlannerRecordDTO(
        Long id,

        @NotBlank(message = "The service field cannot be null.")
        @Size(min=3,max=20,message = "The service field must contain a minimum of 3 and a maximum of 20 characters.")
        String service,
        @NotBlank(message = "The customer field cannot be null.")
        @Size(min=3,max=20,message = "The customer field must contain a minimum of 3 and a maximum of 20 characters.")
        String customer,
        @NotBlank(message = "The location field cannot be null.")
        @Size(min=3,max=20,message = "The location field must contain a minimum of 3 and a maximum of 20 characters.")
        String location,
        @NotNull(message = "The datetime field cannot be null.")
        LocalDateTime dateTime,
        boolean done,
        boolean canceled) {
}
