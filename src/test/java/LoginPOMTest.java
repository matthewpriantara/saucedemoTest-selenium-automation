import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPOMTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private final String BASE_URL = "https://www.saucedemo.com/";
    private final String USERNAME = "standard_user";
    private final String PASSWORD = "secret_sauce";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginPageElementsPresent() {
        loginPage.verifyLogoPresent();
        loginPage.verifyUsernameFieldPresent();
        loginPage.verifyPasswordFieldPresent();
        loginPage.verifyLoginButtonPresent();
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.login(USERNAME, PASSWORD);
        assertTrue(loginPage.isLoginSuccessful());
    }

    @Test
    public void testInputUsernameOnly() {
        loginPage.verifyUsernameFieldPresent();
        loginPage.inputUsername(USERNAME);
    }

    @Test
    public void testInputPasswordOnly() {
        loginPage.verifyPasswordFieldPresent();
        loginPage.inputPassword(PASSWORD);
    }

    @Test
    public void testLoginFlowDetailed() {
        loginPage.verifyLogoPresent();
        loginPage.verifyUsernameFieldPresent();
        loginPage.inputUsername(USERNAME);
        loginPage.inputPassword(PASSWORD);
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful());
    }
}
