package br.com.edugoncalves.myplanner.service;

import br.com.edugoncalves.myplanner.exception.GenericExeception;
import br.com.edugoncalves.myplanner.model.PlannerRecord;
import br.com.edugoncalves.myplanner.repository.PlannerRecordCustomRepository;
import br.com.edugoncalves.myplanner.repository.PlannerRecordRepository;
import br.com.edugoncalves.myplanner.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlannerRecordServiceTest {

    @Mock
    private PlannerRecordRepository repository;
    @Mock
    private PlannerRecordCustomRepository customRepository;

    @InjectMocks
    private PlannerRecordService service;


    @Test
    void service_mustReturnPlannerRecord_whenSaveSuccessfully(){
        PlannerRecord planner= TestUtils.buildValidPlannerRecord();

        when(repository.save(any())).thenReturn(planner);
        PlannerRecord plannerResult=service.save(planner);

        verify(repository,times(1)).save(planner);

        assertNotNull(plannerResult);



    }
    @Test
    void service_mustThrowException_whenErrorOccursOnSave(){
        PlannerRecord plannerRecord=TestUtils.buildValidPlannerRecord();
        when(repository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(GenericExeception.class,()-> service.save(plannerRecord));
    }
    @Test
    void service_mustReturnPage_whenFindSuccessfully(){
        PlannerRecord planner= TestUtils.buildValidPlannerRecord();
        when(customRepository.findPaginated(any(),any(),any(),any(),any(),
                any(),any(),anyInt(),anyInt())).thenReturn(Page.empty());
        Page<PlannerRecord> page=service.findPaginated(planner.getService(),planner.getCustomer(),planner.getLocation(),
                planner.getDateTime().toLocalDate(),planner.getDateTime().toLocalDate(),
                planner.isCanceled(),planner.isDone(),0,10);
        assertNotNull(page);
        assertEquals(page,Page.empty());

    }
}
