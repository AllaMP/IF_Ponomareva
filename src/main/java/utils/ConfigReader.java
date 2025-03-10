package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties = new Properties();

    public ConfigReader() {
        loadConfig();
    }

    private void loadConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден в classpath!");
            }
            properties.load(input);
            System.out.println("Файл config.properties загружен успешно."); // Лог для подтверждения
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении config.properties", e);
        }
    }

    public String getRickAndMortyApiUrl() {
        return properties.getProperty("rick.and.morty.api.url");
    }

    public String getReqresApiClientUrl() {
        return properties.getProperty("reqres.api.url");
    }
}