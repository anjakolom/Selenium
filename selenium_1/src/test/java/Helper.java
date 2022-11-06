import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement webElement, By locator) {
        try {
            webElement.findElement(locator);
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

}
