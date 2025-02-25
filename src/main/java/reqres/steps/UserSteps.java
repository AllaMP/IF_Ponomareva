package reqres.steps;

import io.restassured.response.Response;
import reqres.pojoObject.UserRequest;
import reqres.pojoObject.UserResponse;
import reqres.reqresApiClient.ReqresApiClient;

public class UserSteps {

    public static UserResponse createUser(String name, String job) {

        UserRequest userRequest = new UserRequest(name, job);

        Response response = ReqresApiClient.createUser(userRequest);

        response.then().statusCode(201);

        return response.as(UserResponse.class);
    }

    public static Response createUserResponse(String name, String job) {

        UserRequest userRequest = new UserRequest(name, job);

        return ReqresApiClient.createUser(userRequest);
    }
}
