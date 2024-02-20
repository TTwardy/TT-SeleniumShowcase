import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CheckoutPage {
    private WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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
        email.sendKeys(RandomStringUtils.randomAlphabetic(1,5)+"@test");
    }
    public void chooseCountry() {
        Select country = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
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
        driver.findElement(By.xpath("//button[@class = 'button-1 new-address-next-step-button']")).click();
    }
    public void clickContinueShipping() {
        driver.findElement(By.xpath("//button[@class = 'button-1 shipping-method-next-step-button']")).click();
    }
    public void clickContinuePaymentMethod() {
        driver.findElement(By.xpath("//button[@class = 'button-1 payment-method-next-step-button']")).click();
    }
    public void clickContinuePaymentInfo() {
        driver.findElement(By.xpath("//button[@class = 'button-1 payment-info-next-step-button']")).click();
    }
    public void clickContinueConfirmOrder() {
        driver.findElement(By.xpath("//button[@class = 'button-1 confirm-order-next-step-button']")).click();
    }
    public void compareTotals() {
        String productTotal = driver.findElement(By.xpath("//span[@class = 'product-subtotal']")).getText();
        String orderTotal =driver.findElement(By.xpath("//span[@class = 'value-summary']")).getText();
//        productTotal = productTotal.replace("$","");
//        orderTotal = orderTotal.replace("$","");
        assertEquals(productTotal,orderTotal);
    }


}
