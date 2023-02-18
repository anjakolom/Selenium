package Zadanie_19.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Checkout extends Page{
    public Checkout(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/create_account");
    }

    @FindBy(css = "span.quantity")
    public WebElement quantity;  //Количество в корзине

    @FindBy(css = "#cart a.link")
    public WebElement cart;

    @FindBy(css = "td.item")
    public List<WebElement> item;

    @FindBy(css = "button[name=remove_cart_item]")
    public WebElement remove;

    public List<WebElement> getItem() {
        return item;
    }
}
