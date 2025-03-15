package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader() {
        loadConfig();
    }

    private void loadConfig() {
        String configFilePath = "src/test/resources/config.properties";
        try (InputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
            System.out.println("Файл config.properties загружен успешно.");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении config.properties", e);
        }
    }

    public String getRickAndMortyApiUrl() {
        return properties.getProperty("rickAndMorty.api.url");
    }

    public String getReqresApiClientUrl() {
        return properties.getProperty("reqres.api.url");
    }

    public int getMaxEpisode() {
        String maxEpisode = properties.getProperty("max.episode");
        if (maxEpisode == null || maxEpisode.trim().isEmpty()) {
            throw new RuntimeException("Свойство max.episode не найдено в config.properties!");
        }
        return Integer.parseInt(maxEpisode);
    }
}