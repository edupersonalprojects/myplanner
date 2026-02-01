package br.com.edugoncalves.myplanner.controller.dto;

import java.time.LocalDateTime;

public record PlannerRecordDTO(
        Long id,
        String service,
        String costumer,
        String location,
        LocalDateTime dateTime,
        boolean done,
        boolean canceled) {
}
