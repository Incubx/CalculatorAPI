package shevtsov.denis.calculator_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import shevtsov.denis.calculator_api.Entity.Record;
import shevtsov.denis.calculator_api.Entity.UserInfo;
import shevtsov.denis.calculator_api.Repository.RecordRepository;
import shevtsov.denis.calculator_api.Repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    private UserRepository userRepository;

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveRecord(String expression, String result) {
        Record record = getRecord(expression, result);
        recordRepository.save(record);
    }

    //Method creates Record from expression and result, using Authentication to get Principal
    private Record getRecord(String expression, String result) {
        Record record = new Record();
        record.setExpression(expression);
        record.setResult(result);
        record.setTimestamp(new Date());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        record.setUser(null);
        if (auth != null) {
            User user = (User) auth.getPrincipal();
            Optional<UserInfo> userInfo = userRepository.findByUsername(user.getUsername());
            userInfo.ifPresent(record::setUser);
        }
        return record;
    }

    public List<Record> findByExpression(String expression) {
        return recordRepository.findRecordsByExpression(expression);
    }

    public List<Record> findByDate(Date startDate, Date endDate) {
        return recordRepository.findRecordsByTimestampBetween(startDate, endDate);
    }

    public List<Record> findByUsername(String username) {
            return recordRepository.findRecordsByUserInfoUsername(username);
    }

}
