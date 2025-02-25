package rickAndMorty.characterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rickAndMorty.characterMortySmith.Result;
import rickAndMorty.characterMortySmith.RickAndMortyCharacter;
import rickAndMorty.episode.RickAndMortyEpisode;
import rickAndMorty.characterStranger.SingleCharacter;
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
        Assertions.assertEquals(expectedCharacterName, rickAndMortyCharacter.getName(),
                "Не верное имя персонажа");
    }

    @Test
    @DisplayName("Проверка максимального номера эпизода для Morty Smith")
    public void getMaxEpisodeNumber() {
        String characterName = "Morty Smith";
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(characterName);

        Result characterResult = rickAndMortyCharacter.results.get(0);
        int maxEpisode = characterSteps.getMaxEpisodeNumber(characterResult);

        Assertions.assertTrue(maxEpisode > 0, "Максимальный номер эпизода должен быть больше 0");

        System.out.println("Максимальный номер эпизода для " + characterName + ": " + maxEpisode);
    }

    @Test
    @DisplayName("Получение последнего персонажа из списка последнего эпизода Morty Smith")
    public void getLastCharacterFromLastEpisode() {
        String characterName = "Morty Smith";
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(characterName);

        Result characterResult = rickAndMortyCharacter.results.get(0);
        int maxEpisode = characterSteps.getMaxEpisodeNumber(characterResult);

        RickAndMortyEpisode episode = characterSteps.getEpisodeById(maxEpisode);
        Assertions.assertNotNull(episode, "Данные об эпизоде не получены");
        Assertions.assertNotNull(episode.getCharacters(), "Список персонажей эпизода не получен");
        Assertions.assertFalse(episode.getCharacters().isEmpty(), "Список персонажей эпизода пуст");

        List<String> characterUrls = episode.getCharacters();
        String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);
        SingleCharacter lastCharacter = characterSteps.getCharacterByUrl(lastCharacterUrl);

        Assertions.assertNotNull(lastCharacter, "Последний персонаж не получен");
        Assertions.assertNotNull(lastCharacter.getName(), "Имя последнего персонажа не определено");

        System.out.println("Последний персонаж из эпизода " + maxEpisode +
                " (" + episode.getName() + "): " + lastCharacter.getName());
    }

    @Test
    @DisplayName("Получение данных по местонахождению и расе последнего персонажа из последнего эпизода Morty Smith")
    public void getLastCharacterLocationAndSpecies() {
        String characterName = "Morty Smith";
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(characterName);

        Result characterResult = rickAndMortyCharacter.results.get(0);
        int maxEpisode = characterSteps.getMaxEpisodeNumber(characterResult);

        RickAndMortyEpisode episode = characterSteps.getEpisodeById(maxEpisode);

        List<String> characterUrls = episode.getCharacters();
        String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);
        SingleCharacter lastCharacter = characterSteps.getCharacterByUrl(lastCharacterUrl);

        Assertions.assertNotNull(lastCharacter, "Последний персонаж не получен");
        Assertions.assertNotNull(lastCharacter.getSpecies(), "Раса последнего персонажа не определена");
        Assertions.assertNotNull(lastCharacter.getLocation(),
                "Местоположение последнего персонажа не определено");
        Assertions.assertNotNull(lastCharacter.getLocation().getName(),
                "Название местоположения последнего персонажа не определено");

        System.out.println("Последний персонаж из эпизода " + maxEpisode +
                " (" + episode.getName() + "): " + lastCharacter.getName());
        System.out.println("Раса: " + lastCharacter.getSpecies());
        System.out.println("Местоположение: " + lastCharacter.getLocation().getName());
    }

    @Test
    @DisplayName("Проверка совпадения расы и местоположения последнего персонажа с Morty Smith")
    public void lastCharacterVsMortySmithTest() {
        String characterName = "Morty Smith";
        RickAndMortyCharacter rickAndMortyCharacter = characterSteps.getCharacterByName(characterName);

        Result mortyResult = rickAndMortyCharacter.results.get(0);
        String mortySpecies = mortyResult.getSpecies();
        String mortyLocation = mortyResult.getLocation().getName();

        int maxEpisode = characterSteps.getMaxEpisodeNumber(mortyResult);

        RickAndMortyEpisode episode = characterSteps.getEpisodeById(maxEpisode);

        List<String> characterUrls = episode.getCharacters();
        String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);
        SingleCharacter lastCharacter = characterSteps.getCharacterByUrl(lastCharacterUrl);

        Assertions.assertNotNull(lastCharacter, "Последний персонаж не получен");
        Assertions.assertNotNull(lastCharacter.getName(), "Имя последнего персонажа не известно");
        Assertions.assertNotNull(lastCharacter.getSpecies(), "Раса последнего персонажа не известна");
        Assertions.assertNotNull(lastCharacter.getLocation(),
                "Местоположение последнего персонажа не известно");
        Assertions.assertNotNull(lastCharacter.getLocation().getName(),
                "Название местоположения последнего персонажа не определено");

        String lastCharacterSpecies = lastCharacter.getSpecies();
        String lastCharacterLocation = lastCharacter.getLocation().getName();

        boolean sameSpecies = mortySpecies.equals(lastCharacterSpecies);
        boolean sameLocation = mortyLocation.equals(lastCharacterLocation);

        String result = "Результат сравнения Morty Smith с последним персонажем из эпизода " +
                maxEpisode + " (" + episode.getName() + "):\n" +
                "Morty Smith: Раса = " + mortySpecies + ", Местоположение = " +
                mortyLocation + "\n" +
                "Последний персонаж (" + lastCharacter.getName() + "): Раса = " +
                lastCharacterSpecies + ", Местоположение = " + lastCharacterLocation + "\n" +
                "Раса совпадает: " + sameSpecies + "\n" +
                "Местоположение совпадает: " + sameLocation;

        System.out.println(result);
    }
}
