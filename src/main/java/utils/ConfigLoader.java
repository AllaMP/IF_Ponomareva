package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static final Properties properties = new Properties();


    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке config.properties", e);
        }
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
}