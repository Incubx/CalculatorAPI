package shevtsov.denis.calculator_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shevtsov.denis.calculator_api.Entity.Record;
import shevtsov.denis.calculator_api.Service.RecordService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("history")
public class RequestHistoryController {

    private RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("date")
    public ResponseEntity<List<Record>> getRecordsByDate(
            @RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endDate){
        List<Record> records = recordService.findByDate(startDate,endDate);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("expression")
    public ResponseEntity<List<Record>> getRecordsByExpression(@RequestParam("expression") String expression){
        List<Record> records = recordService.findByExpression(expression);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<List<Record>> getRecordsByUser(@RequestParam("username")String username){
        List<Record> records = recordService.findByUsername(username);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
}
