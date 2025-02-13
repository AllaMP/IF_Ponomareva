package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraTestProject {
    private final WebDriver driver;

    @FindBy(id = "browse_link")
    private WebElement browseLink;

    @FindBy(id = "admin_main_proj_link_lnk")
    private WebElement testProjectLink;

    public JiraTestProject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectProject() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Ожидание и клик по browseLink
        wait.until(ExpectedConditions.elementToBeClickable(browseLink));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].focus();", browseLink);
        js.executeScript("arguments[0].click();", browseLink);

        // Ожидание и клик по testProjectLink
        wait.until(ExpectedConditions.elementToBeClickable(testProjectLink));
        js.executeScript("arguments[0].focus();", testProjectLink);
        js.executeScript("arguments[0].click();", testProjectLink);

        // Проверка загрузки страницы проекта
        WebElement projectHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("project-name-val")));
        assert projectHeader.isDisplayed() : "Страница проекта не загрузилась!";
        assert projectHeader.getText().equals("Test") : "Название проекта не соответствует ожидаемому!";
    }
}
