package br.com.edugoncalves.myplanner.controller;

import br.com.edugoncalves.myplanner.controller.converter.PlannerRecordDTOConverter;
import br.com.edugoncalves.myplanner.controller.dto.PlannerRecordDTO;
import br.com.edugoncalves.myplanner.model.PlannerRecord;
import br.com.edugoncalves.myplanner.service.PlannerRecordService;
import br.com.edugoncalves.myplanner.util.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlannerRecordControllerTest {

    @Mock
    private PlannerRecordService service;

    @Mock
    private PlannerRecordDTOConverter converter;

    @InjectMocks
    private PlannerRecordController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void controller_mustReturnOk_whenRequestSaveSuccefully() throws Exception {
        PlannerRecord planner = TestUtils.buildValidPlannerRecord();
        PlannerRecordDTO dto = TestUtils.buildValidPlannerRecordDTO();

        // DTO -> Entity (controller receives DTO)
        doReturn(planner).when(converter).convert(any(PlannerRecordDTO.class));

        // service save
        doReturn(planner).when(service).save(any(PlannerRecord.class));

        // Entity -> DTO (controller returns DTO)
        doReturn(dto).when(converter).convert(any(PlannerRecord.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/planner/record")
                        .content(asAJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asAJsonString(dto)));
    }

    @Test
    void controller_mustReturnOk_whenRequestFindSuccefully() throws Exception {
        PlannerRecord planner=TestUtils.buildValidPlannerRecord();
        Page<PlannerRecord> page=new PageImpl<>(List.of(planner),
                PageRequest.of(0,10),1);
        when(service.findPaginated(any(),any(),any(),any(),any(),
                any(),any(),anyInt(),anyInt())).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/planner/record")
                .param("page","0")
                .param("size","10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asAJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
