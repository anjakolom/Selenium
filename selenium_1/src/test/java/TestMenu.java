import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestMenu extends TestBase {
    @Test
    public void menuTest() {
        helper.login("admin", "admin", driver);

        List<WebElement> menu = driver.findElements(By.cssSelector("#app- > a > span.name"));
        List<String> a = helper.getMenu(menu);
        for (String i : a) {
            if (helper.isElementPresent(driver, By.linkText(i))) {
                driver.findElement(By.linkText(i)).click();
                System.out.println(i);
                List<WebElement> subBar = driver.findElements(By.cssSelector("ul.docs li"));
                if (subBar != null) {
                    List<String> b = helper.getMenu(subBar);
                    for (String k : b) {
                        if (helper.isElementPresent(driver, By.linkText(i))) {
                            driver.findElement(By.linkText(k)).click();
                            Assert.assertNotNull(driver.findElement(By.cssSelector("#content > h1")).getText());
                            System.out.println("--" + k);
                        }
                    }
                } else {
                    Assert.assertNotNull(driver.findElement(By.cssSelector("#content > h1")).getText());
                }
            }
        }
    }


}
