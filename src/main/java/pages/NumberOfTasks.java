
package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$x;

public class NumberOfTasks {
    private final SelenideElement amountTasks = $x("//div[@class='showing']").
            as("Элемент с количеством задач");

    private int totalTasks;

    private int extractTotalTasks(String text) {
        Pattern pattern = Pattern.compile("из (\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Общее количество задач не найдено в тексте: " + text);
    }

    public void saveTotalTasks() {
        String text = amountTasks.getText();
        totalTasks = extractTotalTasks(text);
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void tasks() {
        saveTotalTasks();
    }
}

