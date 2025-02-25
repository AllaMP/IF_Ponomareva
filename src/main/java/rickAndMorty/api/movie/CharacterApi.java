package rickAndMorty.api.movie;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static rickAndMorty.constants.EnvConstants.RICKANDMORTY_URL;

public class CharacterApi{

    public ValidatableResponse getNameCharacter(String name) {
        return given()
                .baseUri(RICKANDMORTY_URL)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .queryParam("name", name)
                .when()
                .get("/character")
                .then();
    }

    public ValidatableResponse getEpisodeById(String episodeId) {
        return given()
                .baseUri(RICKANDMORTY_URL)
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

