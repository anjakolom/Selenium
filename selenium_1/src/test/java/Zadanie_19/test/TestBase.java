package Zadanie_19.test;

import Zadanie_19.app.Application;
import org.junit.Before;

public class TestBase {

    public Application app;
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();


    @Before

    public void start() {
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
    }

}
