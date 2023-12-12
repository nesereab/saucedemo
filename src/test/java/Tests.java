import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import pageObjects.pageObjects;
import testData.testData;

import java.time.Duration;

public class All_Tests {

    WebDriver driver;
    WebDriverWait wait;


    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
    }
    @BeforeMethod
    public void beforeTest(){
        driver.navigate().to(testData.URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));

    }
    @Test
    public void testAccessWeb() {
        String verifyBrowserStackTitle = driver.getTitle();
        System.out.println("Title: " +verifyBrowserStackTitle);
        assertEquals("Swag Labs",verifyBrowserStackTitle);
    }

    @Test
    public void testPositiveLogin() {
        pageObjects.usernameField(driver).sendKeys(testData.standard_user);
        pageObjects.passwordField(driver).sendKeys(testData.password);
        pageObjects.loginButton(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.shoppingCart(driver)));
        assertTrue(pageObjects.shoppingCart(driver).isDisplayed());
    }
    @Test
    public void testNegativeLogin() throws InterruptedException {
        String expectedErrorMessage = "Sorry, this user has been locked out.";
        pageObjects.usernameField(driver).sendKeys(testData.locked_out_user);
        pageObjects.passwordField(driver).sendKeys(testData.password);
        pageObjects.loginButton(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.loginErrorMessage(driver)));
        String actualErrorMessage = pageObjects.loginErrorMessage(driver).getText();
        assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    @Test
    public void testAddItemToBasket() throws InterruptedException {
        pageObjects.usernameField(driver).sendKeys(testData.standard_user);
        pageObjects.passwordField(driver).sendKeys(testData.password);
        pageObjects.loginButton(driver).click();
        pageObjects.addItemToCart(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.shoppingCart(driver)));
        String shoppingCart = pageObjects.shoppingCart(driver).getText();
        assertTrue(shoppingCart.contains("1"));
    }

    @Test
    public void testProceedToCheckout() throws InterruptedException {
        pageObjects.usernameField(driver).sendKeys(testData.standard_user);
        pageObjects.passwordField(driver).sendKeys(testData.password);
        pageObjects.loginButton(driver).click();
        pageObjects.addItemToCart(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.shoppingCart(driver)));
        pageObjects.shoppingCart(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.checkoutButton(driver)));
        assertTrue(pageObjects.checkoutButton(driver).isDisplayed());
    }

    @Test
    public void testCheckout() throws InterruptedException {
        pageObjects.usernameField(driver).sendKeys(testData.standard_user);
        pageObjects.passwordField(driver).sendKeys(testData.password);
        pageObjects.loginButton(driver).click();
        pageObjects.addItemToCart(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.shoppingCart(driver)));
        pageObjects.shoppingCart(driver).click();
        pageObjects.checkoutButton(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.firstNameField(driver)));
        pageObjects.firstNameField(driver).sendKeys(testData.first_name);
        pageObjects.lastNameField(driver).sendKeys(testData.last_name);
        pageObjects.postCodeField(driver).sendKeys(testData.post_code);
        pageObjects.continueButton(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.finishButton(driver)));
        pageObjects.finishButton(driver).click();
        wait.until(ExpectedConditions.visibilityOf(pageObjects.thankYouHeaderMessage(driver)));
        String checkOutCompleteHeader = pageObjects.thankYouHeaderMessage(driver).getText();
        System.out.println(checkOutCompleteHeader);
        assertTrue(checkOutCompleteHeader.contains("Thank you for your order!"));
    }

    @AfterMethod
    public void afterTest(){
        driver.close();
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}