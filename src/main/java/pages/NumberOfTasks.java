package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfTasks {

    @FindBy(xpath = "//div[@class='showing']")
    private WebElement amountTasks;

    // Глобальная переменная для хранения числа
    private int totalTasks;

    public NumberOfTasks(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Метод для извлечения числа из текста
    private int extractTotalTasks(String text) {
        // Используем регулярное выражение для поиска числа после "из"
        Pattern pattern = Pattern.compile("из (\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)); // Извлекаем число после "из"
        }
        throw new RuntimeException("Общее количество задач не найдено в тексте: " + text);
    }

    // Метод для сохранения числа в глобальную переменную
    public void saveTotalTasks() {
        String text = amountTasks.getText();
        totalTasks = extractTotalTasks(text);
    }

    // Геттер для получения сохраненного числа
    public int getTotalTasks() {
        return totalTasks;
    }

    public void tasks() {
        saveTotalTasks();
    }
}

