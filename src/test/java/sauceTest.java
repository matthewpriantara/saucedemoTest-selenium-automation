import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class sauceTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginValid() {
        driver.get("https://www.saucedemo.com/");

        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");

        loginButton.click();

        wait.until(ExpectedConditions.urlContains("inventory"));

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory"), "Login failed - not redirected to inventory page");
    }

    @Test
    public void testLoginInvalid() {
        driver.get("https://www.saucedemo.com/");

        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("invalid_user");
        passwordField.sendKeys("wrong_password");

        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3[data-test='error']")));

        assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
        assertTrue(errorMessage.getText().contains("Epic sadface"), "Expected error message not found");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
