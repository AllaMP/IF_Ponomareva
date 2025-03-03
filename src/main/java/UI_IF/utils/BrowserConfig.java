package UI_IF.utils;

import com.codeborne.selenide.Configuration;

public class BrowserConfig {
    private final ConfigLoader configLoader;

    public BrowserConfig(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    public void configureBrowser() {
        String browser = configLoader.getProperty("browser", "chrome").toLowerCase();
        String chromeDriverPath = configLoader.getProperty("chrome.driver.path", "");
        String firefoxDriverPath = configLoader.getProperty("firefox.driver.path", "");

        Configuration.browser = browser;

        if ("firefox".equals(browser)) {
            if (!firefoxDriverPath.isEmpty()) {
                System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            }
        }
        else if ("chrome".equals(browser)) {
            if (!chromeDriverPath.isEmpty()) {
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            }
        }
        else {
            throw new IllegalArgumentException("Неверный браузер: " + browser);
        }
    }
}