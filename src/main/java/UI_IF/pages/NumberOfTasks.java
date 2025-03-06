
package UI_IF.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$x;

public class NumberOfTasks {
    private final SelenideElement amountTasks = $x("//div[@class='showing']").
            as("Элемент с количеством задач");

    private int totalTasks;

    @Step("Определение общего количества задач")
    private int extractTotalTasks(String text) {
        Pattern pattern = Pattern.compile("из (\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Общее количество задач не найдено в тексте: " + text);
    }

    @Step("Сохранение общего количества задач в переменную totalTasks")
    public void saveTotalTasks() {
        String text = amountTasks.getText();
        totalTasks = extractTotalTasks(text);
    }

    @Step("Предоставление общего количества задач")
    public int getTotalTasks() {
        return totalTasks;
    }

    @Step("Сохранение общего количества задач")
    public void tasks() {
        saveTotalTasks();
    }
}

