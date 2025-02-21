package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderProcessor {

    public static final SelenideElement usernameField = $x("//input[@name='searchString']").as("Поле поиска");
    public static final SelenideElement selectTest = $x("//li[@original-title='TestSeleniumATHomework']").
            as("Элемент 'TestSeleniumATHomework'");
    public static final SelenideElement statusElement = $x("//span[contains(@class, " +
            "'jira-issue-status-lozenge') " +
            "and contains(text(), 'Сделать')]").as("Элемент статуса");
    public static final SelenideElement versionElement = $x("//span[@id='fixVersions-field']/a").
            as("Элемент версии");

    public static void order() {
        try {
            usernameField.shouldBe(visible, enabled).setValue("TestSeleniumATHomework");
            selectTest.shouldBe(visible, enabled).click();

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}