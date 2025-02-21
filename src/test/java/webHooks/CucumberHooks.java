package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import testPage.CucumberRunnerTest;
import utils.ConfigLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;


public class CucumberHooks {

    private static final Properties properties;

    static {
        properties = configProperties();
    }

    @Before
    public void initBrowser() {
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

        String baseUrl = ConfigLoader.getBaseUrl();
        open(baseUrl);

        WebDriverRunner.getWebDriver().manage().window().maximize();
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
    public void closeBrowser() {
        WebDriverRunner.closeWebDriver();
    }
}

