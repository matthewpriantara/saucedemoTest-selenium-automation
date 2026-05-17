package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage Class
 * Berfungsi untuk:
 * - Mengurangi code duplication
 * - Menyatukan fungsi umum
 * - Mempermudah inheritance
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final long WAIT_TIME = 10;

    /**
     * Constructor untuk BasePage
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }

    /**
     * Method untuk menunggu elemen muncul
     * @param by Locator elemen
     * @return WebElement yang sudah visible
     */
    public WebElement waitForElementVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Method untuk click pada elemen dengan wait otomatis
     * @param by Locator elemen yang akan di-click
     */
    public void click(By by) {
        waitForElementVisible(by);
        driver.findElement(by).click();
    }

    /**
     * Method untuk input text pada elemen dengan wait otomatis
     * @param by Locator elemen
     * @param text Text yang akan di-input
     */
    public void inputText(By by, String text) {
        WebElement element = waitForElementVisible(by);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Method untuk get text dari elemen
     * @param by Locator elemen
     * @return Text dari elemen
     */
    public String getText(By by) {
        return waitForElementVisible(by).getText();
    }

    /**
     * Method untuk check apakah elemen visible
     * @param by Locator elemen
     * @return true jika visible, false jika tidak
     */
    public boolean isElementVisible(By by) {
        try {
            waitForElementVisible(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
