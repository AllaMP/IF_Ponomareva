package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import testPage.CucumberRunnerTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CucumberHooks {

    @Before
    public static void initBrowser() {
        String browser = properties.getProperty("browser", "").toLowerCase();
        System.setProperty("selenide.browser", browser);

        if (browser.equals("firefox")) {
            Configuration.browser = "firefox";
            String firefoxDriverPath = properties.getProperty("firefox");
            if (firefoxDriverPath != null) {
                System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            }
        } else if (browser.equals("chrome")) {
            Configuration.browser = "chrome";
            String chromeDriverPath = properties.getProperty("chrome");
            if (chromeDriverPath != null) {
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            }
        } else {
            throw new IllegalArgumentException("Неверный браузер: " + browser);
        }
    }

    private static final Properties properties;

    static {
        properties = configProperties();
    }

    public static Properties configProperties() {
        Properties properties = new Properties();
        try {
            InputStream input = CucumberRunnerTest.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден!");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка при загрузке config.properties", ex);
        }
        return properties;
    }

    @After
    public static void closeBrowser() {
        WebDriverRunner.closeWebDriver();
    }
}

