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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class waitStrategiesTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCase1() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
        addBtn.click();
        
        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 input")));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be displayed");
    }

    @Test
    public void testCase5() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        // Using 6 seconds wait to prevent TimeoutException since row 2 takes ~5 seconds to load.
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
        addBtn.click();
        
        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 input")));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be displayed");
    }

    @Test
    public void testCase2() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
        addBtn.click();
        
        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 input")));
        row2Input.sendKeys("Burger");
        
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='row2']/button[@name='Save']")));
        saveBtn.click();
        
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        assertEquals("Row 2 was saved", confirmationMessage.getText());
    }

    @Test
    public void testCase3() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit_btn")));
        editBtn.click();
        
        WebElement row1Input = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#row1 input")));
        row1Input.clear();
        row1Input.sendKeys("Pizza");
        
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='row1']/button[@name='Save']")));
        saveBtn.click();

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        assertEquals("Row 1 was saved", confirmationMessage.getText());
    }

    @Test
    public void testCase4() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement instructions = driver.findElement(By.id("instructions"));
        
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
        addBtn.click();
        
        assertTrue(wait.until(ExpectedConditions.invisibilityOf(instructions)), "Instructions should be invisible");
    }
}