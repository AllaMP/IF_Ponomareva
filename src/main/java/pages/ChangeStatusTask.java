package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangeStatusTask {
    private final WebDriver driver;

    @FindBy(xpath = "//a[@id='action_id_21']")
    private WebElement workButton;

    @FindBy(xpath = "//a[@id='opsbar-transitions_more']")
    private WebElement businessProcessButton;

    @FindBy(xpath = "//aui-item-link[@id='action_id_31']")
    private WebElement readyButton;

    public ChangeStatusTask(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void status() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(workButton));
            js.executeScript("arguments[0].click();", workButton);
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(businessProcessButton));
            js.executeScript("arguments[0].click();", businessProcessButton);
            wait.until(ExpectedConditions.elementToBeClickable(readyButton));
            js.executeScript("arguments[0].click();", readyButton);
        } catch (TimeoutException e) {
            System.out.println("Ошибка: элемент не найден или не кликабелен.");
        } catch (NoSuchElementException e) {
            System.out.println("Ошибка: элемент не найден в DOM.");
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка.");
        }
    }
}
