package Zadanie_19.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Product extends Page {
    public Product(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "h1.title")
    public WebElement title;

    @FindBy(css = "select")
    public List<WebElement> select;

    @FindBy(css = "button[name=add_cart_product]")
    public WebElement add_cart_product;

    @FindBy(css = "span.quantity")
    public WebElement quantity;

    public String open() {
        return title.getAttribute("innerText").trim();
    }

    public void select(int i) {

        if (select.size() > 0) {
            Select sel = new Select(select.get(0));
            sel.selectByIndex(i);
        }
    }
}
