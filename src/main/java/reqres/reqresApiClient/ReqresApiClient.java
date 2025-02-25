package reqres.reqresApiClient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reqres.pojoObject.UserRequest;

public class ReqresApiClient {

    private static final String BASE_URL = "https://reqres.in/api";

    public static Response createUser(UserRequest userRequest) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userRequest)
                .post(BASE_URL + "/users");
    }
}
