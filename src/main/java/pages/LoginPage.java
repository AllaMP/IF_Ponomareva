package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(xpath = "//input[@name='os_username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='os_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@class='aui-button aui-button-primary']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        // Проверка успешного входа
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        assert userProfile.isDisplayed() : "Пользователь не вошел в систему!";
    }
}