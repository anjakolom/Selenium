package cucumber;

import Zadanie_19.app.Application;
import io.cucumber.java8.En;

public class CucumberTestBase implements En {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    public CucumberTestBase() {
        Before(1, () -> {
            if (tlApp.get() != null) {
                app = tlApp.get();
                return;
            }

            app = new Application();
            tlApp.set(app);

            Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> {
                        app.quit();
                        app = null;
                    }));
        });

    }
}
