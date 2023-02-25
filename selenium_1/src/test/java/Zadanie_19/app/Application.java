package Zadanie_19.app;

import Zadanie_19.pages.Checkout;
import Zadanie_19.pages.Litecart;
import Zadanie_19.pages.Product;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Application {
    private WebDriver driver;

    private Product product;
    private Litecart litecart;
    private Checkout checkout;

    public Application() {
        driver = new ChromeDriver();
        product = new Product(driver);
        litecart = new Litecart(driver);
        checkout = new Checkout(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addToCart() {

        litecart.open();
        int count = getCount();
        Assert.assertTrue(litecart.column.isDisplayed());
        String productName = litecart.nameProduct(0);
        litecart.product.get(0).click();
        String pageName = product.open();
        Assert.assertEquals(productName, pageName);

        product.select(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        product.add_cart_product.click();
        int count2 = litecart.countProduct();
        while (count2 < count + 1) {
            count2 = litecart.countProduct();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
    }

    public void removeFromCart(int count) {

        checkout.open();
        Assert.assertEquals(Integer.parseInt(checkout.quantity.getText()), count);
        checkout.cart.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        count = checkout.item.size();
        while (count != 0) {
            List<WebElement> ducks = checkout.getItem();
            checkout.remove.click();
            wait.until(ExpectedConditions.stalenessOf(ducks.get(count - 1)));
            count = checkout.item.size();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
        litecart.open();
    }

    public Integer getCount() {
        return litecart.countProduct();
    }
}
