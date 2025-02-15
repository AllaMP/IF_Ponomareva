package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ChangeStatusTask {
    private final SelenideElement workButton = $x("//*[@id='action_id_21']").as("Кнопка 'В работе'");
    private final SelenideElement businessProcessButton = $x("//a[@id='opsbar-transitions_more']").
            as("Кнопка 'Бизнес-процесс'");
    private final SelenideElement readyButton = $x("//aui-item-link[@id='action_id_31']").
            as("Кнопка 'Выполнено'");
    private final SelenideElement closeButton = $x("//button[@class='aui-close-button']").
            as("Кнопка 'Выполнено'");
    private static final SelenideElement statusChange = $x("//span[@id='status-val']").
            as("Статус 'В РАБОТЕ'");

    public static String getstatusChange() {
        return statusChange.getText();
    }

    public void status() {
        try {
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