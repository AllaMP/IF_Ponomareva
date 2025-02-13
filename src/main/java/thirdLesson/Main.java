package thirdLesson;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import pages.*;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        // Загрузка конфигурации
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден!");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке config.properties", e);
        }

        // Получение настроек
        String browser = properties.getProperty("browser", "").toLowerCase();
        String url = properties.getProperty("url", "");
        String username = properties.getProperty("username", "");
        String password = properties.getProperty("password", "");

        // Инициализация драйвера
        WebDriver driver;

        if ("firefox".equals(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else if ("chrome".equals(browser)) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Неверный браузер: " + browser);
        }

        //Открыть страницу
        driver.get(url);

        //Масштабирование
        driver.manage().window().maximize();

        try {
            //Залогиниться
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);

            //Перейти в проект TEST
            JiraTestProject jiraTestProject = new JiraTestProject(driver);
            jiraTestProject.selectProject();

            //Количество задач
            NumberOfTasks numberOfTasks = new NumberOfTasks(driver);
            numberOfTasks.tasks();

            //Новая задача
            CreateNewTest createNewTest = new CreateNewTest(driver);
            createNewTest.create(numberOfTasks.getTotalTasks());

            // Изменить статус задачи
            ChangeStatusTask changeStatusTask = new ChangeStatusTask(driver);
            changeStatusTask.status();

            //Перейти в задачу и проверить параметры
            OrderProcessor orderProcessor = new OrderProcessor(driver);
            orderProcessor.order();

        } finally {
            driver.quit();
        }
    }
}