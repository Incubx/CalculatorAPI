package shevtsov.denis.calculator_api.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import shevtsov.denis.calculator_api.Entity.Record;
import shevtsov.denis.calculator_api.Entity.UserInfo;
import shevtsov.denis.calculator_api.Repository.RecordRepository;
import shevtsov.denis.calculator_api.Repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Component
@Aspect
public class RequestHistoryAspect {

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

    @Pointcut("@annotation(SaveRequestHistory)")
    public void saveRequestRecord() {
    }

    @AfterReturning(pointcut = "saveRequestRecord()", returning = "result")
    public void saveRequestRecordCall(JoinPoint jp, Double result) {
        Record record = getRecord((String) jp.getArgs()[0], result.toString());
        recordRepository.save(record);
    }

    private Record getRecord(String expression, String result) {
        Record record = new Record();
        record.setExpression(expression);
        record.setResult(result);
        record.setTimestamp(new Date());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Optional<UserInfo> userInfo = userRepository.findByUsername(user.getUsername());
        if (userInfo.isPresent())
            record.setUser(userInfo.get());
        else
            record.setUser(null);

        return record;
    }

    @AfterThrowing(pointcut = "saveRequestRecord()", throwing = "arithmeticException")
    public void saveRequestRecordCall(JoinPoint jp, ArithmeticException arithmeticException) {
        Record record = getRecord((String) jp.getArgs()[0], "dividing by zero!");
        recordRepository.save(record);

    }

    @AfterThrowing(pointcut = "saveRequestRecord()", throwing = "argumentException")
    public void saveRequestRecordCall(JoinPoint jp, IllegalArgumentException argumentException) {
        Record record = getRecord((String) jp.getArgs()[0], "wrong expression");
        recordRepository.save(record);
    }
}
