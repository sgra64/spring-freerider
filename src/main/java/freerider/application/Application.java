package freerider.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


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