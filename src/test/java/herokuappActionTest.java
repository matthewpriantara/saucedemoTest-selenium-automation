import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class herokuappActionTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @Test
    public void testHover() {
        // Navigate to hovers page
        driver.get("https://the-internet.herokuapp.com/hovers");
        
        // Find the first image
        WebElement firstImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@alt='User Avatar'])[1]")));
        
        // Hover over the first image
        actions.moveToElement(firstImage).perform();
        
        // Wait for the name text to appear and verify
        WebElement nameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h5[contains(text(), 'name:')])[1]")));
        
        assertEquals("name: user1", nameText.getText(), "Name should be 'name: user1'");
    }

    @Test
    public void testKeyPresses() {
        // Navigate to key presses page
        driver.get("https://the-internet.herokuapp.com/key_presses");
        
        // Find the input field
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("target")));
        
        // Press SHIFT key
        actions.sendKeys(inputField, Keys.SHIFT).perform();
        
        // Wait for result text and verify
        WebElement resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        
        assertEquals("You entered: SHIFT", resultText.getText(), "Text should be 'You entered: SHIFT'");
    }

    @Test
    public void testDragAndDrop() {
        // Navigate to drag and drop page
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        
        // Find box A and box B
        WebElement boxA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        WebElement boxB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));
        
        // Get original text
        String originalAText = boxA.getText();
        String originalBText = boxB.getText();
        
        // Drag box A to box B
        actions.dragAndDrop(boxA, boxB).perform();
        
        // Verify that boxes have switched
        String newAText = boxA.getText();
        String newBText = boxB.getText();
        
        assertEquals(originalBText, newAText, "Box A should now have Box B's content");
        assertEquals(originalAText, newBText, "Box B should now have Box A's content");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
