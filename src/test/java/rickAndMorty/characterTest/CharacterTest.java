package rickAndMorty.characterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rickAndMorty.characterMovie.Result;
import rickAndMorty.characterMovie.RickAndMortyCharacter;
import rickAndMorty.steps.CharacterSteps;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CharacterTest {

    private static final CharacterSteps characterSteps = new CharacterSteps();

    @Test
    @DisplayName("Получение информации по персонажу Morty Smith")
    public void checkCharacterName() {
        // Шаг 1: Указываем ожидаемое имя персонажа
        String expectedCharacterName = "Morty Smith";

        // Шаг 2: Получаем информацию о персонаже по имени
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(expectedCharacterName);

        // Шаг 3: Проверяем, что имя персонажа соответствует ожидаемому
        Assertions.assertNotNull(rickAndMortyCharacter);
        Assertions.assertEquals(expectedCharacterName, rickAndMortyCharacter.getName(), "Имя персонажа не совпадает");
    }

    @Test
    @DisplayName("Проверка максимального номера эпизода для Morty Smith")
    public void getMaxEpisodeNumber() {
        // Шаг 1: Указываем имя персонажа
        String characterName = "Morty Smith";

        // Шаг 2: Получаем информацию о персонаже
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(characterName);

        // Шаг 3: Проверяем, что данные получены
        Assertions.assertNotNull(rickAndMortyCharacter, "Информация о персонаже не получена");
        Assertions.assertNotNull(rickAndMortyCharacter.results, "Список результатов пуст");
        Assertions.assertFalse(rickAndMortyCharacter.results.isEmpty(), "Результаты не содержат данных");

        // Шаг 4: Получаем первый результат (предполагается, что это основной Morty Smith)
        Result characterResult = rickAndMortyCharacter.results.get(0);

        //Шаг 5: Получаем список эпизодов
        List<String> episodes = characterResult.getEpisode();
        Pattern pattern = Pattern.compile("episode/(\\d+)");
        int maxEpisode = 0;

        for (String episodeUrl : episodes) {
            Matcher matcher = pattern.matcher(episodeUrl);
            if (matcher.find()) {
                int episodeNum = Integer.parseInt(matcher.group(1));
                maxEpisode = Math.max(maxEpisode, episodeNum);
            }
        }
        System.out.println("!!!!! " + maxEpisode);
    }
}