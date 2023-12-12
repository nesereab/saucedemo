package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class pageObjects {
    // User-name field
    public static WebElement usernameField(WebDriver driver) {
        return driver.findElement(By.id("user-name"));// user-name field
    }
    public static WebElement passwordField(WebDriver driver) {
        return driver.findElement(By.id("password"));// user-name field
    }
    public static WebElement loginButton(WebDriver driver) {
        return driver.findElement(By.id("login-button"));// user-name field
    }
    public static WebElement shoppingCart(WebDriver driver) {
        return driver.findElement(By.id("shopping_cart_container"));// user-name field
    }
    public static WebElement loginErrorMessage(WebDriver driver) {
        return driver.findElement(By.xpath("//h3[@data-test='error']"));// user-name field
    }
    public static WebElement addItemToCart(WebDriver driver) {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));// user-name field
    }
    public static WebElement checkoutButton(WebDriver driver) {
        return driver.findElement(By.id("checkout"));// user-name field
    }

}
