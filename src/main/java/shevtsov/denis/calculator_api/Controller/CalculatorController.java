package shevtsov.denis.calculator_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shevtsov.denis.calculator_api.Service.CalculatorService;

@RestController
@RequestMapping("evaluate")
public class CalculatorController {


    private CalculatorService calculatorService;

    @Autowired
    public void setCalculatorService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @GetMapping("")
    public ResponseEntity<Double> evaluateExpression(@RequestParam("expression") String expression){
        System.out.println(expression);
        try{
            double result = calculatorService.evaluate(expression);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        catch (ArithmeticException ex){
            return new ResponseEntity<>(null,HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
}
