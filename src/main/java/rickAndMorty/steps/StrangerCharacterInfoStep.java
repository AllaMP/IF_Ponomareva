package rickAndMorty.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.То;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import rickAndMorty.models.characterMortySmith.Result;
import rickAndMorty.models.characterMortySmith.RickAndMortyCharacter;
import rickAndMorty.models.characterStranger.SingleCharacter;
import rickAndMorty.models.episode.RickAndMortyEpisode;
import java.util.List;
import io.qameta.allure.Step;
import rickAndMorty.api.CharacterApi;
import utils.ConfigLoader;


public class StrangerCharacterInfoStep {

    private final CharacterApi characterApi = new CharacterApi();
    private RickAndMortyEpisode episode;
    private SingleCharacter lastCharacter;
    private Result mortyCharacter;
    private final int maxEpisode;

    public StrangerCharacterInfoStep() {
        ConfigLoader configLoader = new ConfigLoader();
        this.maxEpisode = configLoader.getMaxEpisode();
    }

    @Дано("Получаю последний эпизод, в котором появлялся Morty Smith")
    @Step("Получаю последний эпизод")
    public void getLastEpisode() {
        episode = characterApi.getEpisodeById(String.valueOf(maxEpisode))
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyEpisode.class);
    }

    @И("Нахожу последнего персонажа из эпизода")
    @Step("Нахожу последнего персонажа из эпизода")
    public void getLastCharacterFromLastEpisode() {
        List<String> characterUrls = episode.getCharacters();
        String lastCharacterUrl = characterUrls.get(characterUrls.size() - 1);
        lastCharacter = characterApi.getCharacterByUrl(lastCharacterUrl)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(SingleCharacter.class);
    }

    @То("Определяю имя последнего персонажа")
    @Step("Определяю имя последнего персонажа")
    public void checkLastCharacterName() {
        Assertions.assertNotNull(lastCharacter.getName(), "Имя последнего персонажа не определено");
    }

    @И("Определяю расу и местоположение последнего персонажа")
    @Step("Определяю расу и местоположение последнего персонажа")
    public void checkLastCharacterDetails() {
        Assertions.assertNotNull(lastCharacter.getSpecies(), "Раса не определена");
        Assertions.assertNotNull(
                lastCharacter.getLocation().getName(),
                "Местоположение не определено"
        );
    }

    @Дано("Запрашиваю персонажа с именем {string}")
    @Step("Запрашиваю персонажа с именем {name}")
    public void requestMortyCharacter(String name) {
        RickAndMortyCharacter rickAndMortyCharacter = characterApi.getNameCharacter(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
        this.mortyCharacter = rickAndMortyCharacter.getResults().get(0);
    }

    @То("Сравниваю расу и местоположение последнего персонажа с {string}")
    @Step("Сравниваю расу и местоположение последнего персонажа с {name}")
    public void compareCharacterDetails(String name) {
        printMortySmithInfo(mortyCharacter);

        String mortySpecies = mortyCharacter.getSpecies();
        String mortyLocation = mortyCharacter.getLocation().getName();
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

        Assertions.assertEquals(
                mortySpecies,
                lastCharacterSpecies,
                "Раса не совпадает с " + name
        );
        Assertions.assertEquals(
                mortyLocation,
                lastCharacterLocation,
                "Местоположение не совпадает с " + name
        );
    }

    @Step("Информация о персонаже Морти Смит")
    public void printMortySmithInfo(Result character) {
        String info = "Информация о Морти Смит:\n" +
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
}