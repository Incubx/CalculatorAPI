package shevtsov.denis.calculator_api.Service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;


@Service
public class LibraryCalculatorService implements CalculatorService {

    @Override
    public double evaluate(String expressionString) throws ArithmeticException, IllegalArgumentException {
        Expression expression = new ExpressionBuilder(expressionString).build();
        return expression.evaluate();

    }
}
