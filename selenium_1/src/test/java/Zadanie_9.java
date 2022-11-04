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

        //#content > form > table  td:nth-child(3) > a
        Assert.assertTrue(helper.isElementPresent(driver,By.cssSelector("#content > form > table  td:nth-child(3) > a")));
        List<WebElement> countries = driver.findElements(By.cssSelector("#content > form > table  td:nth-child(3) > a"));
        for (WebElement c : countries) {
            System.out.println(c.getAttribute("innerText"));
           // c.findElement(By.linkText(c.getAttribute("innerText"))).click();
        }

        Assert.assertTrue(helper.isElementPresent(driver,By.cssSelector("#content > h1")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#content > h1")).getText(), "Edit Geo Zone");

        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("#table-zones")));
        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("#table-zones td:nth-child(3) [selected=\"selected\"]")));
        List<WebElement> zones = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) [selected=\"selected\"]"));
        List<String> arrayZonesExpected = new ArrayList<String>();
        List<String> arrayZones = new ArrayList<String>();
        for (WebElement c : zones) {
            System.out.println(c.getAttribute("innerText"));
            arrayZones.add(c.getAttribute("innerText"));
        }
        arrayZonesExpected = new ArrayList<String>(arrayZones);
        Collections.sort(arrayZonesExpected);
        Assert.assertEquals(arrayZonesExpected, arrayZones);

    }
}
