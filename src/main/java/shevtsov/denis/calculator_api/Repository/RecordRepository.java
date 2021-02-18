package shevtsov.denis.calculator_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shevtsov.denis.calculator_api.Entity.Record;

import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findRecordsByExpression(String expression);

    List<Record> findRecordsByTimestampBetween(Date startDate, Date endDate);

    List<Record> findRecordsByUserInfoUsername(String username);

}
