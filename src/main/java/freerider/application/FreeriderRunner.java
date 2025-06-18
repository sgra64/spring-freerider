package freerider.application;

// make sure to import 'org.slf4j.Logger' and not 'java.lang.System.Logger',
// which is Java's built-in logger (former 'java.util.logging' JUL), see
// https://stackoverflow.com/questions/11359187/why-not-use-java-util-logging
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class FreeriderRunner implements CommandLineRunner {

    /**
     * Any String can be used to create or identify a new logger instance.
     * A common technique has become to use the fully qualified class name.
     */
    private static final Logger log = LoggerFactory.getLogger(FreeriderRunner.class.getName());

    /*
     * Access value of 'spring.application.name' property
     */
    @Value("${spring.application.name}")
    private String programName;

    private final String programNameFinal;

    /**
     * Constructor-injected property value.
     */
    FreeriderRunner(
        @Value("${spring.application.name}") String applicationName
    ) {
        this.programNameFinal = applicationName;
        // 
        // 'programName' not initialized yet
        log.info(String.format("-<1>--> FreeriderRunner constructor, programName=%s", programName));
        log.info(String.format("-<1>--> FreeriderRunner constructor, programNameFinal=%s", programNameFinal));
    }


    @Override
    public void run(String... args) throws Exception {
        /*
         * print program name as welcome message
         */
        log.info(String.format("-<2>--> FreeriderRunner.run(), programName=%s", programName));
    }
}