# 3. *"Inversion-of-Control" and Startup*

*Spring Boot* immediatly assumes control when the application is started and
never returns control before exit.

This behavior is programmed in the *main()* - function in
[*Application.java*](src/main/java/freerider/application/Application.java) :

```java
package freerider.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

The principle of
[*"Inversion-of-Control (IOC)"*](https://martinfowler.com/bliki/InversionOfControl.html)
(Martin Fowler, 2005)
means the reversal of the usual control flow in programs that start somewhere
and progress by invoking functions and methods, that in turn invoke functions
and methods, and so on. This is called a *"regular control flow"* of a
program.

*"Inversion-of-Control"* means control is handed from the program over to a
*control unit* (here the *Spring Boot Runtime System*), which then calls
functions and methods.
The difference is that the order in which functions and methods are called
is not programmed, it is determined by the *control unit* (by the *Spring Boot
Runtime System*).

The code below traces the invocations of the *Spring Boot Runtime System*.

1. Execution starts in the *main()* - function logging line `-<1>-- ...`.

1. Since class *Application* carries an annotation `@SpringBootApplication`,
    it becomes a *Spring Boot* *"Bean"* object, which is instantiated by
    *Spring Boot*. Object creation invokes the constructor causing the
    output of log line `-<2>-- ...`.

1. Then, *Spring Boot* scans the code for more annotations such as
    `@EventListener` invoking methods based on the indicated event type,
    here *ApplicationReadyEvent* trigged after *Spring Boot* is done
    with its initialization and *"ready"*.

    Invocation of method *runAfterStartup()* is logged by line `-<3>-- ...`.

1. Finally, if nothing else happens, *Spring Boot* decides to exit and
    returns to the *main()* - function causing log line `-<4>-- ...`.


```java
package freerider.application;

@SpringBootApplication
public class Application {

    /**
     * Constructor executes when the Spring runtime system creates the Application Bean.
     */
    Application() {
        System.out.println("\n-<2>--> Application constructor is called\n");
    }


    public static void main(String[] args) {
        System.out.println("\n-<1>--> before Spring runtime system is called\n");
        // 
        SpringApplication.run(Application.class, args);
        // 
        System.out.println("\n-<4>--> after Spring runtime system has exited\n");
    }

    /**
     * Called from Spring runtime system after IoC container has been initialized.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("\n-<3>--> doSomethingAfterStartup()");
    }
}
```

Running the program shows log lines in the described order.

```
-<1>--> before Spring runtime system is called

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.0)

2025-06-18T21:35:49.008+02:00  INFO 7404 --- ["Freerider Reservation System"] [           main] freerider.application.Application        : Starting Application using Java 21 with PID 7404 (C:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider\target\classes started by svgr2 in c:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider)

-<2>--> Application constructor is called

2025-06-18T21:35:50.041+02:00  INFO 7404 --- ["Freerider Reservation System"] [           main] freerider.application.Application        : Started Application in 2.101 seconds (process running for 2.694)

-<3>--> doSomethingAfterStartup()

-<4>--> after Spring runtime system has exited
```
