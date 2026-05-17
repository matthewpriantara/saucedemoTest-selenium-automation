package org.example;

import org.openqa.selenium.By;

/**
 * Centralized Locators untuk SauceDemo
 * Berfungsi untuk:
 * - Memudahkan perubahan locator
 * - Mengurangi duplikasi locator
 * - Maintenance lebih mudah
 */
public class SaucedemoLocators {
    
    // Login Page Locators
    public static final By LOGO = By.xpath("//div[text()='Swag Labs']");
    public static final By USERNAME_FIELD = By.name("user-name");
    public static final By PASSWORD_FIELD = By.xpath("//input[@id='password']");
    public static final By LOGIN_BUTTON = By.xpath("//input[@type='submit' and @value='Login']");
    
    // Inventory Page Locators
    public static final By INVENTORY_CONTAINER = By.xpath("//div[contains(@class, 'inventory_container')]");
}
