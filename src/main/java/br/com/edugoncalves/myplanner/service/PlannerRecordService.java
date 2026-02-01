package br.com.edugoncalves.myplanner.service;

import br.com.edugoncalves.myplanner.model.PlannerRecord;
import br.com.edugoncalves.myplanner.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PlannerRecordService {
    private final RecordRepository repository;

    public PlannerRecord save(PlannerRecord plannerRecord){
        return repository.save(plannerRecord);

    }
}

