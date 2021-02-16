package shevtsov.denis.calculator_api;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shevtsov.denis.calculator_api.Service.CalculatorService;
import shevtsov.denis.calculator_api.Service.LibraryCalculatorService;

public class CalculatorServiceTest {

    private static final String simpleExp = "(1+2)*(4+(5-6/3.0))";
    private static final String wrongExp = "3.5+((2-6)";
    private static final String dividingByZeroExp = "(3+2)/(5-5)";

    //Testing that CalculatorService evaluates expression correct
    @Test
    public void evaluateExpressionTest() {
        CalculatorService service = new LibraryCalculatorService();

        Assertions.assertEquals((1 + 2) * (4 + (5 - 6 / 3.0)), service.evaluate(simpleExp));
        Assertions.assertThrows(ArithmeticException.class, () -> service.evaluate(dividingByZeroExp));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.evaluate(wrongExp));
    }
}
