import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class ProductHelper {
    public void addToCart(WebDriver driver) {

        driver.get("http://localhost/litecart/en/");
        int count = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());//#cart > a.content > span.quantity
        Assert.assertNotNull(driver.findElements(By.cssSelector("li.product.column.shadow.hover-light")));
        List<WebElement> ducks = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light a.link"));
        String productName = ducks.get(0).getAttribute("title").trim();
        ducks.get(0).click();

        Assert.assertNotNull(driver.findElements(By.cssSelector("h1.title")));
        String pageName = driver.findElement(By.cssSelector("h1.title")).getAttribute("innerText").trim();
        Assert.assertEquals(productName, pageName);

        List<WebElement> select = driver.findElements(By.cssSelector("select"));
        if (select.size() > 0) {
            Select sel = new Select(select.get(0));
            sel.selectByIndex(1);
        }

        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
        int count2 = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
        while (count2 < count + 1) {
            count2 = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        }
    }

    public void removeFromCart(WebDriver driver, int count) {
        driver.get("http://localhost/litecart/en/");
        Assert.assertEquals(Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText()), count);
        driver.findElement(By.cssSelector("#cart a.link")).click();

        while (count != 0) {
            while (!driver.findElement(By.cssSelector("button[name=remove_cart_item]")).isDisplayed()){
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));}
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
            driver.navigate().refresh();
            if (!isElementPresent(driver,By.cssSelector("[class=\"sku\"]"))){
                count=0;
            };
        }
    }

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}