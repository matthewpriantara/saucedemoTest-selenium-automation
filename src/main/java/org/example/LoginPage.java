package org.example;

import org.openqa.selenium.WebDriver;

/**
 * LoginPage Class
 * Page Object untuk halaman Login SauceDemo
 * Merepresentasikan semua elemen dan action di halaman login
 */
public class LoginPage extends BasePage {

    /**
     * Constructor untuk LoginPage
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method untuk verify logo ada
     */
    public void verifyLogoPresent() {
        assert isElementVisible(SaucedemoLocators.LOGO) : "Logo tidak ditemukan!";
    }

    /**
     * Method untuk verify username field ada
     */
    public void verifyUsernameFieldPresent() {
        assert isElementVisible(SaucedemoLocators.USERNAME_FIELD) : "Username field tidak ditemukan!";
    }

    /**
     * Method untuk verify password field ada
     */
    public void verifyPasswordFieldPresent() {
        assert isElementVisible(SaucedemoLocators.PASSWORD_FIELD) : "Password field tidak ditemukan!";
    }

    /**
     * Method untuk verify login button ada
     */
    public void verifyLoginButtonPresent() {
        assert isElementVisible(SaucedemoLocators.LOGIN_BUTTON) : "Login button tidak ditemukan!";
    }

    /**
     * Method untuk input username
     * @param username Username yang akan di-input
     */
    public void inputUsername(String username) {
        inputText(SaucedemoLocators.USERNAME_FIELD, username);
    }

    /**
     * Method untuk input password
     * @param password Password yang akan di-input
     */
    public void inputPassword(String password) {
        inputText(SaucedemoLocators.PASSWORD_FIELD, password);
    }

    /**
     * Method untuk click login button
     */
    public void clickLoginButton() {
        click(SaucedemoLocators.LOGIN_BUTTON);
    }

    /**
     * Method untuk perform full login action
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        verifyLogoPresent();
        verifyUsernameFieldPresent();
        verifyPasswordFieldPresent();
        verifyLoginButtonPresent();
        
        inputUsername(username);
        inputPassword(password);
        clickLoginButton();
    }

    /**
     * Method untuk verify berhasil login (inventory container ada)
     */
    public boolean isLoginSuccessful() {
        return isElementVisible(SaucedemoLocators.INVENTORY_CONTAINER);
    }
}
