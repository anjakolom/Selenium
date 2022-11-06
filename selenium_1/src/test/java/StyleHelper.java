import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StyleHelper {
    public  String getStyle(WebDriver driver, String cssPrice) {
        return driver.findElement(By.cssSelector(cssPrice)).getCssValue("text-decoration").trim();
    }

    public  String getSize(WebDriver driver, String cssPrice) {
        return driver.findElement(By.cssSelector(cssPrice)).getSize().toString().trim();
    }

    public  boolean getStyleLineThrough(WebDriver driver, String cssPrice) {
        String textDecoration = driver.findElement(By.cssSelector(cssPrice)).getCssValue("text-decoration").trim();
        return textDecoration.contains("line-through");
    }

    public int[] getArray(String str){
        int i = str.indexOf("(");
        int j = str.indexOf(")");
        String str2 = str.substring(i+1,j);
        String[] words = str2.split(", ");
        int[] i2 = new int[3];
        i = 0;
        for (String word : words) {
            System.out.println(word);
            try {
                i2[i] = Integer.valueOf(word);
            }catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
            }
            i=i+1;
        }
        return  i2;
    }

    public boolean grayColor(int[] rgb){
        int expected = rgb[0];
        for (int i : rgb) {
            if (i!=expected){
                return false;
            }
        }
        return true;
    }

    public boolean redColor(int[] rgb){
        if (rgb[0]==0){
            return false;
        };
        for (int i=1; i< rgb.length-1;i++) {
            if (rgb[i]!=0){
                return false;
            }
        }
        return true;
    }


}
