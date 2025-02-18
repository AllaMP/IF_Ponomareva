package webHooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import testPage.CucumberRunnerTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CucumberHooks {

    @Before
    public static void initBrowser() {
        Properties properties = new Properties();
        try {
            InputStream input = CucumberRunnerTest.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден!");
            }
            try {
                properties.load(input);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке config.properties", e);
        }

        String browser = properties.getProperty("browser", "").toLowerCase();

        System.setProperty("selenide.browse", properties.getProperty("firefox"));

        if ("firefox".equals(browser)) {
            Configuration.browser = "firefox";
            String firefoxDriverPath = properties.getProperty("firefox");
            if (firefoxDriverPath != null) {
                System.setProperty("webdriver.firefox.driver", firefoxDriverPath);
            }
        } else if ("chrome".equals(browser)) {
            Configuration.browser = "chrome";
            String chromeDriverPath = properties.getProperty("chrome");
            if (chromeDriverPath != null) {
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            }
        } else {
            throw new IllegalArgumentException("Неверный браузер: " + browser);
        }
    }

    @After
    public static void closeBrowser() {
    }
}


