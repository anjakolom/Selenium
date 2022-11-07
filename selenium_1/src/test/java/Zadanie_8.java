import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Zadanie_8 extends TestBase {
    @Test
    public void countryTest() {

        driver.get("http://localhost/litecart/admin/login.php");
        helper.login("admin", "admin", driver);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries = driver.findElements(By.cssSelector("tr.row"));

        List<String> arrayCountries = new ArrayList<>();
        List<String> arrayCountries2 = new ArrayList<>();
        for (WebElement c : countries) {
            String name = c.findElement(By.xpath("td[5]/a")).getText();
            arrayCountries.add(name);
            String zone = c.findElement(By.xpath("td[6]")).getText().trim();
            if (zone.equals("0") == false) {
                arrayCountries2.add(name);
            }
        }
        helper.checkSort(arrayCountries);

        List<String> arrayCountries3 = new ArrayList<>();
        for (String c : arrayCountries2) {
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            driver.findElement(By.linkText(c)).click();
            List<WebElement> countries3 = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
            for (WebElement s : countries3) {
                String name = s.getAttribute("innerText");
                if (name.length() > 0 && s.isDisplayed()) {
                    arrayCountries3.add(name);
                }
            }
            helper.checkSort(arrayCountries3);
            arrayCountries3.clear();
        }
    }
}
