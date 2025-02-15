package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CreateNewTest {
    private final SelenideElement createButton = $x("//a[@id='create_link']").
            as("Кнопка создания задачи");
    private final SelenideElement summaryField = $x("//input[@id='summary']").
            as("Поле Тема");
    private final SelenideElement visualButton1 = $x("//button[text()='Визуальный']").
            as("Кнопка Визуальный 1");
    private final SelenideElement tinymceIframe1 = $x("(//iframe[contains(@class, 'tox-edit-area__iframe')" +
            "])[1]").as("Iframe 1");
    private final SelenideElement visualButton2 = $x("(//button[text()='Визуальный'])[2]").
            as("Кнопка Визуальный 2");
    private final SelenideElement tinymceIframe2 = $x("(//iframe[contains(@class, 'tox-edit-area__iframe')" +
            "])[2]").as("Iframe 2");
    private final SelenideElement selectFixVersions = $x("//select[@id='fixVersions']").
            as("Выбор Fix Versions");
    private final SelenideElement selectVersions = $x("//select[@id='versions']").as("Выбор Versions");
    private final SelenideElement labelsTextarea = $x("//textarea[@id='labels-textarea']").
            as("Поле Labels");
    private final SelenideElement selectLinkToEpic = $x("//input[@id='customfield_10100-field']").
            as("Ссылка на Epic");
    private final SelenideElement selectLinkToEpicOption = $x("//li[@class='aui-list-item " +
            "aui-list-item-li-issue'][3]").as("Опция Epic");
    private final SelenideElement selectLinkToSprint = $x("//input[@id='customfield_10104-field']").
            as("Ссылка на Sprint");
    private final SelenideElement selectLinkToSprintOption = $x("//li[@class='aui-list-item " +
            "aui-list-item-li-доска-спринт-3']").as("Опция Sprint");
    private final SelenideElement selectSeriousness = $x("//select[@id='customfield_10400']").
            as("Выбор Seriousness");
    private final SelenideElement urlNewTaskButton = $x("//a[@class='issue-created-key issue-link']").
            as("Ссылка на новую задачу");
    private final SelenideElement amountTasks = $x("//div[@class='showing']").as("Количество задач");
    private static final SelenideElement statusTasks = $x("//a[@id='project-name-val']").
            as("Статус задачи");

    public static String getStatusTasks() {
        return statusTasks.getText();
    }

    public void create(int oldCounter) {
        try {
            createButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();

            summaryField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue("ALLA");

            selectSeriousness.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10103");

            checkAndClickVisualButton(visualButton1);
            checkAndClickVisualButton(visualButton2);

            fillTinyMCEField(tinymceIframe1, "Описание для iframe 1");

            fillTinyMCEField(tinymceIframe2, "Окружение для iframe 2");

            selectFixVersions.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10001");
            selectVersions.shouldBe(Condition.visible, Duration.ofSeconds(10)).selectOptionByValue("10001");

            labelsTextarea.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue("bugfix").pressEnter();

            selectLinkToEpic.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
            selectLinkToEpicOption.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();

            selectLinkToSprint.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
            selectLinkToSprintOption.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
            selectLinkToSprint.pressEnter();

            urlNewTaskButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();

            Selenide.back();
            Selenide.sleep(2000L);

            String text = amountTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
            int totalTasks = extractTotalTasks(text);
            System.out.println("Общее количество до = " + oldCounter + " Новое количество = " + totalTasks);

            Selenide.forward();

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            throw e;
        }
    }

    private void checkAndClickVisualButton(SelenideElement button) {
        String ariaPressed = button.getAttribute("aria-pressed");
        if (ariaPressed == null || "false".equals(ariaPressed)) {
            button.shouldBe(visible, Duration.ofSeconds(10)).click();
        }
    }

    private void fillTinyMCEField(SelenideElement tinymceIframe, String text) {
        switchTo().frame(tinymceIframe);
        SelenideElement tinyMCEBody = $("body");
        tinyMCEBody.shouldBe(visible, Duration.ofSeconds(20)).setValue(text);
        switchTo().defaultContent();
    }

    private int extractTotalTasks(String text) {
        Pattern pattern = Pattern.compile("из (\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Общее количество задач не найдено в тексте: " + text);
    }
}