package br.com.edugoncalves.myplanner.service;

import br.com.edugoncalves.myplanner.exception.GenericExeception;
import br.com.edugoncalves.myplanner.model.PlannerRecord;
import br.com.edugoncalves.myplanner.repository.PlannerRecordCustomRepository;
import br.com.edugoncalves.myplanner.repository.PlannerRecordRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
@Service
public class PlannerRecordService {
    private final PlannerRecordRepository repository;
    private final PlannerRecordCustomRepository customRepository;
    private final static String SAVE_ERROR="Error during save planner records.";
    private final static String FIND_ERROR="Error during find planner records.";
    private final static String SAVING_INFO="Saving planner record -> service: {} customer : {} location: {} dateTime: {}";
    private final static String FINDING_INFO=  """
                                                    Finding planner record -> service: {}
                                                    customer : {} location: {}
                                                    initialDate: {} finalDate: {}
                                                    canceled: {} done: {}
                                                    size: {} page: {}
                                                """;



    public PlannerRecord save(PlannerRecord planner){
        try {
            log.info(SAVING_INFO,planner.getService(),planner.getCustomer(),
                    planner.getLocation(),planner.getDateTime());
            return repository.save(planner);
        }catch (Exception ex){
            log.error(SAVE_ERROR,ex);
            throw new GenericExeception(SAVE_ERROR);
        }

    }
    public Page<PlannerRecord> findPaginated(String service, String customer,
                                             String location, LocalDate initialDate, LocalDate finalDate,
                                             Boolean canceled, Boolean done, int page, int size){
        try {
            log.info(FINDING_INFO,service, customer, location,
                    initialDate, finalDate, canceled, done, page, size);
            return customRepository.findPaginated(service, customer, location,
                    initialDate, finalDate, canceled, done, page, size);
        }catch (Exception ex){
            log.error(FIND_ERROR,ex);
            throw new GenericExeception(FIND_ERROR);
        }
    }
}

