//package webHooks;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.WebDriverRunner;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import testPage.TestJira;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//
//public class WebHooks {
//    @BeforeAll
//    public static void initBrowser() {
//
//        Properties properties = new Properties();
//        try {
//            InputStream input = TestJira.class.getClassLoader().getResourceAsStream("config.properties");
//            if (input == null) {
//                throw new RuntimeException("Файл config.properties не найден!");
//            }
//            try {
//                properties.load(input);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Ошибка при загрузке config.properties", e);
//        }
//
//        String browser = properties.getProperty("browser", "").toLowerCase();
//        String url = properties.getProperty("url", "");
//        String username = properties.getProperty("username", "");
//        String password = properties.getProperty("password", "");
//
//        System.setProperty("selenide.browse", properties.getProperty("chrome"));
//
//        if ("firefox".equals(browser)) {
//            Configuration.browser = "firefox";
//            String firefoxDriverPath = properties.getProperty("firefox");
//            if (firefoxDriverPath != null) {
//                System.setProperty("webdriver.firefox.driver", firefoxDriverPath);
//            }
//        } else if ("chrome".equals(browser)) {
//            Configuration.browser = "chrome";
//            String chromeDriverPath = properties.getProperty("chrome");
//            if (chromeDriverPath != null) {
//                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//            }
//        } else {
//            throw new IllegalArgumentException("Неверный браузер: " + browser);
//        }
//
//        Selenide.open(url);
//
////        WebDriverRunner.getWebDriver().manage().window().maximize();
//
//        TestJira.username = username;
//        TestJira.password = password;
//    }
//
//    @AfterAll
//    public static void closeBrowser() {
//    }
//}
