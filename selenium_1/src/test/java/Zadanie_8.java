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
        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("tr.row")));
        List<WebElement> countries = driver.findElements(By.cssSelector("tr.row"));

        List<String> arrayCountries = new ArrayList<>();
        List<String> arrayCountries2 = new ArrayList<>();
        for (WebElement c : countries) {
            Assert.assertTrue(helper.isElementPresent(c, By.xpath("td[5]/a")));
            arrayCountries.add(c.findElement(By.xpath("td[5]/a")).getText());
            String zone = c.findElement(By.xpath("td[6]")).getText().trim();
            if (zone.equals("0") == false) {
                arrayCountries2.add(c.findElement(By.xpath("td[5]/a")).getText());
            }
        }
        List<String> arrayCountriesExpected = new ArrayList<String>(arrayCountries);
        Collections.sort(arrayCountriesExpected);
        Assert.assertEquals(arrayCountriesExpected, arrayCountries);

        List<String> arrayCountries3 = new ArrayList<>();
        List<String> arrayCountriesExpected3 = new ArrayList<>();
        for (String c : arrayCountries2) {
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            Assert.assertTrue(helper.isElementPresent(driver, By.linkText(c)));
            driver.findElement(By.linkText(c)).click();
            Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("#table-zones td:nth-child(3)")));
            List<WebElement> countries3 = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
            for (WebElement s : countries3) {
                if (s.getAttribute("innerText").length()>0 && s.isDisplayed()){
                    arrayCountries3.add(s.getAttribute("innerText"));
                }
            }
            arrayCountriesExpected3 = new ArrayList<String>(arrayCountries3);
            Collections.sort(arrayCountriesExpected3);
            Assert.assertEquals(arrayCountriesExpected3, arrayCountries3);
            arrayCountries3.clear();
            arrayCountriesExpected3.clear();

        }
    }
}
