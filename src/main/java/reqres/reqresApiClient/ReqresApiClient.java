package reqres.reqresApiClient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reqres.pojoObject.UserRequest;
import utils.ConfigLoader;


public class ReqresApiClient {

    private final String baseUrl;

    public ReqresApiClient() {
        ConfigLoader configLoader = new ConfigLoader();
        this.baseUrl = configLoader.getReqresApiClientUrl();
    }

    public Response createUser(UserRequest userRequest) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userRequest)
                .post(baseUrl + "/users");
    }
}
