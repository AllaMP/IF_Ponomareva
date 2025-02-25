package rickAndMorty.steps;

import org.apache.http.HttpStatus;
import rickAndMorty.api.movie.CharacterApi;

public class CharacterSteps {

    private static final CharacterApi characterApi = new CharacterApi();

    public Character getCharacterByName(String name) {
        return characterApi.getNameCharacter(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }


}
