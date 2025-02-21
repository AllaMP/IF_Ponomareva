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
import static pages.LoginPage.*;

public class CreateNewTestSteps {
    int totalTasks1;

    @Дано("Провожу авторизацию на странице входа")
        public void authorizationPageTest() {
            String baseUrl = ConfigLoader.getBaseUrl();
            open(baseUrl);
            String username = ConfigLoader.getUsername();
            String password = ConfigLoader.getPassword();
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
    }

    @Когда("Нажимаю на вкладку 'Проекты'")
        public void openBrowseLink() {
            projectLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Тогда("Перехожу в проект 'Тест'")
        public void openTestProject() {
            testProjectLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @И("Фиксирую количество задач до создания новой задачи")
    public void saveInitialTestCount() {
        String text = amountTasks.getText();
        totalTasks1 = extractTotalTasks(text);
    }

    @Дано("Нажимаю на кнопку 'Создать задачу'")
    public void clickCreateTaskButton() {
        createButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("Заполняю поле 'Тема'")
    public void fillSummaryField() {
        summaryField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue("HardWork");
    }

    @И("Выбираю значение 'Seriousness' из выпадающего списка")
    public void selectSeriousness() {
        selectSeriousness.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10103");
    }

    @И("Нажимаю кнопку 'Визуальный' для добавления визуальных данных")
    public void clickVisualButton() {
        checkAndClickVisualButton(visualButton1);
    }

    @И("Заполняю поле 'Описание'")
    public void fillDescriptionField() {
        fillTinyMCEField(tinymceIframe1, "Описание для iframe 1");
    }

    @И("Нажимаю кнопку 'Визуальный2' для дополнительных данных")
    public void clickVisualButton2() {
        checkAndClickVisualButton(visualButton2);
    }

    @И("Заполняю поле 'Описание2' значением")
    public void fillDescriptionField2() {
        fillTinyMCEField(tinymceIframe2, "Окружение для iframe 2");
    }

    @И("Выбираю версию исправления 'Fix Versions' из выпадающего списка")
    public void selectFixVersions() {
        selectFixVersions.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10001");
        selectVersions.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10001");
    }

    @И("Добавляю метки 'Labels' к задаче")
    public void fillLabelsField() {
        labelsTextarea.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue("bugfix").pressEnter();
    }

    @И("Связываю задачу с 'Epic' через ссылку")
    public void selectEpicLink() {
        selectLinkToEpic.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        selectLinkToEpicOption.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("Выбираю спринт из доступных")
    public void selectSprint() {
        selectLinkToSprint.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        selectLinkToSprintOption.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("Нажимаю кнопку 'Создать'")
    public void clickCreateButton() {
        selectLinkToSprint.pressEnter();
    }

    @И("Нажимаю по URL созданной задачи")
    public void clickNewTestUrl() {
        urlNewTaskButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @И("Возвращаюсь на страницу со списком задач")
    public void navigateBackToTestsPage() {
        Selenide.back();
        Selenide.sleep(2000);
    }

    @И("Сравниваю количество задач после создания новой")
    public void compareTestCount() {
        String text = amountTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
        int totalTasks2 = extractTotalTasks(text);
        System.out.println("Общее количество до = " + totalTasks1 + " Новое количество = " + totalTasks2);
        Selenide.forward();
    }

    @И("Перехожу к задаче и нажимаю кнопку 'В работу'")
    public void clickInProgressButton() {
        workButton.shouldBe(visible, Duration.ofSeconds(20));
        workButton.shouldBe(enabled).click();
        closeButton.click();
    }

    @И("Нажимаю вкладку 'Бизнес-процесс' для изменения статуса")
    public void clickBusinessProcessButton() {
        businessProcessButton.shouldBe(visible, Duration.ofSeconds(10));
        businessProcessButton.shouldBe(enabled).click();
    }

    @И("Выбираю статус 'Выполнено' из выпадающего списка")
    public void selectDoneStatus() {
        readyButton.shouldBe(visible, Duration.ofSeconds(10));
        readyButton.shouldBe(enabled).click();
    }

    @И("Нажимаю на иконку профиля в верхней панели")
    public void personalProfile() {
        profilePersonal.click();
    }

    @И("Нажимаю на 'Выйти' для завершения сессии")
    public void logOutButtonn() {
        outButton.click();
    }

    @Тогда("Успешно возвращаюсь на страницу входа")
    public void logOutPage() {
        logOutPage.shouldBe(visible);
    }
}
