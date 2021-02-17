package shevtsov.denis.calculator_api.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shevtsov.denis.calculator_api.Repository.RecordRepository;
import shevtsov.denis.calculator_api.Repository.UserRepository;
import shevtsov.denis.calculator_api.Service.RecordService;


@Component
@Aspect
public class RequestHistoryAspect {

    private RecordService recordService;

    private RecordRepository recordRepository;

    private UserRepository userRepository;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

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
        recordService.saveRecord((String) jp.getArgs()[0],result.toString());
    }



    @AfterThrowing(pointcut = "saveRequestRecord()", throwing = "arithmeticException")
    public void saveRequestRecordCall(JoinPoint jp, ArithmeticException arithmeticException) {
        recordService.saveRecord((String) jp.getArgs()[0], "dividing by zero!");
    }

    @AfterThrowing(pointcut = "saveRequestRecord()", throwing = "argumentException")
    public void saveRequestRecordCall(JoinPoint jp, IllegalArgumentException argumentException) {
        recordService.saveRecord((String) jp.getArgs()[0], "wrong expression");
    }
}
