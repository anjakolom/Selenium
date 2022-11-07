import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public class Zadanie_10 extends TestBase {

    @Test
    public void productTest() {

        driver.get("http://localhost/litecart/en/");
        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("li.product.column.shadow.hover-light")));
        List<WebElement> ducks = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light a.link"));

        //Проверка первого на странице продукта
        String productName = ducks.get(0).getAttribute("title").trim();
        ducks.get(0).click();

        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("h1.title")));
        String pageName = driver.findElement(By.cssSelector("h1.title")).getAttribute("innerText").trim();
        Assert.assertEquals(productName, pageName);

        //Проверки в блоке Campaign
        driver.get("http://localhost/litecart/en/");

        String page = "#box-campaigns li.product.column.shadow.hover-light a.link";
        String campaignPrice = page + " strong.campaign-price";
        String regularPrice = page + " s.regular-price";

        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector(page)));

        productName = driver.findElement(By.cssSelector(page+" div.name")).getAttribute("innerText").trim();

        int[] rgb = style.getArray(style.getStyle(driver, regularPrice));
        Assert.assertTrue(style.grayColor(rgb)); //Проверка на серый цвет шрифта
        Assert.assertFalse(style.redColor(rgb)); //Проверка на красный цвет шрифта
        Assert.assertTrue(style.getStyleLineThrough(driver, regularPrice)); //Проверка на зачеркнутый текст

        int[] rgb2 = style.getArray(style.getStyle(driver, campaignPrice));
        Assert.assertFalse(style.grayColor(rgb2)); //Проверка на серый цвет шрифта
        Assert.assertTrue(style.redColor(rgb2)); //Проверка на красный цвет шрифта
        Assert.assertFalse(style.getStyleLineThrough(driver, campaignPrice)); //Проверка на зачеркнутый текст
        Assert.assertTrue(style.getSize(driver, regularPrice) < style.getSize(driver, campaignPrice));  //Проверка на размер шрифта

        float regularPriceInt = helper.getPrice(driver.findElement(By.cssSelector(regularPrice)));
        float campaignPriceInt = helper.getPrice(driver.findElement(By.cssSelector(campaignPrice)));

        //Проверки на странице продукта Campaign
        String page2 = "div.price-wrapper";
        String campaignPricePage = page2 + " strong.campaign-price";
        String regularPricePage = page2 + " s.regular-price";

        driver.findElement(By.cssSelector(page)).click();
        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector("h1.title")));

        pageName = driver.findElement(By.cssSelector("h1.title")).getAttribute("innerText").trim();
        Assert.assertEquals(productName,pageName);

        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector(regularPricePage)));
        Assert.assertTrue(helper.isElementPresent(driver, By.cssSelector(campaignPricePage)));

        rgb = style.getArray(style.getStyle(driver, regularPricePage));
        Assert.assertTrue(style.grayColor(rgb)); //Проверка на серый цвет шрифта
        Assert.assertFalse(style.redColor(rgb)); //Проверка на красный цвет шрифта
        Assert.assertTrue(style.getStyleLineThrough(driver, regularPricePage)); //Проверка на зачеркнутый текст

        rgb2 = style.getArray(style.getStyle(driver, campaignPricePage));
        Assert.assertFalse(style.grayColor(rgb2)); //Проверка на серый цвет шрифта
        Assert.assertTrue(style.redColor(rgb2)); //Проверка на красный цвет шрифта
        Assert.assertFalse(style.getStyleLineThrough(driver, campaignPricePage)); //Проверка на зачеркнутый текст
        Assert.assertTrue(style.getSize(driver, regularPricePage) < style.getSize(driver, campaignPricePage));  //Проверка на размер шрифта

        float regularPricePageInt = helper.getPrice(driver.findElement(By.cssSelector(regularPricePage)));
        float campaignPricePageInt = helper.getPrice(driver.findElement(By.cssSelector(campaignPricePage)));

        Assert.assertTrue(regularPriceInt==regularPricePageInt);
        Assert.assertTrue(campaignPriceInt==campaignPricePageInt);

    }


}
