import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Zadanie_11 extends TestBase {


    @Test
    public void registration() {
        driver.get("http://localhost/litecart/en/");
        helper.isElementPresent(driver, By.linkText("New customers click here"));
        driver.findElement(By.linkText("New customers click here")).click();

        helper.isElementPresent(driver, By.cssSelector("#create-account h1.title"));
        Assert.assertEquals(driver.findElement(By.cssSelector("#create-account h1.title")).getText().trim(), "Create Account");

        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", Long.toString(now));

        helper.type(driver, By.cssSelector("[name=lastname]"), "LastName-" + now);
        helper.type(driver, By.cssSelector("[name=firstname]"), "FirstName-" + now);
        helper.type(driver, By.cssSelector("[name=address1]"), "address1");
        helper.type(driver, By.cssSelector("[name=address2]"), "address2");
        helper.type(driver, By.cssSelector("[name=city]"), "city");
        helper.type(driver, By.cssSelector("[name=phone]"), "+79261233242");
        helper.type(driver, By.cssSelector("[name=password]"), "password");
        helper.type(driver, By.cssSelector("[name=confirmed_password]"), "password");
        helper.type(driver, By.cssSelector("[name=email]"), email);
        helper.isElementPresent(driver, By.cssSelector("[name=country_code]"));
        helper.select(driver, By.name("country_code"),"United States");
        helper.type(driver, By.cssSelector("[name=postcode]"), "35004");

        helper.isElementPresent(driver, By.cssSelector("[name=create_account]"));
        helper.click(driver, By.cssSelector("[name=create_account]"));

        helper.isElementPresent(driver, By.id("notices"));
        Assert.assertEquals(driver.findElement(By.id("notices")).getText(), "Your customer account has been created.");

        helper.click(driver, By.linkText("Logout"));

        helper.isElementPresent(driver, By.id("notices"));
        Assert.assertEquals(driver.findElement(By.id("notices")).getText(), "You are now logged out.");

        helper.type(driver, By.cssSelector("[name=email]"), email);
        helper.type(driver, By.cssSelector("[name=password]"), "password");
        helper.click(driver, By.cssSelector("button[name=login]"));

        Assert.assertEquals(driver.findElement(By.id("notices")).getText(), "You are now logged in as "+"FirstName-" + now+ " LastName-" + now+".");

        helper.click(driver, By.linkText("Logout"));
        helper.isElementPresent(driver, By.id("notices"));
        Assert.assertEquals(driver.findElement(By.id("notices")).getText(), "You are now logged out.");
        System.out.println();

    }


}