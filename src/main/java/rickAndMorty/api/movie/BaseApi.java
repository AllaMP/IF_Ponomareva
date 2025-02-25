package rickAndMorty.api.movie;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import rickAndMorty.api.Specifications;
import rickAndMorty.constants.EnvConstants;

public abstract class BaseApi {

    public BaseApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.RICKANDMORTY_URL);
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}

