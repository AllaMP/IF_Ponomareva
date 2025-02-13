package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewTest {
    private final WebDriver driver;

    @FindBy(xpath = "//a[@id='create_link']")
    private WebElement createButton;

    @FindBy(xpath = "//input[@name='summary']")
    private WebElement summaryField;

    @FindBy(xpath = "//button[text()='Визуальный']")
    private WebElement visualButton1;

    @FindBy(xpath = "(//button[text()='Визуальный'])[2]")
    private WebElement visualButton2;

    @FindBy(xpath = "(//iframe[contains(@class, 'tox-edit-area__iframe')])[1]")
    private WebElement tinymceIframe1;

    @FindBy(xpath = "(//iframe[contains(@class, 'tox-edit-area__iframe')])[2]")
    private WebElement tinymceIframe2;

    @FindBy(xpath = "//body[@id='tinymce']/p")
    private WebElement TinyMCEField;

    @FindBy(xpath = "//select[@id='fixVersions']")
    private WebElement selectFixVersions;

    @FindBy(xpath = "//select[@id='versions']")
    private WebElement selectVersions;

    @FindBy(id = "labels-textarea")
    private WebElement labelsTextarea;

    @FindBy(xpath = "//input[@id='customfield_10100-field']")
    private WebElement selectLinkToEpic;

    @FindBy(xpath = "//li[@class='aui-list-item aui-list-item-li-issue'][3]")
    private WebElement selectLinkToEpicOption;

    @FindBy(xpath = "//input[@id='customfield_10104-field']")
    private WebElement selectLinkToSprint;

    @FindBy(xpath = "//li[@class='aui-list-item aui-list-item-li-доска-спринт-3']")
    private WebElement selectLinkToSprintOption;

    @FindBy(xpath = "//select[@id='customfield_10400']")
    private WebElement selectSeriousness;

//    @FindBy(xpath = "//input[@id='create-issue-submit']")
//    private WebElement createTaskButton;

    @FindBy(xpath = "//a[@class='issue-created-key issue-link']")
    private WebElement urlNewTaskButton;

    @FindBy(xpath = "//div[@class='showing']")
    private WebElement amountTasks;

    public CreateNewTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void create(int oldCounter) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            createButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(summaryField));
            summaryField.sendKeys("ALLA");

            wait.until(ExpectedConditions.visibilityOf(selectSeriousness));
            wait.until(ExpectedConditions.elementToBeClickable(selectSeriousness));
            // Создаем объект Select
            Select dropdownSeriousness = new Select(selectSeriousness);
            // Выбираем опцию по значению
            dropdownSeriousness.selectByValue("10103");

            // Проверка первой кнопки "Визуальный"
            wait.until(ExpectedConditions.elementToBeClickable(visualButton1));
            if ("false".equals(visualButton1.getAttribute("aria-pressed"))) {
                visualButton1.click();
            }

            // Проверка второй кнопки "Визуальный"
            wait.until(ExpectedConditions.elementToBeClickable(visualButton2));
            if ("false".equals(visualButton2.getAttribute("aria-pressed"))) {
                visualButton2.click();
            }

            // Переключение на первый iframe
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(tinymceIframe1));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].innerText = 'Описание для iframe 1';", TinyMCEField);
            driver.switchTo().defaultContent();

            // Переключение на второй iframe
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(tinymceIframe2));
            js.executeScript("arguments[0].innerText = 'Окружение для iframe 2';", TinyMCEField);
            driver.switchTo().defaultContent();

            // Создаем объект Select
            Select dropdownFixVersion = new Select(selectFixVersions);
            // Выбираем опцию по значению
            dropdownFixVersion.selectByValue("10001");
            // Создаем объект Select
            Select dropdownVersion = new Select(selectVersions);
            // Выбираем опцию по значению
            dropdownVersion.selectByValue("10001");

            // Ввод метки
            wait.until(ExpectedConditions.elementToBeClickable(labelsTextarea));
            labelsTextarea.sendKeys("bugfix");
            wait.until(ExpectedConditions.elementToBeClickable(labelsTextarea));
            labelsTextarea.sendKeys(Keys.ENTER);

            // Выбор epic
            wait.until(ExpectedConditions.elementToBeClickable(selectLinkToEpic));
            selectLinkToEpic.click();
            wait.until(ExpectedConditions.elementToBeClickable(selectLinkToEpicOption));
            selectLinkToEpicOption.click();
            // Ожидание появления и кликабельности элемента в списке предложений
            wait.until(ExpectedConditions.elementToBeClickable(selectLinkToEpicOption));
            selectLinkToEpicOption.click();
            js.executeScript("arguments[0].click();", selectLinkToEpicOption);

            // Выбор sprint
            wait.until(ExpectedConditions.elementToBeClickable(selectLinkToSprint));
            selectLinkToSprint.click();
            wait.until(ExpectedConditions.elementToBeClickable(selectLinkToSprintOption));
            selectLinkToSprintOption.click();

            selectLinkToSprint.sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.elementToBeClickable(urlNewTaskButton));
            urlNewTaskButton.click();

            driver.navigate().back();
            Thread.sleep(2000);

            String text = amountTasks.getText();
            int totalTasks;

            // Используем регулярное выражение для поиска числа после "из"
            Pattern pattern = Pattern.compile("из (\\d+)");
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                totalTasks = Integer.parseInt(matcher.group(1)); // Извлекаем число после "из"
                System.out.println("Общее количество до = " + oldCounter + " Новое количество =  " + totalTasks);
            }

            driver.navigate().forward();

        } catch (TimeoutException e) {
            System.out.println("Ошибка: элемент не найден или не кликабелен.");
        } catch (NoSuchElementException e) {
            System.out.println("Ошибка: элемент не найден в DOM.");
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка.");
        }
    }
}
