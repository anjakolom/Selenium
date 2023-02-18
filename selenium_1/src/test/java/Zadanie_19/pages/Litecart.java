package Zadanie_19.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Litecart extends Page {
    public Litecart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    @FindBy(css = "span.quantity")
    public WebElement quantity;  //Количество в корзине
    @FindBy(css = "li.product.column.shadow.hover-light")
    public WebElement column;

    @FindBy(css = "li.product.column.shadow.hover-light a.link")
    public List<WebElement> product;

    public int countProduct() {
        return Integer.parseInt(quantity.getText());
    }

    public String nameProduct(int i) {
        return product.get(i).getAttribute("title").trim();
    }
}
