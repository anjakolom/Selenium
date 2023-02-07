import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Zadanie_14 extends TestBase {

    @Test
    public void windowsTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        helper.login("admin", "admin", driver);
        List<WebElement> menu = driver.findElements(By.cssSelector("#app- > a > span.name"));
        List<String> a = helper.getMenu(menu);
        int i = a.indexOf("Countries");
        menu.get(i).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#content > h1")).getText().trim(), "Countries");
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("table tbody td:nth-child(5) a")));
        driver.findElements(By.cssSelector("table tbody td:nth-child(5) a")).get(0).click();
        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("i[class=\"fa fa-external-link\"]")));
        List<WebElement> categories = driver.findElements(By.cssSelector("i[class=\"fa fa-external-link\"]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        for (i = 0; i < categories.size(); i++) {
            categories.get(i).click();
            String newWindow = wait.until(helper.thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
    }
}
