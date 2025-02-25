package rickAndMorty.api.movie;


import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import rickAndMorty.constants.EnvConstants;

import static io.restassured.RestAssured.given;

public class CharacterApi extends BaseApi {

    public static final String RICKANDMORTY_URN = "/character";

    public ValidatableResponse getNameCharacter(String name) {
        return given()
                .baseUri(EnvConstants.RICKANDMORTY_URL)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .queryParam("name", name)
                .when()
                .get(RICKANDMORTY_URN + "/")
                .then();
    }
}
