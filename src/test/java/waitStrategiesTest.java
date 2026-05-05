import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class waitStrategiesTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * TEST CASE 1 - NoSuchElementException: Row 2 tidak muncul instan
     * 
     * Deskripsi: Row 2 muncul setelah 5 detik setelah Add button diklik.
     * Tanpa wait yang tepat, akan throw NoSuchElementException
     * 
     * Exception yang muncul (tanpa wait):
     * - org.openqa.selenium.NoSuchElementException: no such element
     * 
     * Penjelasan exception:
     * - Row 2 belum ada di DOM saat Selenium mencoba menemukan element
     * - Element dibuat secara dinamis oleh JavaScript setelah delay 5 detik
     */
    @Test
    public void testCase1_WithoutWait() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();
        
        // Tanpa wait - akan throw NoSuchElementException
        try {
            WebElement row2Input = driver.findElement(By.cssSelector("#row2 input"));
            System.out.println("Test Case 1 (No Wait): Row 2 input found immediately");
        } catch (NoSuchElementException e) {
            System.out.println("\n=== TEST CASE 1 - EXCEPTION CAUGHT ===");
            System.out.println("Exception: " + e.getClass().getSimpleName());
            System.out.println("Message: no such element: unable to locate element");
            System.out.println("Reason: Row 2 belum ada di DOM (dibuat setelah 5 detik delay)");
        }
    }

    @Test
    public void testCase1_WithThreadSleep() throws InterruptedException {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();
        
        // Thread.sleep(6000) - menunggu 6 detik secara fixed
        Thread.sleep(6000);
        
        WebElement row2Input = driver.findElement(By.cssSelector("#row2 input"));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be displayed");
        System.out.println("Test Case 1 (Thread.sleep): PASSED - Row 2 input ditemukan");
    }

    @Test
    public void testCase1_WithImplicitWait() {
        // Implicit wait - berlaku global untuk semua element searches
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();
        
        // Driver akan menunggu hingga 10 detik untuk menemukan element di DOM
        WebElement row2Input = driver.findElement(By.cssSelector("#row2 input"));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be displayed");
        System.out.println("Test Case 1 (Implicit Wait): PASSED - Row 2 input ditemukan");
    }

    @Test
    public void testCase1_WithExplicitWait() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
        addBtn.click();
        
        // Explicit wait - tunggu sampai Row 2 input field visible dan dapat diklik
        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 input")));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be visible");
        System.out.println("Test Case 1 (Explicit Wait): PASSED - Row 2 input visible dan interactable");
    }

    /**
     * TEST CASE 5 - TimeoutException: Row 2 muncul setelah 5 detik, tapi timeout 3 detik
     * 
     * Deskripsi: Sama seperti Test Case 1, tapi dengan timeout yang tidak cukup (3 detik).
     * Row 2 butuh 5 detik untuk muncul, jadi akan throw TimeoutException dengan wait 3 detik
     * 
     * Exception yang muncul (dengan timeout 3 detik):
     * - org.openqa.selenium.TimeoutException: Expected condition failed
     * 
     * Penjelasan exception:
     * - Explicit wait menunggu kondisi spesifik (visibility) selama 3 detik
     * - Row 2 tidak muncul dalam 3 detik (butuh 5 detik)
     * - WebDriverWait melempar TimeoutException setelah waktu habis
     */
    @Test
    public void testCase5_WithTimeout_3Seconds() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();
        
        // Explicit wait dengan timeout 3 detik - TIDAK CUKUP untuk Row 2 yang butuh 5 detik
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        
        try {
            WebElement row2Input = shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 input")));
            System.out.println("Test Case 5 (3 sec timeout): Row 2 found");
        } catch (TimeoutException e) {
            System.out.println("\n=== TEST CASE 5 - TIMEOUT EXCEPTION CAUGHT ===");
            System.out.println("Exception: " + e.getClass().getSimpleName());
            System.out.println("Message: Expected condition failed (timeout after 3 seconds)");
            System.out.println("Reason: Row 2 membutuhkan 5 detik untuk muncul, tapi timeout hanya 3 detik");
        }
    }

    @Test
    public void testCase5_WithThreadSleep_5Seconds() throws InterruptedException {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();
        
        // Thread.sleep(5500) - menunggu 5.5 detik (fixed delay)
        Thread.sleep(5500);
        
        WebElement row2Input = driver.findElement(By.cssSelector("#row2 input"));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be displayed");
        System.out.println("Test Case 5 (Thread.sleep 5.5s): PASSED");
    }

    @Test
    public void testCase5_WithImplicitWait_10Seconds() {
        // Implicit wait 10 detik - berlaku global
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();
        
        // Tunggu Row 2 ada di DOM (tidak perlu visible, hanya presence)
        WebElement row2Input = driver.findElement(By.cssSelector("#row2 input"));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be displayed");
        System.out.println("Test Case 5 (Implicit Wait 10s): PASSED");
    }

    @Test
    public void testCase5_WithExplicitWait_10Seconds() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        
        // Explicit wait 10 detik - fokus pada kondisi spesifik
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
        addBtn.click();
        
        // Tunggu sampai Row 2 input field visible
        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 input")));
        assertTrue(row2Input.isDisplayed(), "Row 2 input should be visible");
        System.out.println("Test Case 5 (Explicit Wait 10s): PASSED");
    }

    /**
     * ANALISIS DAN PERBANDINGAN WAIT STRATEGIES:
     * 
     * ╔════════════════════════════════════════════════════════════════════════════════╗
     * ║                          PERBANDINGAN WAIT STRATEGIES                           ║
     * ╠═══════════════════╦════════════════════╦════════════════════╦════════════════╣
     * ║ Aspek             ║ Thread.sleep()     ║ Implicit Wait      ║ Explicit Wait  ║
     * ╠═══════════════════╬════════════════════╬════════════════════╬════════════════╣
     * ║ Tipe              ║ Fixed Delay        ║ Global Setting     ║ Kondisi Spesifik║
     * ║ Durasi            ║ ALWAYS Fixed       ║ Max duration       ║ Max duration   ║
     * ║ Berhenti Kapan    ║ Setelah delay      ║ Saat element ada   ║ Kondisi true   ║
     * ║ Scope             ║ Local              ║ Global             ║ Lokal          ║
     * ║ Performance       ║ LAMBAT             ║ Sedang             ║ CEPAT          ║
     * ║ Best Practice     ║ ❌ NO              ║ ⚠️  Sometimes      ║ ✅ YES         ║
     * ║ Use Case          ║ Debug only         ║ Simple elements    ║ Dynamic content║
     * ╠═══════════════════╩════════════════════╩════════════════════╩════════════════╣
     * ║ CONTOH TEST CASE 1 & 5 (Row 2 muncul setelah 5 detik):                        ║
     * ╠════════════════════════════════════════════════════════════════════════════════╣
     * ║                                                                                ║
     * ║ 1. Thread.sleep(6000)                                                         ║
     * ║    - SELALU menunggu 6 detik penuh, walau Row 2 muncul lebih cepat            ║
     * ║    - Cara: driver.findElement(By.cssSelector("#row2 input"))                  ║
     * ║    - Pro: Sederhana                                                           ║
     * ║    - Con: Lambat, tidak predictable, bukan best practice                      ║
     * ║    - Waktu eksekusi: Minimal 6 detik                                          ║
     * ║                                                                                ║
     * ║ 2. Implicit Wait (10 detik)                                                   ║
     * ║    - Tunggu sampai element PRESENCE (ada di DOM) atau 10 detik                ║
     * ║    - Cara: driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))  ║
     * ║          driver.findElement(By.cssSelector("#row2 input"))                    ║
     * ║    - Pro: Mudah diimplementasi, berlaku untuk semua element searches          ║
     * ║    - Con: Lambat saat element tidak ada (tunggu full 10 detik)                ║
     * ║    - Waktu eksekusi: ~5 detik (Row 2 muncul di detik ke-5)                   ║
     * ║                                                                                ║
     * ║ 3. Explicit Wait (10 detik) - RECOMMENDED                                     ║
     * ║    - Tunggu sampai kondisi SPESIFIK (visibility/clickability) atau 10 detik   ║
     * ║    - Cara: wait = new WebDriverWait(driver, Duration.ofSeconds(10))          ║
     * ║          wait.until(ExpectedConditions.visibilityOfElementLocated(...))       ║
     * ║    - Pro: Cepat, kontrol penuh, reliable, best practice                       ║
     * ║    - Con: Perlu lebih banyak kode                                             ║
     * ║    - Waktu eksekusi: ~5 detik (berhenti saat Row 2 visible)                  ║
     * ║                                                                                ║
     * ╠════════════════════════════════════════════════════════════════════════════════╣
     * ║ KESIMPULAN:                                                                    ║
     * ║ • JANGAN gunakan Thread.sleep() di production code                            ║
     * ║ • Gunakan Implicit Wait untuk case sederhana                                  ║
     * ║ • Gunakan Explicit Wait untuk case kompleks - RECOMMENDED! ✅                 ║
     * ╚════════════════════════════════════════════════════════════════════════════════╝
     */
}
