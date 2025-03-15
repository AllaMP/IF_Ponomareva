package rickAndMorty.api;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import utils.ConfigLoader;

import static io.restassured.RestAssured.given;

public class CharacterApi {

    private final String rickAndMortyUrl;

    public CharacterApi() {
        ConfigLoader configLoader = new ConfigLoader();
        this.rickAndMortyUrl = configLoader.getRickAndMortyApiUrl();
    }

    public ValidatableResponse getNameCharacter(String name) {
        return given()
                .baseUri(rickAndMortyUrl)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .queryParam("name", name)
                .when()
                .get("/character")
                .then();
    }


    public ValidatableResponse getEpisodeById(String episodeId) {
        return given()
                .baseUri(rickAndMortyUrl)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get("/episode/" + episodeId)
                .then();
    }

    public ValidatableResponse getCharacterByUrl(String url) {
        return given()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get(url)
                .then();
    }
}

