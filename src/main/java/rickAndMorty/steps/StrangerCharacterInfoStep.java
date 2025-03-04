package rickAndMorty.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.jupiter.api.Assertions;
import rickAndMorty.api.movie.CharacterApi;
import rickAndMorty.models.characterMortySmith.Result;
import rickAndMorty.models.characterMortySmith.RickAndMortyCharacter;
import rickAndMorty.models.characterStranger.SingleCharacter;
import rickAndMorty.models.episode.RickAndMortyEpisode;

import java.util.List;

public class StrangerCharacterInfoStep {

    private static final CharacterApi characterApi = new CharacterApi();
    private RickAndMortyCharacter rickAndMortyCharacter;
    private Result characterResult;
    private int maxEpisode;
    private RickAndMortyEpisode episode;
    private SingleCharacter lastCharacter;

    @Дано("Отправляю запрос на получение персонажа с именем 'Morty Smith'")
    public void requestForCharacterNamed() {
        String name = "Morty Smith";
        rickAndMortyCharacter = characterApi.getNameCharacter(name)
                .statusCode(200)  // Используем код 200 вместо HttpStatus.SC_OK
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
    }

    @Когда("Получаю максимальный номер эпизода для персонажа")
    public void getMaxEpisodeNumber() {
        characterResult = rickAndMortyCharacter.getResults().get(0);
        maxEpisode = getMaxEpisodeNumber(characterResult);
    }

    @Когда("Получаю последний эпизод")
    public void getLastEpisode() {
        episode = characterApi.getEpisodeById(String.valueOf(maxEpisode))
                .statusCode(200)
                .extract()
                .body()
                .as(RickAndMortyEpisode.class);
    }

    @Когда("Нахожу последнего персонажа из эпизода")
    public void getLastCharacterFromLastEpisode() {
        List<String> characterUrls = episode.getCharacters();
        String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);
        lastCharacter = characterApi.getCharacterByUrl(lastCharacterUrl)
                .statusCode(200)
                .extract()
                .body()
                .as(SingleCharacter.class);
    }

    @То("Определяю имя последнего персонажа")
    public void getNameLastCharacter() {
        Assertions.assertNotNull(lastCharacter.getName(), "Имя последнего персонажа не определено");
    }

    @То("Определяю расу и местоположение последнего персонажа")
    public void getLastCharacterLocationAndSpecies() {
        Assertions.assertNotNull(lastCharacter.getSpecies(), "Раса последнего персонажа не определена");
        Assertions.assertNotNull(lastCharacter.getLocation().getName(), "Местоположение последнего персонажа не определено");
    }

    // Метод для получения максимального номера эпизода
    private int getMaxEpisodeNumber(Result character) {
        List<String> episodes = character.getEpisode();
        int maxEpisode = 0;
        for (String episodeUrl : episodes) {
            String[] parts = episodeUrl.split("/");
            int episodeNum = Integer.parseInt(parts[parts.length - 1]);
            maxEpisode = Math.max(maxEpisode, episodeNum);
        }
        return maxEpisode;
    }
}