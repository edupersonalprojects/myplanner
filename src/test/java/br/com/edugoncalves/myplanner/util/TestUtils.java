package br.com.edugoncalves.myplanner.util;

import br.com.edugoncalves.myplanner.controller.dto.PlannerRecordDTO;
import br.com.edugoncalves.myplanner.model.PlannerRecord;

import java.time.LocalDateTime;

public class TestUtils {

    public static PlannerRecord buildValidPlannerRecord(){
        return PlannerRecord.builder()
                .service("Test")
                .customer("Customer")
                .location("Test Street")
                .dateTime(LocalDateTime.now())
                .canceled(false)
                .done(false)
                .build();
    }
    public static PlannerRecordDTO buildValidPlannerRecordDTO(){
        return new PlannerRecordDTO(null,"Test","Customer","Test Street",
                LocalDateTime.now(),false,false);
    }
}
