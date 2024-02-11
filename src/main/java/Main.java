import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        Scanner scanner = new Scanner(System.in);
        Actions actions = new Actions(driver);
        Dimension dimension = new Dimension(1800, 1000);
        driver.manage().window().setSize(dimension);


        try {
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
            driver.quit();
        }

        catch (Exception NoSuchElementException){
            System.out.println("ERROR, quitting\n " + NoSuchElementException.getMessage());
            NoSuchElementException.printStackTrace();
            driver.quit();
        }
        finally {
            System.out.println("press key to finish");
            scanner.next();
            driver.quit();
        }
    }
}
