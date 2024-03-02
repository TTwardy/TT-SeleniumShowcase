import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class RegistrationPage extends BasePage {
    private WebDriver driver;
    String randomPassword;
    String email;
    Random random = new Random();

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "ico-register")
    private WebElement registerLink;
    @FindBy(id = "FirstName")
    private WebElement firstName;
    @FindBy(id = "LastName")
    private WebElement lastName;
    @FindBy(id = "Email")
    private WebElement emailField;
    @FindBy(id = "Password")
    private WebElement password;
    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPassword;

    public void clickRegister() {
        registerLink.click();
    }

    public void chooseGender() {
        WebElement radioMale = find(By.xpath("//input[@value = 'M']"));
        WebElement radioFemale = find(By.xpath("//input[@value = 'F']"));
        if (random.nextInt(2) == 0) {
            radioMale.click();
        } else {
            radioFemale.click();
        }
    }

    public void provideFirstName() {
        firstName.sendKeys(RandomStringUtils.randomAlphabetic(1, 10));
    }

    public void provideLastName() {
        lastName.sendKeys(RandomStringUtils.randomAlphabetic(1, 10));
    }

    public void setBirthDate() {//improve day selection, even though site allows it
        Select day = new Select(find(By.name("DateOfBirthDay")));
        Select month = new Select(find(By.name("DateOfBirthMonth")));
        Select year = new Select(find(By.name("DateOfBirthYear")));

        day.selectByValue(String.valueOf(random.nextInt(32)));
        month.selectByValue(String.valueOf(random.nextInt(13)));
        year.selectByValue(String.valueOf(random.nextInt(1950, 2011)));
    }

    public void provideEmail() {
        email = RandomStringUtils.randomAlphabetic(1, 5) + "@test.com";
        emailField.sendKeys(email);
    }

    public void providePassword() {
        randomPassword = RandomStringUtils.randomAlphabetic(6, 15);

        password.sendKeys(randomPassword);
        confirmPassword.sendKeys(randomPassword);
    }

    public void clickRegisterButton() {
        find(By.id("register-button")).click();
    }

    public void showUserLoginData() {
        System.out.println("Email: " + email);
        System.out.println("Password: " + randomPassword);
    }

    public String[] getUserLoginData() {
        String[] credentials = new String[2];
        credentials[0] = email;
        credentials[1] = randomPassword;
        return credentials;
    }

    public String[] registerUser() {
        clickRegister();
        chooseGender();
        provideFirstName();
        provideLastName();
        setBirthDate();
        provideEmail();
        providePassword();
        clickRegisterButton();
        return getUserLoginData();
    }

    public void logIn(String[] credentials) {
        click(By.cssSelector("a[href= '/login?returnUrl=%2F']"));
        type(By.className("email"), credentials[0]);
        type(By.className("password"), credentials[1]);
        click(By.cssSelector("[class= 'button-1 login-button']"));

    }
}
