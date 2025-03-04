package rickAndMorty.steps;

import io.cucumber.java.ru.То;
import org.junit.jupiter.api.Assertions;
import rickAndMorty.models.characterMortySmith.Result;
import rickAndMorty.models.characterMortySmith.RickAndMortyCharacter;
import rickAndMorty.models.characterStranger.SingleCharacter;
import rickAndMorty.models.episode.RickAndMortyEpisode;

import java.util.List;

public class MatchCheckInfoStep {
    private static final CharacterSteps characterSteps = new CharacterSteps();

    @То("Сравнениваю расу и местоположение последнего персонажа с 'Morty Smith'")
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
