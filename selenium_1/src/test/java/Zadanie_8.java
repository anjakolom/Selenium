import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Zadanie_8 extends TestBase {
    @Test
    public void countryTest() {

        driver.get("http://localhost/litecart/admin/login.php");
        helper.login("admin", "admin", driver);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        Assert.assertNotNull(driver.findElements(By.cssSelector("tr.row")));
        List<WebElement> countries = driver.findElements(By.cssSelector("tr.row"));
        List<String> arrayCountries = new ArrayList<>();
        List<String> arrayCountries2 = new ArrayList<>();
        for (WebElement c : countries) {
            Assert.assertNotNull(driver.findElements(By.xpath("td[5]/a")));
            arrayCountries.add(c.findElement(By.xpath("td[5]/a")).getText());
            String zone = c.findElement(By.xpath("td[6]")).getText().trim();
            if (zone.equals("0") == false) {
                arrayCountries2.add(c.findElement(By.xpath("td[5]/a")).getText());
            }
        }
        List<String> arrayCountriesExpected = arrayCountries;
        Collections.sort(arrayCountriesExpected);
        Assert.assertEquals(arrayCountriesExpected, arrayCountries);

        List<String> arrayCountries3 = new ArrayList<>();
        List<String> arrayCountriesExpected3 = new ArrayList<>();
        for (String c : arrayCountries2) {
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            Assert.assertNotNull(driver.findElements(By.linkText(c)));
            driver.findElement(By.linkText(c)).click();
            Assert.assertNotNull(driver.findElements(By.cssSelector("#table-zones td:nth-child(3) input[type=hidden]")));
            List<WebElement> countries3 = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) input[type=hidden]"));
            for (WebElement s : countries3) {
                arrayCountries3.add(s.getAttribute("defaultValue"));
            }
            arrayCountriesExpected3 = arrayCountries3;
            Collections.sort(arrayCountriesExpected3);
            Assert.assertEquals(arrayCountriesExpected3, arrayCountries3);

        }


    }
}
