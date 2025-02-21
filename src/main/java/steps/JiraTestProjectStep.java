package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.LoginPage;
import utils.ConfigLoader;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static pages.JiraTestProject.*;
import static pages.LoginPage.*;

public class JiraTestProjectStep {
    @Дано("Провожу авторизацию для входа в личный кабинет")
    public void authPageTest() {
        String baseUrl = ConfigLoader.getBaseUrl();
        open(baseUrl);
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    @Когда("Выбираю в хэдере вкладку 'Проекты'")
    public void getProjectLink() {
        projectLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }


    @Тогда("Выбираю проект 'Тест'")
    public void openTestProject() {
        testProjectLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @И("Нажимаю на аватар профиля в хэдере страницы")
    public void avatar() {
        profilePersonal.click();
    }

    @И("Нажимаю на строку 'Выйти' для завершения сессии")
    public void buttonnlogOut() {
        outButton.click();
    }

    @Тогда("Успешно перемещаюсь на страницу входа")
    public void startPage() {
        logOutPage.shouldBe(visible);
    }
}
