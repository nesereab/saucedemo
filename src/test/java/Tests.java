//package saucedemo;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BuildingSeleniumProject {

    @Test
    public void buildingTestMethod() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        String verifyBrowserStackTitle = driver.getTitle();
        assertEquals("Most Reliable App & Cross Browser Testing Platform | BrowserStack",verifyBrowserStackTitle);
    }
}