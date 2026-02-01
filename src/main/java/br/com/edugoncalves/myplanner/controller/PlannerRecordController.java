package br.com.edugoncalves.myplanner.controller;

import br.com.edugoncalves.myplanner.model.PlannerRecord;
import br.com.edugoncalves.myplanner.service.PlannerRecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/planner/record")
public class PlannerRecordController {
    private final PlannerRecordService service;


    @PostMapping
    public PlannerRecord save(@RequestBody PlannerRecord plannerRecord){
        return service.save(plannerRecord);
    }
}
