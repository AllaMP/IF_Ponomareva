package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.LoginPage;
import utils.ConfigLoader;

import static com.codeborne.selenide.Selenide.open;
import static pages.OrderProcessor.*;

public class OrderProcessorSteps {

    @Дано("логинюсь на странице входа для поиска")
    public void authorizationPageForSearch() {
        String baseUrl = ConfigLoader.getBaseUrl();
        open(baseUrl);
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
    }

    @Дано("Ищу и переход к тесту 'TestSeleniumATHomework'")
    public void searchTestSeleniumATHomework() {
        order();
    }

    @Когда("ожидаемая версия 'Version 2.0'")
    public void checkExpectedVersion() {
        getVersionElement();
    }

    @Тогда("Версия корректна")
    public void versionIsCorrect() {
        checkVersion();
    }

    @Когда("cтатус 'Сделать'")
    public void checkStatusDo() {
        getStatusElement();
    }

    @Тогда("Статус корректен")
    public void statusIsCorrect() {
        checkStatus();
    }
}
