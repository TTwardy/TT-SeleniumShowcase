import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage(WebDriver driver, int timeOut) {
        super(driver);
        setTimeoutSec(timeOut);
    }

    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement firstName;
    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement lastName;
    @FindBy(id = "BillingNewAddress_Email")
    private WebElement email;
    @FindBy(id = "BillingNewAddress_City")
    private WebElement city;
    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement address1;
    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement zip;
    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement phoneNumber;


    public void provideFirstName() {
        firstName.sendKeys(RandomStringUtils.randomAlphabetic(1,10));
    }
    public void provideLastName() {
        lastName.sendKeys(RandomStringUtils.randomAlphabetic(1,10));
    }
    public void provideEmail() {
        email.sendKeys(RandomStringUtils.randomAlphabetic(1,5)+"@test.com");
    }
    public void chooseCountry() {
        Select country = new Select(find(By.id("BillingNewAddress_CountryId")));
        int index = RandomUtils.nextInt(0, country.getOptions().size());
        country.selectByIndex(index);
    }
    public void provideCity() {
        city.sendKeys(RandomStringUtils.randomAlphabetic(1,5));
    }
    public void provideAddress1() {
        address1.sendKeys(RandomStringUtils.randomAlphabetic(10));
    }
    public void provideZip() {
        zip.sendKeys(RandomStringUtils.randomAlphabetic(1,5));
    }
    public void providePhoneNo() {
        phoneNumber.sendKeys(RandomStringUtils.randomNumeric(10));
    }
    public void clickContinueAddress() {
        click(By.xpath("//button[@class = 'button-1 new-address-next-step-button']"));
    }
    public void clickContinueShipping() {
        click(By.xpath("//button[@class = 'button-1 shipping-method-next-step-button']"));
    }
    public void clickContinuePaymentMethod() {
        click(By.xpath("//button[@class = 'button-1 payment-method-next-step-button']"));
    }
    public void clickContinuePaymentInfo() {
        click(By.xpath("//button[@class = 'button-1 payment-info-next-step-button']"));
    }
    public void clickContinueConfirmOrder() {
        click(By.xpath("//button[@class = 'button-1 confirm-order-next-step-button']"));
    }
    public void compareTotals() {
        String productTotal = find(By.xpath("//span[@class = 'product-subtotal']")).getText();
        String orderTotal =find(By.xpath("//span[@class = 'value-summary']")).getText();
        assertEquals(productTotal,orderTotal);
    }
    public void successfullOrderConfirmation() {
        assertTrue(isDisplayed(By.xpath("//div [@class = 'page checkout-page order-completed-page']")));

    }

}
