import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = ".cart-label")
    private WebElement cartLink;

    @FindBy(xpath = "//button[@class = 'ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-close']")
    private WebElement closePopupButton;

    @FindBy(id = "termsofservice")
    private WebElement tosCheckbox;


    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void clickCartLink() {
        cartLink.click();
    }

    public void clickClosePopupButton() {
        closePopupButton.click();
    }

    public void markTosCheckbox() {
        tosCheckbox.click();
    }

    public void checkTosAlert() {
        String termsText = driver.findElement(By.xpath("//div[@id = 'terms-of-service-warning-box']/p")).getText();
        assertEquals("Please accept the terms of service before the next step.",termsText);
    }
}

