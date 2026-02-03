package br.com.edugoncalves.myplanner.repository;

import br.com.edugoncalves.myplanner.model.PlannerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannerRecordRepository extends JpaRepository<PlannerRecord,Long> {
}
