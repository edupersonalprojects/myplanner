package br.com.edugoncalves.myplanner.controller.converter;

import br.com.edugoncalves.myplanner.controller.dto.PlannerRecordDTO;
import br.com.edugoncalves.myplanner.model.PlannerRecord;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class PlannerRecordDTOConverter {
    public PlannerRecordDTO convert(PlannerRecord plannerRecord){
        return Optional.ofNullable(plannerRecord).map(source->
                new PlannerRecordDTO(
                        source.getId(),
                        source.getService(),
                        source.getCustomer(),
                        source.getLocation(),
                        source.getDateTime(),
                        source.isDone(),
                        source.isCanceled()
                )).orElse(null);
    }
    public PlannerRecord convert(PlannerRecordDTO dto){
        return Optional.ofNullable(dto).map(source->
                PlannerRecord.builder()
                        .id(source.id())
                        .service(source.service())
                        .customer(source.customer())
                        .location(source.location())
                        .dateTime(source.dateTime())
                        .done(source.done())
                        .canceled(source.canceled())
                        .build()
        ).orElse(null);
    }
}
