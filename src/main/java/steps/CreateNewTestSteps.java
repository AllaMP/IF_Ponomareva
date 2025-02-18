package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.LoginPage;
import utils.ConfigLoader;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static pages.CreateNewTest.*;
import static pages.JiraTestProject.*;
import static pages.ChangeStatusTask.*;

public class CreateNewTestSteps {
    int totalTasks1;

    @Дано("авторизовываюсь на странице входа")
        public void authorizationPageTest() {
            String baseUrl = ConfigLoader.getBaseUrl();
            open(baseUrl);
            String username = ConfigLoader.getUsername();
            String password = ConfigLoader.getPassword();
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
    }

    @Когда("кликаю на вкладку 'Проекты'")
        public void openBrowseLink() {
            browseLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Тогда("перехожу в проект 'Тест'")
        public void openTestProject() {
            testProjectLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @И("сохраняю количество тестов до создания нового")
    public void saveInitialTestCount() {
        String text = amountTasks.getText();
        totalTasks1 = extractTotalTasks(text);
    }

    @Дано("кликаю на кнопку создания задачи")
    public void clickCreateTaskButton() {
        createButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("заполняю поле 'Тема'")
    public void fillSummaryField() {
        summaryField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue("ALLA");
    }

    @И("выбораю 'Seriousnes'")
    public void selectSeriousness() {
        selectSeriousness.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10103");
    }

    @И("нажимаю кнопку 'Визуальный'")
    public void clickVisualButton() {
        checkAndClickVisualButton(visualButton1);
    }

    @И("нажимаю кнопку 'Визуальный2'")
    public void clickVisualButton2() {
        checkAndClickVisualButton(visualButton2);
    }

    @И("заполняю поле 'Описание'")
    public void fillDescriptionField() {
        fillTinyMCEField(tinymceIframe1, "Описание для iframe 1");
    }

    @И("заполняю поле 'Описание2'")
    public void fillDescriptionField2() {
        fillTinyMCEField(tinymceIframe2, "Окружение для iframe 2");
    }

    @И("выбираю Fix 'Versions'")
    public void selectFixVersions() {
        selectFixVersions.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10001");
        selectVersions.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10001");
    }

    @И("заполняю поле 'Labels'")
    public void fillLabelsField() {
        labelsTextarea.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue("bugfix").pressEnter();
    }

    @И("выбираю ссылку на 'Epic'")
    public void selectEpicLink() {
        selectLinkToEpic.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        selectLinkToEpicOption.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("выбираю 'Спринт'")
    public void selectSprint() {
        selectLinkToSprint.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        selectLinkToSprintOption.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("кликаю кнопку 'Создать'")
    public void clickCreateButton() {
        selectLinkToSprint.pressEnter();
    }

    @И("кликаю на url нового теста")
    public void clickNewTestUrl() {
        urlNewTaskButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("перехожу обратно на страницу тестов")
    public void navigateBackToTestsPage() {
        Selenide.back();
        Selenide.sleep(2000);
    }

    @И("сравниваю количество тестов после создания нового")
    public void compareTestCount() {
        String text = amountTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
        int totalTasks2 = extractTotalTasks(text);
        System.out.println("Общее количество до = " + totalTasks1 + " Новое количество = " + totalTasks2);
        Selenide.forward();
    }

    @И("нажимаю кнопку 'В работе'")
    public void clickInProgressButton() {
        workButton.shouldBe(visible, Duration.ofSeconds(20));
        workButton.shouldBe(enabled).click();
        closeButton.click();
    }

    @И("нажимаю кнопку 'Бизнес-процесс'")
    public void clickBusinessProcessButton() {
        businessProcessButton.shouldBe(visible, Duration.ofSeconds(10));
        businessProcessButton.shouldBe(enabled).click();
    }

    @И("выбираю 'Выполнено'")
    public void selectDoneStatus() {
        readyButton.shouldBe(visible, Duration.ofSeconds(10));
        readyButton.shouldBe(enabled).click();
    }
}
