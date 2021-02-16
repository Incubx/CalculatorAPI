package shevtsov.denis.calculator_api.Service;

import org.springframework.stereotype.Service;

@Service
public interface CalculatorService {

    double evaluate(String expression) throws ArithmeticException, IllegalArgumentException;

}
