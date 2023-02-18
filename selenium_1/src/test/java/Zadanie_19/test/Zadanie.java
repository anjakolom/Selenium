package Zadanie_19.test;

import org.junit.Test;

public class Zadanie extends TestBase{

    @Test
    public void addProductToCart19() {
        int count = 3;
        for (int i = 0; i < count; i++) {
            app.addToCart();
        }
        app.removeFromCart(count);
    }


}
