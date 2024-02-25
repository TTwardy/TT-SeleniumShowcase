import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrdersTest{

    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    Dimension dimension = new Dimension(1800, 1000);
    Actions actions;


    @BeforeEach
    void setup() {
        options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().window().setSize(dimension);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
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
        driver.findElement(By.cssSelector(".close")).click();
        //cart
        cartPage.clickCartLink();
        cartPage.clickCheckoutButton();
        cartPage.checkTosAlert();
        cartPage.clickClosePopupButton();
        cartPage.markTosCheckbox();
        cartPage.clickCheckoutButton();
        //login screen
        checkoutPage.click(By.xpath("//button[@class = 'button-1 checkout-as-guest-button']"));
        //checkout screen
        checkoutPage.provideFirstName();
        checkoutPage.provideLastName();
        checkoutPage.provideEmail();
        checkoutPage.chooseCountry();
        checkoutPage.provideCity();
        checkoutPage.provideZip();
        checkoutPage.provideAddress1();
        checkoutPage.providePhoneNo();
        checkoutPage.clickContinueAddress();
        checkoutPage.clickContinueShipping();
        checkoutPage.clickContinuePaymentMethod();
        checkoutPage.clickContinuePaymentInfo();
        checkoutPage.compareTotals();
        checkoutPage.clickContinueConfirmOrder();
        checkoutPage.successfullOrderConfirmation();
    }
}