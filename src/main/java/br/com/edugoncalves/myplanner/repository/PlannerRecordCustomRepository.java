package br.com.edugoncalves.myplanner.repository;

import br.com.edugoncalves.myplanner.model.PlannerRecord;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Repository
public class PlannerRecordCustomRepository {
    private static final String LIKE_EXPRESSION="%%%s%%";
    private static final String COUNT_QUERY="SELECT COUNT(1) FROM planner_record WHERE 1=1 %s";
    private static final String DATA_QUERY="""
                                            SELECT * FROM
                                                planner_record
                                            WHERE 1=1 %s ORDER BY date_time DESC
                                            LIMIT :limit OFFSET :offset""";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Page<PlannerRecord> findPaginated(String service, String customer,
                                             String location, LocalDate initialDate,LocalDate finalDate,
                                             Boolean canceled,Boolean done, int page,int size){
        PageRequest pageRequest=PageRequest.of(page,size, Sort.by("date_time").descending());
        MapSqlParameterSource parameterSource=buildParameters(service,customer,location
                ,initialDate,finalDate,canceled,done);
        StringBuilder sbWhereClause=buildWhereClause(service,customer,location
                ,initialDate,canceled,done);
        Integer totalPlannerRecords=count(parameterSource,sbWhereClause.toString());
        parameterSource.addValue("limit",size);
        parameterSource.addValue("offset",pageRequest.getOffset());
        List<PlannerRecord> plannerRecords=find(parameterSource,sbWhereClause.toString());
        return new PageImpl<>(plannerRecords,pageRequest,totalPlannerRecords);

    }
    private Integer count(MapSqlParameterSource parameterSource,String whereClause){

        String sql=String.format(COUNT_QUERY,whereClause.isEmpty()?"":whereClause);
        return jdbcTemplate.queryForObject(sql,parameterSource,Integer.class);
    }
    private List<PlannerRecord> find(MapSqlParameterSource parameterSource,String whereClause){
        String sql=String.format(DATA_QUERY,whereClause.isEmpty()?"":whereClause);
        return jdbcTemplate.query(sql,parameterSource,new BeanPropertyRowMapper<>(PlannerRecord.class));
    }
    private MapSqlParameterSource buildParameters(String service, String customer, String location, LocalDate initialDate, LocalDate finalDate, Boolean canceled, Boolean done) {
        MapSqlParameterSource parameterSource= new MapSqlParameterSource();

        if(service!=null)
             parameterSource
                     .addValue("service",String
                             .format(LIKE_EXPRESSION,service));
        if(customer!=null)
            parameterSource
                    .addValue("customer",String
                            .format(LIKE_EXPRESSION,customer));

        if(location!=null)
            parameterSource
                    .addValue("location",String
                            .format(LIKE_EXPRESSION,location));

        if(done!=null)
            parameterSource
                    .addValue("done",done);

        if(canceled!=null)
            parameterSource
                    .addValue("canceled",canceled);

        if(initialDate!=null){
            parameterSource.addValue("initialDate",initialDate.atStartOfDay());
            parameterSource.addValue("finalDate",
                    Objects.requireNonNullElseGet(finalDate, () -> initialDate.plusYears(1))
                            .atTime(LocalTime.MAX));
        }

        return parameterSource;
    }
    private StringBuilder buildWhereClause(String service, String customer, String location, LocalDate initialDate, Boolean canceled, Boolean done) {
        StringBuilder sbWhereClause= new StringBuilder();
        if(service!=null)
            sbWhereClause.append(" AND service LIKE :service");

        if(customer!=null)
            sbWhereClause.append(" AND customer LIKE :customer");

        if(location!=null)
            sbWhereClause.append(" AND location LIKE :location");
        if(initialDate!=null)
            sbWhereClause.append(" AND date_tme BETWEEN  :initialDate AND :finalDate");

        if(done!=null)
            sbWhereClause.append(" AND done= :done");

        if(canceled!=null)
            sbWhereClause.append(" AND canceled= :canceled");



        return sbWhereClause;

    }

 }


