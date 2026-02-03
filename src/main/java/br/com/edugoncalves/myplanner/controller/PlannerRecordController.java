package br.com.edugoncalves.myplanner.controller;

import br.com.edugoncalves.myplanner.controller.converter.PlannerRecordDTOConverter;
import br.com.edugoncalves.myplanner.controller.dto.PlannerRecordDTO;
import br.com.edugoncalves.myplanner.model.PlannerRecord;
import br.com.edugoncalves.myplanner.service.PlannerRecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/planner/record")
public class PlannerRecordController {

    private final PlannerRecordService plannerRecordService;
    private final PlannerRecordDTOConverter converter;


    @PostMapping
    public PlannerRecordDTO save(@RequestBody @Valid PlannerRecordDTO dto){
        return converter.convert(plannerRecordService.save(converter.convert(dto)));
    }

    @GetMapping
    public Page<PlannerRecordDTO> findPaginated(
            @RequestParam(required = false) String service,
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String location,
            @RequestParam(required = false)
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)  LocalDate initalDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)  LocalDate finalDate,
            @RequestParam(required = false) Boolean canceled,
            @RequestParam(required = false) Boolean done,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return plannerRecordService.findPaginated(service,customer,location,
                initalDate,finalDate,canceled,done,page,size).map(converter::convert);
    }
}
