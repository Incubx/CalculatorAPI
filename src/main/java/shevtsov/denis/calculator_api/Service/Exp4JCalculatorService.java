package shevtsov.denis.calculator_api.Service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import shevtsov.denis.calculator_api.aspects.SaveRequestHistory;


@Service
public class Exp4JCalculatorService implements CalculatorService {

    @Override
    @SaveRequestHistory
    public double evaluate(String expressionString) throws ArithmeticException, IllegalArgumentException {
        Expression expression = new ExpressionBuilder(expressionString).build();
        return round(expression.evaluate());
    }

    private double round(double n){
        double scale = Math.pow(10, 15);
        return Math.round(n*scale)/scale;
    }
}
