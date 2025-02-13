package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderProcessor {
    private final WebDriver driver;

    @FindBy(xpath = "//input[@name='searchString']")
    private WebElement usernameField;

    @FindBy(xpath = "//li[@original-title='TestSeleniumATHomework']")
    private WebElement selectTest;

    @FindBy(xpath = "//span[contains(@class, 'jira-issue-status-lozenge') and contains(text(), 'Сделать')]")
    private WebElement statusElement;

    @FindBy(xpath = "//span[@id='fixVersions-field']/a")
    private WebElement versionElement;

    public OrderProcessor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void order() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            usernameField.sendKeys("TestSeleniumATHomework");
            wait.until(ExpectedConditions.elementToBeClickable(selectTest));
            selectTest.click();

            // Проверка версии
            checkVersion();
            // Проверка статуса
            checkStatus();

        } catch (TimeoutException e) {
            System.out.println("Ошибка: элемент не найден или не кликабелен.");
        } catch (NoSuchElementException e) {
            System.out.println("Ошибка: элемент не найден в DOM.");
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка.");
        }
    }

    public void checkVersion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(versionElement));
            String versionText = versionElement.getText();
            if ("Version 2.0".equals(versionText)) {
                System.out.println("Версия корректна: " + versionText);
            } else {
                System.out.println("Ошибка: ожидаемая версия 'Version 2.0', но найдена '" + versionText + "'");
            }
        } catch (TimeoutException e) {
            System.out.println("Ошибка: элемент версии не найден или не видим.");
        } catch (NoSuchElementException e) {
            System.out.println("Ошибка: элемент версии не найден в DOM.");
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка при проверке версии.");
        }
    }

    public void checkStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(statusElement));
            String statusText = statusElement.getText();
            System.out.println("Статус корректен: " + statusText);
        } catch (TimeoutException e) {
            System.out.println("Ошибка: элемент статуса не найден или не видим.");

        } catch (NoSuchElementException e) {
            System.out.println("Ошибка: элемент статуса не найден в DOM.");
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка при проверке статуса.");
        }
    }


}
