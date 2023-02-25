package cucumber;

import io.cucumber.java8.En;
import org.junit.Assert;

public class Steps extends CucumberTestBase implements En {

    public Steps() {

        When("^We add (\\d+) items to the cart$", (Integer count) -> {
            for (int i = 0; i < count; i++) {
                app.addToCart();
            }
        });
        When("^We are removing (\\d+) items from the cart$", (Integer count) -> {
            app.removeFromCart(count);
        });
        Then("^check that the number of items in the cart (\\d+)$", (Integer count) -> {
            Assert.assertEquals(count, app.getCount());
        });

    }
}
