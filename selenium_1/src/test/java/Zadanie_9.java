import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Zadanie_9 extends TestBase {
    @Test
    public void countryTest() {

        driver.get("http://localhost/litecart/admin/login.php");
        helper.login("admin", "admin", driver);
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        Assert.assertTrue(helper.isElementPresent(driver,By.cssSelector("#content > form > table  td:nth-child(3) > a")));
        List<WebElement> line = driver.findElements(By.cssSelector("#content > form > table  td:nth-child(3) > a"));
        List<String> countries = new ArrayList<>();

        for (WebElement c : line) {
            System.out.println(c.getAttribute("innerText"));
            countries.add(c.getAttribute("innerText").trim());
        }

        for (int i=0; i<countries.size();i++){
            Assert.assertTrue(helper.isElementPresent(driver,By.linkText(countries.get(i))));
            driver.findElement(By.linkText(countries.get(i))).click();
            Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("#content > h1")));
            Assert.assertEquals(driver.findElement(By.cssSelector("#content > h1")).getText(), "Edit Geo Zone");
            Assert.assertTrue(helper.isElementPresent(driver,By.cssSelector("input[name=\"name\"]")));
            Assert.assertEquals(countries.get(i),driver.findElement(By.cssSelector("input[name=\"name\"]")).getAttribute("defaultValue"));
            Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("#table-zones")));
            Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("#table-zones td:nth-child(3) [selected=\"selected\"]")));
            List<WebElement> zones = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) [selected=\"selected\"]"));
            List<String> arrayZonesExpected = new ArrayList<String>();
            List<String> arrayZones = new ArrayList<String>();

            for (WebElement z: zones) {
                System.out.println(z.getAttribute("innerText"));
                arrayZones.add(z.getAttribute("innerText"));
            }
            arrayZonesExpected = new ArrayList<String>(arrayZones);
            Collections.sort(arrayZonesExpected);
            Assert.assertEquals(arrayZonesExpected, arrayZones);

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }
}
