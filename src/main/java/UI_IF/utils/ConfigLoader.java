package UI_IF.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigLoader {
    private final Properties properties;

    public ConfigLoader(String configFilePath) {
        properties = new Properties();
        loadProperties(configFilePath);
    }

    private void loadProperties(String configFilePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                throw new RuntimeException("Файл конфигурации не найден: " + configFilePath);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке конфигурации", e);
        }
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
