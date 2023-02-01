import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Zadanie_12 extends TestBase{
    @Test
    public void addNewProduct(){
        long now = System.currentTimeMillis();
        String name_duck = String.format("Duck-%s", Long.toString(now).substring(1,4));
        driver.get("http://localhost/litecart/admin/login.php");
        helper.login("admin", "admin", driver);
        List<WebElement> menu = driver.findElements(By.cssSelector("#app- > a > span.name"));
        List<String> a = helper.getMenu(menu);
        int i = a.indexOf("Catalog");
        menu.get(i).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#content > h1")).getText().trim(),"Catalog");
        driver.findElement(By.linkText("Add New Product")).click();

        /*General*/
        driver.findElement(By.cssSelector("input[name=status]")).click();
        helper.type(driver, By.cssSelector("[name=\"name[en]\"]"), name_duck );
        helper.type(driver, By.cssSelector("[name=code]"), "cod-1" );
        List<WebElement> categories = driver.findElements(By.cssSelector("[name=\"categories[]\"]"));
        categories.get(1).click();
        categories.get(2).click();
        driver.findElements(By.cssSelector("[name=\"product_groups[]\"]")).get(0).click();
        driver.findElement(By.cssSelector("[name=quantity]")).sendKeys("9"+ Keys.TAB);
        WebElement web = driver.findElement(By.cssSelector("input[name=\"new_images[]\"]"));
        driver.findElement(By.cssSelector("input[name=\"new_images[]\"]")).sendKeys(System.getProperty("user.dir")+"\\src\\test\\resources\\Duck.jpg");
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        driver.findElement(By.cssSelector("input[name=\"date_valid_from\"]")).sendKeys(formater.format(new Date()));
        driver.findElement(By.cssSelector("input[name=\"date_valid_to\"]")).sendKeys(formater.format(new Date()));

        /*Information*/
        driver.findElement(By.cssSelector("form > div > ul > li:nth-child(2)")).click();
        Select selectManufacturer = new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id]")));
        selectManufacturer.selectByIndex(0);
        Select selectSupplier = new Select(driver.findElement(By.cssSelector("select[name=supplier_id]")));
        selectSupplier.selectByIndex(0);
        driver.findElement(By.cssSelector("input[name=keywords]")).sendKeys("keywords");
        driver.findElement(By.cssSelector("input[name=\"short_description[en]\"]")).sendKeys("Short description");
        driver.findElement(By.cssSelector("div[class=trumbowyg-editor]")).sendKeys("Description Description Description"+Keys.TAB);
        driver.findElement(By.cssSelector("input[name=\"head_title[en]\"]")).sendKeys("head_title head_title head_title"+Keys.TAB);
        driver.findElement(By.cssSelector("input[name=\"meta_description[en]\"]")).sendKeys("meta_description meta_description meta_description"+Keys.TAB);

        /*Prices*/
        driver.findElement(By.cssSelector("form > div > ul > li:nth-child(4)")).click();
        driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys("99"+Keys.TAB);
        Select selectPurchase_price_currency_code = new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
        selectPurchase_price_currency_code.selectByIndex(1);

        driver.findElement(By.cssSelector("button[name=save]")).click();
        Assert.assertTrue(helper.isElementPresent(driver,By.linkText(name_duck)));
        System.out.println("");

    }
}
