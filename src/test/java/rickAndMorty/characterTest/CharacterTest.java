package rickAndMorty.characterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rickAndMorty.characterMovie.Result;
import rickAndMorty.characterMovie.RickAndMortyCharacter;
import rickAndMorty.characterMovie.RickAndMortyEpisode;
import rickAndMorty.characterMovie.SingleCharacter;
import rickAndMorty.steps.CharacterSteps;

import java.util.List;


public class CharacterTest {

    private static final CharacterSteps characterSteps = new CharacterSteps();

    @Test
    @DisplayName("Получение информации по персонажу Morty Smith")
    public void checkCharacterName() {
        String expectedCharacterName = "Morty Smith";
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(expectedCharacterName);

        Assertions.assertNotNull(rickAndMortyCharacter, "Информация о персонаже не получена");
        Assertions.assertEquals(expectedCharacterName, rickAndMortyCharacter.getName(), "Имя персонажа не совпадает");
    }

    @Test
    @DisplayName("Проверка максимального номера эпизода для Morty Smith")
    public void getMaxEpisodeNumber() {
        String characterName = "Morty Smith";
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(characterName);

        Assertions.assertNotNull(rickAndMortyCharacter, "Информация о персонаже не получена");
        Assertions.assertNotNull(rickAndMortyCharacter.results, "Список результатов пуст");
        Assertions.assertFalse(rickAndMortyCharacter.results.isEmpty(), "Результаты не содержат данных");

        Result characterResult = rickAndMortyCharacter.results.get(0);
        int maxEpisode = characterSteps.getMaxEpisodeNumber(characterResult);

        System.out.println("Максимальный номер эпизода для " + characterName + ": " + maxEpisode);
        Assertions.assertTrue(maxEpisode > 0, "Максимальный номер эпизода должен быть больше 0");
    }

    @Test
    @DisplayName("Получение последнего персонажа из списка последнего эпизода Morty Smith")
    public void getLastCharacterFromLastEpisode() {
        String characterName = "Morty Smith";
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(characterName);

        Assertions.assertNotNull(rickAndMortyCharacter, "Информация о персонаже не получена");
        Assertions.assertNotNull(rickAndMortyCharacter.results, "Список результатов пуст");
        Assertions.assertFalse(rickAndMortyCharacter.results.isEmpty(), "Результаты не содержат данных");

        Result characterResult = rickAndMortyCharacter.results.get(0);
        int maxEpisode = characterSteps.getMaxEpisodeNumber(characterResult);
        Assertions.assertTrue(maxEpisode > 0, "Максимальный номер эпизода должен быть больше 0");

        RickAndMortyEpisode episode = characterSteps.getEpisodeById(maxEpisode);
        Assertions.assertNotNull(episode, "Данные об эпизоде не получены");
        Assertions.assertNotNull(episode.getCharacters(), "Список персонажей эпизода не получен");
        Assertions.assertFalse(episode.getCharacters().isEmpty(), "Список персонажей эпизода пуст");

        List<String> characterUrls = episode.getCharacters();
        String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);
        SingleCharacter lastCharacter = characterSteps.getCharacterByUrl(lastCharacterUrl);

        Assertions.assertNotNull(lastCharacter, "Последний персонаж не получен");
        Assertions.assertNotNull(lastCharacter.getName(), "Имя последнего персонажа не определено");
        System.out.println("Последний персонаж из эпизода " + maxEpisode + " (" + episode.getName() + "): " + lastCharacter.getName());
    }
}
