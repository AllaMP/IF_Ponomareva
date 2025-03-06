package UI_IF.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class JiraTestProject {

    private static final SelenideElement browseLink = $x("//a[@id='browse_link']").
            as("Ссылка на проекты");
    private static final SelenideElement testProjectLink = $x("//a[@id='admin_main_proj_link_lnk']").
            as("Ссылка на проект 'Test'");
    private static final SelenideElement openTask = $x("//span[@id='issues-subnavigation-title']").
            as("Ссылка на проект 'Test'");

    @Step("Найти в хэдере вкладку 'Проекты'")
    public static String getBrowseLink() {
        return browseLink.getText();
    }

    @Step("Найти в выпадающем списке проект Тест")
    public static String getOpenTask() {
        return openTask.getText();
    }

    @Step("Ожидание отображения элемента и переход по ссылке")
    public void openBrowseLink() {
        browseLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Выбор проекта Тест")
    public void openTestProject() {
        testProjectLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Выбрать проект")
    public void selectProject() {
        openBrowseLink();
        openTestProject();
    }
}

