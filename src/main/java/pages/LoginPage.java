package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class LoginPage {
    public static final SelenideElement authPage = $x("//h3[@id='gadget-0-title']").
            as("Вход в систему");
    private final SelenideElement usernameField = $x("//input[@id='login-form-username']").
            as("Имя пользователя");
    private final SelenideElement passwordField = $x("//input[@name='os_password']").as("Пароль");
    private final SelenideElement loginButton = $x("//input[@class='aui-button aui-button-primary']").
            as("Войти");
    public static final SelenideElement profile = $x("//a[@id='header-details-user-fullname']").
            as("Пользовательский профиль");
    public static final SelenideElement profilePersonal = $x("//a[@id='header-details-user-fullname']").
            as("Аватар личного кабинета");
    public static final SelenideElement outButton = $x("//a[@id='log_out']").
            as("Выход");
    public static final SelenideElement logOutPage = $x("//div[@class='aui-page-header-main']").
            as("Выход из системы Jira");


    public void login(String username, String password) {
        authPage.shouldBe(visible);
        usernameField.shouldBe(visible).setValue(username);
        passwordField.shouldBe(visible).setValue(password);
        loginButton.click();
        profile.shouldBe(visible);
    }

    public boolean isDisplayed() {
        return usernameField.isDisplayed();
    }
}