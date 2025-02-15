package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderProcessor {

    private final SelenideElement usernameField = $x("//input[@name='searchString']").as("Поле поиска");
    private final SelenideElement selectTest = $x("//li[@original-title='TestSeleniumATHomework']").
            as("Элемент 'TestSeleniumATHomework'");
    private static final SelenideElement statusElement = $x("//span[contains(@class, " +
            "'jira-issue-status-lozenge') " +
            "and contains(text(), 'Сделать')]").as("Элемент статуса");
    private static final SelenideElement versionElement = $x("//span[@id='fixVersions-field']/a").
            as("Элемент версии");

    public static String getStatusElement() {
        return statusElement.getText();
    }

    public static String getVersionElement() {
        return versionElement.getText();
    }

    public void order() {
        try {
            usernameField.shouldBe(visible, enabled).setValue("TestSeleniumATHomework");
            selectTest.shouldBe(visible, enabled).click();

            checkVersion();
            checkStatus();

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    public void checkVersion() {
        try {
            versionElement.shouldBe(visible);
            String versionText = versionElement.getText();
            if ("Version 2.0".equals(versionText)) {
                System.out.println("Версия корректна: " + versionText);
            } else {
                System.out.println("Ошибка: ожидаемая версия 'Version 2.0', но найдена '" + versionText + "'");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при проверке версии: " + e.getMessage());
        }
    }

    public void checkStatus() {
        try {
            statusElement.shouldBe(visible);
            String statusText = statusElement.getText();
            System.out.println("Статус корректен: " + statusText);
        } catch (Exception e) {
            System.out.println("Ошибка при проверке статуса: " + e.getMessage());
        }
    }
}