import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        Duration duration = Duration.ofDays(10);
        wait = new WebDriverWait(driver, duration);
    }

    @Test
    public void myTest() {
        driver.get("http://github.com/anjakolom/selenium/");
        driver.findElement(By.linkText("Selenium")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
