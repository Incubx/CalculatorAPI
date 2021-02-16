package shevtsov.denis.calculator_api;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shevtsov.denis.calculator_api.Service.CalculatorService;
import shevtsov.denis.calculator_api.Service.LibraryCalculatorService;

public class CalculatorServiceTest {

    private static final String simpleExp = "(0.2+0.2*0.2)";
    private static final String wrongExp = "3.5+((2-6)";
    private static final String dividingByZeroExp = "(3+2)/(5-5)";

    //Testing that CalculatorService evaluates expression correct
    @Test
    public void evaluateExpressionTest() {
        CalculatorService service = new LibraryCalculatorService();

        Assertions.assertEquals(0.24, service.evaluate(simpleExp));
        //Assertions.assertThrows(ArithmeticException.class, () -> service.evaluate(dividingByZeroExp));
        //Assertions.assertThrows(IllegalArgumentException.class, () -> service.evaluate(wrongExp));
    }
}
