package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.LoginPage;
import utils.ConfigLoader;

import static com.codeborne.selenide.Selenide.*;

public class LoginSteps {

    @Дано("нахожусь на странице входа")
    public void authorizationPage() {
        String baseUrl = ConfigLoader.getBaseUrl();
        open(baseUrl);
    }

    @Когда("ввожу логин и пароль")
    public void loginEnter() {
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    @Тогда("клиент перенаправлен на главную страницу ЛК")
    public void profilePage() {
    }

    @И("клиент должен увидеть вкладку 'Проекты' в хедере страницы")
    public void profileEnter() {
    }
}

