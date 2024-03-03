import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

class UserRegistrationTest {
    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options;
    RegistrationPage registrationPage;
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
        registrationPage = new RegistrationPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void userRegistration() {
        driver.get("https://demo.nopcommerce.com/");
        registrationPage.clickRegister();
        registrationPage.chooseGender();
        registrationPage.provideFirstName();
        registrationPage.provideLastName();
        registrationPage.setBirthDate();
        registrationPage.provideEmail();
        registrationPage.providePassword();
        registrationPage.clickRegisterButton();
        registrationPage.showUserLoginData();
    }
}
