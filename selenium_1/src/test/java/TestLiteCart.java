import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public class TestLiteCart extends TestBase{

    @Test
    public void stickerTest(){

        driver.get("http://localhost/litecart/en/");
        if (helper.isElementPresent(driver, By.cssSelector("li.product"))) {
            List<WebElement> ducks = driver.findElements(By.cssSelector("li.product"));
            for (int i=0; i<ducks.size(); i++) {
                Collection<WebElement> element = ducks.get(i).findElements(By.cssSelector("div.sticker"));
                Assert.assertEquals(1,element.size());
            }
        }
    }

}
