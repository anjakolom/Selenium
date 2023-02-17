import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadanie_17 extends TestBase {

    @Test
    public void logTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        System.out.println(driver.manage().logs().getAvailableLogTypes());
        helper.login("admin", "admin", driver);
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        String locator = "#content > form > table > tbody > tr[class='row'] > td:nth-child(3) a";

        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector(locator)));
        int count = driver.findElements(By.cssSelector(locator)).size();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        ArrayList<String> list = new ArrayList<>();

        for (int i = 2; i < count; i++) {
            List<WebElement> duck = driver.findElements(By.cssSelector(locator));
            duck.get(i).click();
            driver.manage().logs().get("browser").forEach(l -> list.add(l.toString()));
            driver.navigate().back();
        }
        for (String s: list){
            System.out.println(s);
        }
        Assert.assertEquals(0,list.size());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
    }
}
