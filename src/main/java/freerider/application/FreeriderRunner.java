package freerider.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class FreeriderRunner implements CommandLineRunner {

    /*
     * Access value of 'spring.application.name' property
     */
    @Value("${spring.application.name}")
    private String programName;


    @Override
    public void run(String... args) throws Exception {
        /*
         * print program name as welcome message
         */
        System.out.println(String.format("--> %s", programName));
    }
}