import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {

    public EventFiringWebDriver driver;
    //public WebDriver driver;
    public WebDriverWait wait;
    public Helper helper;
    public StyleHelper style;
    public ProductHelper product;

    public static class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            super.beforeFindBy(by, element, driver);
            System.out.println("***"+by+"***");
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            super.afterFindBy(by, element, driver);
            System.out.println("***"+ by+" found ***");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            super.onException(throwable, driver);
            System.out.println("***"+throwable+"***");

        }
    }

    @Before
    public void start() {

        helper = new Helper();
        style = new StyleHelper();
        product = new ProductHelper();

        ChromeOptions options = new ChromeOptions();
        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new MyListener());
        Duration duration = Duration.ofMillis(10);
        wait = new WebDriverWait(driver, duration);

        /*EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);*/

       /*FirefoxOptions caps = new FirefoxOptions();
        //DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.Capability.MARIONETTE, false);
        driver = new FirefoxDriver();*/

        /*FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);*/

        /*FirefoxOptions options = new FirefoxOptions();
        DesiredCapabilities caps = new DesiredCapabilities();
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new EventFiringWebDriver(new FirefoxDriver(options));
        driver.register(new MyListener());
        Duration duration = Duration.ofMillis(10);
        wait = new WebDriverWait(driver, duration);*/

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
