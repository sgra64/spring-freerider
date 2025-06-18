# 6. *Properties*

*Spring Boot* supports *application configuration* to allow changes in program
behavior without requiring to compile source code, which may not be available
in the deployment environment, e.g. at a customer site.

*Application configuration* delegates information to *"configuration files"*,
which are text files that can be adjusted by an editor.
The program reads *"configuration files"* and stores the information in an
internal structure, in Java often by class
[*Properties*](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html).

In contrast to Java, *Spring Boot* automatically reads configuration files from
path `src/main/resources` and makes information available in the program.

Configuration files are passed to compiled files
`target/classes/application.properties`
such that they are available at run-time on the CLASSPATH (`target/classes` is
in CLASSPATH).

The most prominent file *Spring Boot* uses for configuration is file:
[*application.properties*](src/main/resources/application.properties)

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
```

*Spring Boot* uses a hierarchical (tree) structure of names connected by dots.

Alter property: `spring.main.banner-mode` from `console` to `off` and re-run
the program. This change will turn off printing the Spring banner.

In the program, property values can be accessed by property variables:

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
```

Output:

```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.0)

[...] - Starting Application using Java 21 with PID 11992 (C:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider\target\classes started by svgr2 in c:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider)
[...] - No active profile set, falling back to 1 default profile: "default"
[...] - -<1>--> FreeriderRunner constructor, programName=null
[...] - -<1>--> FreeriderRunner constructor, programNameFinal="Freerider Reservation System"
[...] - Started Application in 1.77 seconds (process running for 2.291)
[...] - -<2>--> FreeriderRunner.run(), programName="Freerider Reservation System"
```
