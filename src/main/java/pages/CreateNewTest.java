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
    public static int fistCounter;
    public static final SelenideElement createButton = $x("//a[@id='create_link']").
            as("Кнопка создания задачи");
    public static final SelenideElement summaryField = $x("//input[@id='summary']").
            as("Поле Тема");
    public static final SelenideElement visualButton1 = $x("//button[text()='Визуальный']").
            as("Кнопка Визуальный 1");
    public static final SelenideElement tinymceIframe1 = $x("(//iframe[contains(@class, 'tox-edit-area__iframe')" +
            "])[1]").as("Iframe 1");
    public static final SelenideElement visualButton2 = $x("(//button[text()='Визуальный'])[2]").
            as("Кнопка Визуальный 2");
    public static final SelenideElement tinymceIframe2 = $x("(//iframe[contains(@class, 'tox-edit-area__iframe')" +
            "])[2]").as("Iframe 2");
    public static final SelenideElement selectFixVersions = $x("//select[@id='fixVersions']").
            as("Выбор Fix Versions");
    public static final SelenideElement selectVersions = $x("//select[@id='versions']").as("Выбор Versions");
    public static final SelenideElement labelsTextarea = $x("//textarea[@id='labels-textarea']").
            as("Поле Labels");
    public static final SelenideElement selectLinkToEpic = $x("//input[@id='customfield_10100-field']").
            as("Ссылка на Epic");
    public static final SelenideElement selectLinkToEpicOption = $x("//li[@class='aui-list-item " +
            "aui-list-item-li-issue'][3]").as("Опция Epic");
    public static final SelenideElement selectLinkToSprint = $x("//input[@id='customfield_10104-field']").
            as("Ссылка на Sprint");
    public static final SelenideElement selectLinkToSprintOption = $x("//li[@class='aui-list-item " +
            "aui-list-item-li-доска-спринт-3']").as("Опция Sprint");
    public static final SelenideElement selectSeriousness = $x("//select[@id='customfield_10400']").
            as("Выбор Seriousness");
    public static final SelenideElement urlNewTaskButton = $x("//a[@class='issue-created-key issue-link']").
            as("Ссылка на новую задачу");
    public static final SelenideElement amountTasks = $x("//div[@class='showing']").as("Количество задач");
    public static final SelenideElement statusTasks = $x("//a[@id='project-name-val']").
            as("Статус задачи");

    public static String getStatusTasks() {
        return statusTasks.getText();
    }

    public void create(int oldCounter) {
        fistCounter = oldCounter;
        try {
            createButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();

            summaryField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue("HardWork");

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

    public static void checkAndClickVisualButton(SelenideElement button) {
        String ariaPressed = button.getAttribute("aria-pressed");
        if (ariaPressed == null || "false".equals(ariaPressed)) {
            button.shouldBe(visible, Duration.ofSeconds(10)).click();
        }
    }

    public static void fillTinyMCEField(SelenideElement tinymceIframe, String text) {
        switchTo().frame(tinymceIframe);
        SelenideElement tinyMCEBody = $("body");
        tinyMCEBody.shouldBe(visible, Duration.ofSeconds(20)).setValue(text);
        switchTo().defaultContent();
    }

    public static int extractTotalTasks(String text) {
        Pattern pattern = Pattern.compile("из (\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Общее количество задач не найдено в тексте: " + text);
    }
}