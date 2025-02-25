package rickAndMorty.characterTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rickAndMorty.characterMovie.RickAndMortyCharacter;
import rickAndMorty.steps.CharacterSteps;


public class CharacterTest {

    private static final CharacterSteps characterSteps = new CharacterSteps();

    @Test
    @DisplayName("Получение информации по персонажу Morty Smith")
    public void checkCharacterName() {
        // Шаг 1: Указываем ожидаемое имя персонажа
        String expectedCharacterName = "Morty Smith";

        // Шаг 2: Получаем информацию о персонаже по имени
        var RickAndMortyCharacter = characterSteps.getCharacterByName(expectedCharacterName);

        System.out.println(RickAndMortyCharacter);

        // Шаг 3: Проверяем, что имя персонажа соответствует ожидаемому
        Assertions.assertNotNull(RickAndMortyCharacter);
        Assertions.assertEquals(expectedCharacterName, RickAndMortyCharacter.getName(), "Имя персонажа не совпадает");
    }
}