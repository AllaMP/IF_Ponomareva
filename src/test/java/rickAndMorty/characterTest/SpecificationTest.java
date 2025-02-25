package rickAndMorty.characterTest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import rickAndMorty.api.Specifications;
import rickAndMorty.constants.EnvConstants;

public class SpecificationTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.RICKANDMORTY_URL);
        RestAssured.responseSpecification = Specifications.baseResponseSpecSuccess();
    }



}
