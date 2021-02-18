package shevtsov.denis.calculator_api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shevtsov.denis.calculator_api.Service.CalculatorService;
import shevtsov.denis.calculator_api.Service.Exp4JCalculatorService;

public class CalculatorServiceTest {

    private static final String simpleExp = "(0.2+0.2*0.5)/8";
    private static final String wrongExp = "3.5+((2-6)";
    private static final String dividingByZeroExp = "(3+2)/(5-5)";

    CalculatorService service = new Exp4JCalculatorService();

    @Test
    public void evaluateSimpleExpressionTest() {
        Assertions.assertEquals(0.0375, service.evaluate(simpleExp));
    }

    @Test
    public void evaluateDividingByZeroExpressionTest() {
        Assertions.assertThrows(ArithmeticException.class, () -> service.evaluate(dividingByZeroExp));
    }

    @Test
    public void evaluateWrongExpressionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.evaluate(wrongExp));
    }


}
