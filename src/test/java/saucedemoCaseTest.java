import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class saucedemoCaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "https://www.saucedemo.com/";
    private final String USERNAME = "standard_user";
    private final String PASSWORD = "secret_sauce";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.get(BASE_URL);
            driver.quit();
        }
    }

    @Test
    public void testLoginFlow() {
        System.out.println(" Mulai Tes Nih ");

        testSwagLabsText();
        testUsernameFieldExists();
        testClearUsernameField();
        testFillUsernameField();
        testClearPasswordField();
        testFillPasswordField();
        testLoginButtonExists();
        testClickLoginButton();

        System.out.println(" Done, Aman! ");
    }

    private void testSwagLabsText() {
        System.out.println("\n[Test 1] Cek logo dulu ya pake fungsi text() di XPath...");
        WebElement logo = driver.findElement(By.xpath("//div[text()='Swag Labs']"));
        assertNotNull(logo);
        System.out.println("✓ Done bang");
    }

    private void testUsernameFieldExists() {
        System.out.println("\n[Test 2] Liat field username-nya ada kaga, pake axis descendant...");
        WebElement usernameField = driver.findElement(By.xpath("//div[@id='login_button_container']//descendant::input[@name='user-name']"));
        assertNotNull(usernameField);
        System.out.println("✓ Done bang");
    }

    private void testClearUsernameField() {
        System.out.println("\n[Test 3] Kosongin dulu kolom username-nya pake By.name...");
        WebElement usernameField = driver.findElement(By.name("user-name"));
        usernameField.clear();
        System.out.println("✓ Done bang");
    }

    private void testFillUsernameField() {
        System.out.println("\n[Test 4] Isi username-nya, nyarinya pake contains()...");
        WebElement usernameField = driver.findElement(By.xpath("//input[contains(@id, 'user-name')]"));
        usernameField.sendKeys(USERNAME);
        System.out.println("✓ Done bang");
    }

    private void testPasswordFieldExists() {
        System.out.println("\n[Test 5] Cek field password pake axis following...");
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='user-name']/following::input[@id='password']"));
        assertNotNull(passwordField);
        System.out.println("✓ Done bang");
    }

    private void testClearPasswordField() {
        System.out.println("\n[Test 6] Bersihin kolom password pake By.tagName...");
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement passwordField = inputs.get(1); 
        passwordField.clear();
        System.out.println("✓ Done bang");
    }

    private void testFillPasswordField() {
        System.out.println("\n[Test 7] Masukin password-nya pake atribut XPath...");
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        passwordField.sendKeys(PASSWORD);
        System.out.println("✓ Done bang");
    }

    private void testLoginButtonExists() {
        System.out.println("\n[Test 8] Cek tombol login-nya ada apa kaga pake By.className...");
        WebElement loginButton = driver.findElement(By.className("submit-button"));
        assertNotNull(loginButton);
        System.out.println("✓ Done bang");
    }

    private void testClickLoginButton() {
        System.out.println("\n[Test 9] Gass klik tombol login pake kondisi XPath yang rame...");
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Login']"));
        loginButton.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'inventory_container')]")));
        System.out.println("✓ Done bang: Login Berhasil");
    }
}
