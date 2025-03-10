package rickAndMorty.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.apache.http.HttpStatus;
import rickAndMorty.api.CharacterApi;
import rickAndMorty.models.characterMortySmith.Result;
import rickAndMorty.models.characterMortySmith.RickAndMortyCharacter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.qameta.allure.Step;
import utils.ConfigReader;

public class MortySmithInfoStep {

    private final CharacterApi characterApi = new CharacterApi();
    private RickAndMortyCharacter rickAndMortyCharacter;
    private int maxEpisode;

    @Дано("Отправляю запрос на получение персонажа с именем {string}")
    @Step("Отправляю запрос на получение персонажа с именем {name}")
    public void requestForCharacterNamed(String name) {
        rickAndMortyCharacter = characterApi.getNameCharacter(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
    }

    @То("Имя персонажа должно быть {string}")
    @Step("Проверяю, что имя персонажа равно {expectedName}")
    public void checkCharacterName(String expectedName) {
        Assertions.assertEquals(
                expectedName,
                rickAndMortyCharacter.getName(),
                "Неверное имя персонажа"
        );
    }

    @Когда("Получаю максимальный номер эпизода для персонажа")
    @Step("Получаю максимальный номер эпизода для персонажа")
    public void getMaxEpisodeNumber() {
        Result characterResult = rickAndMortyCharacter.getResults().get(0);
        maxEpisode = getMaxEpisodeNumber(characterResult);
        printMortySmithInfo(characterResult); // Вывод информации о Морти Смит
    }

    @То("Максимальный номер эпизода должен быть больше {int}")
    @Step("Проверяю, что максимальный номер эпизода больше {minValue}")
    public void checkMaxEpisodeNumber(int minValue) {
        Assertions.assertTrue(maxEpisode > minValue, "Максимальный номер эпизода должен быть больше "
                + minValue);
    }

    @Тогда("Вывожу информацию о максимальном эпизоде для Морти Смита")
    @Step("Вывожу информацию о максимальном эпизоде для Морти Смита")
    public void printMaxEpisodeInfo() {
        String result = "Морти Смит появляется в эпизоде с максимальным номером: " + maxEpisode;
        System.out.println(result);
    }

    @Step("Вывожу информацию о персонаже Морти Смит")
    public void printMortySmithInfo(Result character) {
        String info = "Информация о персонаже:\n" +
                "Имя: " + character.getName() + "\n" +
                "Статус: " + character.getStatus() + "\n" +
                "Раса: " + character.getSpecies() + "\n" +
                "Тип: " + character.getType() + "\n" +
                "Пол: " + character.getGender() + "\n" +
                "Местоположение: " + character.getLocation().getName() + "\n" +
                "Происхождение: " + character.getOrigin().getName() + "\n" +
                "Количество эпизодов: " + character.getEpisode().size();

        System.out.println(info);
    }

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