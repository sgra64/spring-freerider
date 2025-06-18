# 5. *Logging*

[*Logging*](https://last9.io/blog/a-guide-to-spring-boot-logging/)
([*Baeldung*](https://www.baeldung.com/spring-boot-logging))
is the recording of text messages produced during program execution.

*Logging* in software systems has many reasons:

- record access to components and data for compliance reasons (*who* accessed
    *what* and *when*),

- record program execution for later investigation, e.g. to find out why a
    program crashed or behaved abnormally,

- record suplemental information and early warnings during program execution.

Printing lines to `<stdout>` with `System.out.println()` is bad practice in
programs and must be removed for product versions because programs in
production environments have no console attached (e.g. when executing in a
container).

*Logging* provides flexible infrastructure to

1. *Collect* information (record log lines), categorized by

    - `trace` -- for low-priority messages,

    - `debug` -- to assist debugging,

    - `info ` -- to log general information,

    - `warn ` -- for warn messages for acceptable, but unusual conditions,
        e.g. empty names

    - `error` -- record error conditions that cause program termination.

1. *Filter* log information by:

    - source (logger-instance),

    - priority (trace, debug, info, warn, error).

1. *Formatting* information to log (selection, format of relevant information).

1. *Appenders* define the destination of log messages in log files (e.g. by a
[*rolling file appender*](https://www.codejava.net/frameworks/spring-boot/logback-rolling-files-example)),
into a databases or over the network.


The code shows the creation of a logger and the collection of log lines:

```java
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
     * Any String can be used to create and identify a new logger instance.
     * It is common to use the fully qualified class name.
     */
    private static final Logger log = LoggerFactory.getLogger(FreeriderRunner.class.getName());

    /*
     * Initialized with value of 'spring.application.name' property
     */
    @Value("${spring.application.name}")
    private String programName;

    /**
     * Constructor executes when the Spring creates the bean object.
     */
    FreeriderRunner() {
        log.info("-<1>--> FreeriderRunner constructor called");
    }


    @Override
    public void run(String... args) throws Exception {
        /*
         * print program name as welcome message
         */
        log.info(String.format("-<2>--> FreeriderRunner.run() for %s", programName));

        // Trace > Debug > Info > Warn > Error > Fatal > Off
        log.trace("trace log message");
        log.debug("debug log message");
        log.info("info log message");
        log.warn("warning log message");
        log.error("error log message");
    }
}
```



*Logging* can negatively impact program performance.

It is important that logging-logic (*Collection*, *Filtering*, *Formatting*,
*Appending*) is not hard-coded in programs, but is configurable.

*Spring Boot* uses the
[*LogBack*](https://www.codejava.net/frameworks/spring-boot/logback-rolling-files-example)
framework and can be configured in special files *"logback.xml"* or
*"logback-spring.xml"*.

<img src="https://www.codejava.net/images/articles/frameworks/springboot/logging/spring_boot_log_architecture.png" width="480"/>


Simplified configuration can also be achieved in file `"application.properties"`:

```properties
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Spring Boot supports configurability in the 'application-properties' file.
# Settings can change the application behavior without requiring compilation.
# 
# https://docs.spring.io/spring-boot/appendix/application-properties/index.html
# 
# 
spring.application.name="Freerider Reservation System"

# output destination of 'Spring' banner message: off, console, log
spring.main.banner-mode=console


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Logging section:
# 
# Spring Boot uses the 'Logback' implementation for logging. A separate file
# `logback-spring.xml` provides advanced configuration. Alternatively, 'Log4J2'
# can be used.
#  - logging.config=classpath:log4j2.xml
#  - logging.config=classpath:logback-spring.xml
# 
# Log Levels:
# - Fatal, Error are used for non-recoverable error.
# - Warn    is used for recoverable error.
# - Info    is used for audit purpose.
# - Debug   is used for investigation.
# - Trace   is used for detailed investigation.
# Log Precedence, most > least:
# - Trace > Debug > Info > Warn > Error > Fatal > Off
# 
logging.level.org.springframework=off
logging.level.freerider.reservation.application=info

logging.threshold.console=info
logging.threshold.file=info

# File Appender
logging.file.name=logs/app.log

# Format log lines, use time(ms) for console log and date+time(s) for file log
logging.pattern.console=[%-5level] [%d{HH:mm:ss:SSS}, %-48logger{46}] - %msg%n
logging.pattern.file=[%-5level] [%d{yyyy-MM-dd HH:mm:ss}, %-48logger{46}] - %msg%n

```

When the program executes, the new format of log line can be noticed.

Furthermore, there are no more *System.out.print()* lines:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.0)

[INFO ] [23:20:17:778, freerider.application.Application               ] - Starting Application using Java 21 with PID 19296 (C:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider\target\classes started by svgr2 in c:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider)
[INFO ] [23:20:17:778, freerider.application.Application               ] - No active profile set, falling back to 1 default profile: "default"
[INFO ] [23:20:18:759, freerider.application.FreeriderRunner           ] - -<1>--> FreeriderRunner constructor called
[INFO ] [23:20:18:936, freerider.application.Application               ] - Started Application in 2.152 seconds (process running for 2.642)
[INFO ] [23:20:18:953, freerider.application.FreeriderRunner           ] - -<2>--> FreeriderRunner.run() in "Freerider Reservation System"
[INFO ] [23:20:18:962, freerider.application.FreeriderRunner           ] - info log message
[WARN ] [23:20:18:963, freerider.application.FreeriderRunner           ] - warning log message
[ERROR] [23:20:18:963, freerider.application.FreeriderRunner           ] - error log message
```
