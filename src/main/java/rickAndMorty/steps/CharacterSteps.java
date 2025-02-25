package rickAndMorty.steps;

import org.apache.http.HttpStatus;
import rickAndMorty.api.movie.CharacterApi;
import rickAndMorty.characterMovie.RickAndMortyCharacter;


public class CharacterSteps {

    private static final CharacterApi characterApi = new CharacterApi();

    public RickAndMortyCharacter getCharacterByName(String name) {
        return characterApi.getNameCharacter(name, "/character")
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
    }

    public RickAndMortyCharacter getMaxEpisodeNumber(String number) {
        return characterApi.getNameCharacter(number, "/episode")
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
    }

    public RickAndMortyCharacter getLastCharacter(String id) {
        return characterApi.getNameCharacter(id, "/episode")
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
    }
}
