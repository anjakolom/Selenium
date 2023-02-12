import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Helper {

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public List<String> getMenu(List<WebElement> menu) {
        List<String> a = new ArrayList<>();
        for (WebElement i : menu) {
            a.add(i.getText());
        }
        return a;
    }

    public void login(String login, String password, WebDriver driver) {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public float getPrice(WebElement webElement) {

        String str = webElement.getAttribute("innerText").trim();
        if (str.contains("$")) {
            int i = str.indexOf("$");
            str = webElement.getAttribute("innerText").trim().substring(i + 1);
        }

        float price = 0;
        try {
            price = Float.valueOf(str);
            // Integer.valueOf(str);
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }
        return price;
    }

    protected void click(WebDriver wd, By locator) {
        wd.findElement(locator).click();

    }

    protected void type(WebDriver wd, By locator, String text) {
        click(wd, locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void select(WebDriver wd, By locator, String text) {
        new Select(wd.findElement(locator)).selectByVisibleText(text);

    }

    public void logoutAccount(WebDriver driver) {
        click(driver, By.linkText("Logout"));
        isElementPresent(driver, By.id("notices"));
        Assert.assertEquals(driver.findElement(By.id("notices")).getText(), "You are now logged out.");
    }

    public void loginAccount(WebDriver driver, String email, String password) {
        type(driver, By.cssSelector("[name=email]"), email);
        type(driver, By.cssSelector("[name=password]"), password);
        click(driver, By.cssSelector("button[name=login]"));
    }
    public void checkSort(List<String> arrayCountries) {
        List<String> arrayCountriesExpected = new ArrayList<String>(arrayCountries);
        Collections.sort(arrayCountriesExpected);
        Assert.assertEquals(arrayCountriesExpected, arrayCountries);
    }
    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition <String>(){
            public String apply(WebDriver driver){
                Set <String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next():null;
            }
        };
    }
}
