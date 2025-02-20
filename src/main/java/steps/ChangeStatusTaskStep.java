package steps;

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
import static pages.ChangeStatusTask.*;
import static pages.LoginPage.*;
import static pages.OrderProcessor.*;

public class ChangeStatusTaskStep {
    @Дано("Ввожу логин и пароль на странице входа в личный кабинет")
        public void usrAuthorisation() {
        String baseUrl = ConfigLoader.getBaseUrl();
        open(baseUrl);
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
    }


    @Когда("Нахожу созданную ранее задачу")
        public void searchTaskHardWork() {
        usernameField.shouldBe(visible, enabled).setValue("HardWork");
        selectTask.shouldBe(visible, enabled).click();
        }

    @И("Нажимаю на кнопку 'В работе'")
    public void buttonWork() {
        workButton.shouldBe(visible, Duration.ofSeconds(20));
        workButton.shouldBe(enabled).click();
    }

    @И("Закрываю всплывающее окно")
    public void popUp() {
        closeButton.click();
    }

    @И("Нажимаю кнопку 'Бизнес-процесс'")
    public void businessProcess() {
        businessProcessButton.shouldBe(visible, Duration.ofSeconds(10));
        businessProcessButton.shouldBe(enabled).click();
    }

    @Тогда("Перевожу задачу в статус 'Выполнено'")
    public void statusTaskReady() {
        readyButton.shouldBe(visible, Duration.ofSeconds(10));
        readyButton.shouldBe(enabled).click();
    }

    @И("Нажимаю на иконку профиля")
    public void profilePicture() {
        profilePersonal.click();
    }

    @И("Нажимаю на пункт 'Выйти'")
    public void exitAccount() {
        outButton.click();
    }

    @Тогда("Возвращаюсь на страницу входа в личный кабинет")
    public void authPage() {
        logOutPage.shouldBe(visible);
    }
}

