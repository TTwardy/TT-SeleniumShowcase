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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

class OrdersTest {

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().setSize(dimension);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void basicGuestBookOrder() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement books = driver.findElement(By.cssSelector("[href= '/books']"));
        actions.moveToElement(books).click().build().perform();
        cartPage.click(By.xpath("//div[@class = 'item-grid']/div[3]//a[@title = 'Show details for Pride and Prejudice']"));
        cartPage.click(By.id("add-to-cart-button-39"));
        cartPage.waitForVisibility(By.xpath("//div[@class = 'bar-notification success' and @style = 'display: block;']"));
        cartPage.click(By.cssSelector(".close"));
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
        checkoutPage.provideBillingAddress();
        checkoutPage.clickContinueAddress();
        checkoutPage.clickContinueShipping();
        checkoutPage.clickContinuePaymentMethod();
        checkoutPage.clickContinuePaymentInfo();
        checkoutPage.compareTotals();
        checkoutPage.clickContinueConfirmOrder();
        checkoutPage.successfullOrderConfirmation();
    }

    public void RegisteredUserOrder() {
        driver.get("https://demo.nopcommerce.com/");
        WebElement computersTab = driver.findElement(By.cssSelector("[href= '/computers']"));
        actions.moveToElement(computersTab).click().build().perform(); //add mouse hover and click Notebooks directly
        cartPage.click(By.cssSelector("[href = '/notebooks']"));
    }
}