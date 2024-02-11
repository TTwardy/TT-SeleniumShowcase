import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class Orders {

    WebDriver driver;
    WebDriverWait wait;
    Dimension dimension = new Dimension(1800, 1000);
    Actions actions;


    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().window().setSize(dimension);
    }



    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void basicBookOrder() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement books = driver.findElement(By.cssSelector("[href= '/books']"));
        //wait.until(ExpectedConditions.elementToBeClickable(By.linkText("/books")));
        actions.moveToElement(books).click().build().perform();
        //driver.findElements(By.linkText("/books")).get(0).click();
        driver.findElement(By.xpath("//div[@class = 'item-grid']/div[3]//a[@title = 'Show details for Pride and Prejudice']")).click();
        driver.findElement(By.id("add-to-cart-button-39")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'bar-notification success' and @style = 'display: block;']")));
        driver.findElement(By.cssSelector("[class=close]")).click();
        //cart
        driver.findElement(By.cssSelector("[class=cart-label]")).click();
        driver.findElement(By.id("checkout")).click();
        String termsText = driver.findElement(By.xpath("//div[@id = 'terms-of-service-warning-box']/p")).getText();
        assertEquals("Please accept the terms of service before the next step.",termsText);

    }
}