import org.junit.Test;

public class Zadanie_13 extends TestBase {

    @Test
    public void addProductToCart() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            product.addToCart(driver);
        }
        product.removeFromCart(driver,count);
    }


}
