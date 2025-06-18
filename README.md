# 4. *CommandLineRunner*

[*CommandLineRunner*](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/CommandLineRunner.html)
is an interface that implements a method `void run(String... args)`.

Any *Spring Boot* *"bean class"*, which is a class that carries a *Spring Boot*
annotation such as `@Component`, that implements interface *CommandLineRunner*
will be invoked by *Spring Boot*.

*CommandLineRunner* classes are therefore used to implement application logic
to execute after *Spring Boot* initialization.

There is no invocation of the `run(String... args)` method in the code, it is
invoked by *Spring Boot*.

In case multiple *CommandLineRunner* classes exist, *Spring Boot* will execute
all of them in an undefined order.

```java
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
```

The example also shows accessing a property value from file
`application.properties` stored under `src/main/resources`.

Also experiment turning the *"Spring"* banner on or off.

Output:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.0)

2025-06-18T22:51:59.333+02:00  INFO 18840 --- ["Freerider Reservation System"] [           main] freerider.application.Application        : Starting Application using Java 21 with PID 18840 (C:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider\target\classes started by svgr2 in c:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider) ...

--> "Freerider Reservation System"
```
