package rickAndMorty.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.jupiter.api.Assertions;
import org.apache.http.HttpStatus;
import rickAndMorty.api.movie.CharacterApi;
import rickAndMorty.models.characterMortySmith.Result;
import rickAndMorty.models.characterMortySmith.RickAndMortyCharacter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MortySmithInfoStep {

    private static final CharacterApi characterApi = new CharacterApi();
    private RickAndMortyCharacter rickAndMortyCharacter;
    private Result characterResult;
    private int maxEpisode;

    @Дано("Отправляю запрос на получение персонажа с именем 'Morty Smith'")
    public void requestForCharacterNamed() {
        String name = "Morty Smith";
        rickAndMortyCharacter = characterApi.getNameCharacter(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
    }

    @То("Имя персонажа должно быть 'Morty Smith'")
    public void checkCharacterName() {
        String expectedName = "Morty Smith";
        Assertions.assertEquals(expectedName, rickAndMortyCharacter.getName(),
                "Неверное имя персонажа");
    }

    @Когда("Получаю максимальный номер эпизода для персонажа")
    public void getMaxEpisodeNumber() {
        characterResult = rickAndMortyCharacter.results.get(0);
        maxEpisode = getMaxEpisodeNumber(characterResult);
    }

    @То("Максимальный номер эпизода должен быть больше {int}")
    public void checkMaxEpisodeNumber(int minValue) {
        Assertions.assertTrue(maxEpisode > minValue, "Максимальный номер эпизода должен быть больше 0");
    }

    // Метод для получения максимального номера эпизода
    private int getMaxEpisodeNumber(Result character) {
        List<String> episodes = character.getEpisode();
        Pattern pattern = Pattern.compile("episode/(\\d+)");
        int maxEpisode = 0;

        for (String episodeUrl : episodes) {
            Matcher matcher = pattern.matcher(episodeUrl);
            if (matcher.find()) {
                int episodeNum = Integer.parseInt(matcher.group(1));
                maxEpisode = Math.max(maxEpisode, episodeNum);
            }
        }
        return maxEpisode;
    }
}
