package webHooks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import testPage.TestJira;
import utils.BrowserConfig;
import utils.ConfigLoader;

public class WebHooks {

    @BeforeEach
    public void initBrowser() {

        ConfigLoader configLoader = new ConfigLoader("config.properties");

        BrowserConfig browserConfig = new BrowserConfig(configLoader);
        browserConfig.configureBrowser();

        String url = configLoader.getProperty("url", "");
        Selenide.open(url);

        WebDriverRunner.getWebDriver().manage().window().maximize();

        String username = configLoader.getProperty("username", "");
        String password = configLoader.getProperty("password", "");
        TestJira.setUsername(username);
        TestJira.setPassword(password);
    }

    @AfterEach
    public void closeBrowser() {
        WebDriverRunner.closeWebDriver();
    }
}
