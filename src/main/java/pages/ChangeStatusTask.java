package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static pages.OrderProcessor.selectTest;
import static pages.OrderProcessor.usernameField;

public class ChangeStatusTask {
    public static final SelenideElement workButton = $x("//*[@id='action_id_21']").as("Кнопка 'В работе'");
    public static final SelenideElement businessProcessButton = $x("//a[@id='opsbar-transitions_more']").
            as("Кнопка 'Бизнес-процесс'");
    public static final SelenideElement readyButton = $x("//aui-item-link[@id='action_id_31']").
            as("Кнопка 'Выполнено'");
    public static final SelenideElement closeButton = $x("//button[@class='aui-close-button']").
            as("Всплывающее окно");
    public static final SelenideElement statusChange = $x("//span[@id='status-val']").
            as("Статус 'В РАБОТЕ'");
    public static final SelenideElement selectTask = $x("//li[@original-title='HardWork']").
            as("Элемент 'HardWork'");

    public void status() {
        try {
            usernameField.shouldBe(visible, enabled).setValue("HardWork");
            selectTask.shouldBe(visible, enabled).click();

            workButton.shouldBe(visible, Duration.ofSeconds(20));
            workButton.shouldBe(enabled).click();

            closeButton.click();

            businessProcessButton.shouldBe(visible, Duration.ofSeconds(10));
            businessProcessButton.shouldBe(enabled).click();

            readyButton.shouldBe(visible, Duration.ofSeconds(10));
            readyButton.shouldBe(enabled).click();

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}