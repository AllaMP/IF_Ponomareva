package rickAndMorty.steps;

import org.apache.http.HttpStatus;
import rickAndMorty.api.movie.CharacterApi;
import rickAndMorty.characterMovie.Result;
import rickAndMorty.characterMovie.RickAndMortyCharacter;
import rickAndMorty.characterMovie.RickAndMortyEpisode;
import rickAndMorty.characterMovie.SingleCharacter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CharacterSteps {

    private static final CharacterApi characterApi = new CharacterApi();

    public RickAndMortyCharacter getCharacterByName(String name) {
        return characterApi.getNameCharacter(name) // Передаем имя персонажа
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyCharacter.class);
    }

    public int getMaxEpisodeNumber(Result character) {
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

    public RickAndMortyEpisode getEpisodeById(int episodeId) {
        return characterApi.getEpisodeById(String.valueOf(episodeId))
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(RickAndMortyEpisode.class);
    }

    public SingleCharacter getCharacterByUrl(String url) {
        return characterApi.getCharacterByUrl(url)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(SingleCharacter.class);
    }
}
