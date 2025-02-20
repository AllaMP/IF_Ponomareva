package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.LoginPage;
import utils.ConfigLoader;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static pages.LoginPage.*;
import static pages.OrderProcessor.*;

public class OrderProcessorSteps {

    @Дано("Ввожу логин и пароль на странице авторизации")
    public void authorizationPageForSearch() {
        String baseUrl = ConfigLoader.getBaseUrl();
        open(baseUrl);
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    @Дано("Нахожу тест 'TestSeleniumATHomework'")
    public void searchTestSeleniumATHomework() {
        order();
    }

    @Когда("Фиксирую версию 'Version 2.0'")
    public void checkExpectedVersion() {
        getVersionElement();
    }

    @Тогда("Подтверждаю, что версия корректна")
    public void versionIsCorrect() {
        checkVersion();
    }

    @Когда("Фиксирую cтатус 'Сделать'")
    public void checkStatusDo() {
        getStatusElement();
    }

    @Тогда("Подтверждаю, что статус корректен")
    public void statusIsCorrect() {
        checkStatus();
    }

    @Тогда("Нажимаю на аватар профиля")
    public void profilePersonal() {
        profilePersonal.click();
    }

    @И("Нажимаю на 'Выйти'")
    public void logOut() {
        outButton.click();
    }

    @Тогда("Возвращаюсь на страницу входа")
    public void logOutPage() {
        logOutPage.shouldBe(visible);
    }
}
