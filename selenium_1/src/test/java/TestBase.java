import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public Helper helper;
    public StyleHelper style;


    @Before
    public void start() {
        driver = new ChromeDriver();
        helper = new Helper();
        style = new StyleHelper();

        /*EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);*/

       /* FirefoxOptions caps = new FirefoxOptions();
        //DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.Capability.MARIONETTE, false);
        driver = new FirefoxDriver();*/

        /*FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);*/

       /*FirefoxOptions options = new FirefoxOptions();
       DesiredCapabilities caps = new DesiredCapabilities();
       options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
       driver = new FirefoxDriver(options);*/

        Duration duration = Duration.ofMillis(10);
        wait = new WebDriverWait(driver, duration);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
