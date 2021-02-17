package shevtsov.denis.calculator_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CalculatorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApiApplication.class, args);
    }

}
