package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.LoginPage;
import utils.ConfigLoader;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.LoginPage.outButton;
import static pages.LoginPage.profilePersonal;
import static pages.LoginPage.logOutPage;

public class LoginSteps {

    @Дано("Нахожусь на странице входа")
    public void authorizationPage() {
        String baseUrl = ConfigLoader.getBaseUrl();
        open(baseUrl);
    }

    @Когда("Ввожу валидные логин и пароль")
    public void loginEnter() {
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    @Тогда("Перенаправляюсь на главную страницу Личного кабинета")
    public void profilePage() {
    }

    @И("Нахожу вкладку 'Проекты' в хедере страницы")
    public void profileEnter() {
    }

    @И("Нажимаю на аватар профиля в верхней панели")
    public void profilePerson() {
        profilePersonal.click();
    }

    @И("Выбираю пункт 'Выйти' из выпадающего меню")
    public void outLButton() {
        outButton.click();
    }

    @Тогда("Успешно выхожу из системы на страницу входа")
    public void logOutPage() {
        logOutPage.shouldBe(visible);
    }
}

