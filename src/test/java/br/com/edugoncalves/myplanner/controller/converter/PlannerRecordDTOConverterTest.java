package br.com.edugoncalves.myplanner.controller.converter;

import br.com.edugoncalves.myplanner.controller.dto.PlannerRecordDTO;
import br.com.edugoncalves.myplanner.model.PlannerRecord;
import br.com.edugoncalves.myplanner.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlannerRecordDTOConverterTest {

    @InjectMocks
    private PlannerRecordDTOConverter converter;
    @Test
    void converter_mustReturnPlannerRecordDTO_whenConvertSuccefully(){
        PlannerRecord planner= TestUtils.buildValidPlannerRecord();
        PlannerRecordDTO dto= converter.convert(planner);

        assertEquals(dto.id(),planner.getId());
        assertEquals(dto.service(),planner.getService());
        assertEquals(dto.customer(),planner.getCustomer());
        assertEquals(dto.location(),planner.getLocation());
        assertEquals(dto.dateTime(),planner.getDateTime());
        assertEquals(dto.canceled(),planner.isCanceled());
         assertEquals(dto.done(),planner.isDone());
    }

    @Test
    void converter_mustReturnPlannerRecord_whenConvertSuccefully(){
        PlannerRecordDTO dto= TestUtils.buildValidPlannerRecordDTO();

        PlannerRecord planner=converter.convert(dto);
        assertEquals(planner.getId(),dto.id());
        assertEquals(planner.getService(),dto.service());
        assertEquals(planner.getCustomer(),dto.customer());
        assertEquals(planner.getLocation(),dto.location());
        assertEquals(planner.getDateTime(),dto.dateTime());
        assertEquals(planner.isCanceled(),dto.canceled());
        assertEquals(planner.isDone(),dto.done());
    }
}
