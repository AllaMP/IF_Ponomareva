package reqres.reqresTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reqres.pojoObject.UserRequest;
import reqres.pojoObject.UserResponse;
import reqres.steps.UserSteps;

import java.io.File;
import java.io.IOException;

public class ReqresUserTest {

    @Test
    @DisplayName("Проверка создания пользователя с данными из JSON-файла")
    public void testCreateUserFromJsonFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/user.json");
        UserRequest userRequest = objectMapper.readValue(file, UserRequest.class);
        UserResponse userResponse = UserSteps.createUser(userRequest.getName(), userRequest.getJob());

        Assertions.assertNotNull(userResponse.getId(), "ID пользователя не должен быть null");
        Assertions.assertNotNull(userResponse.getCreatedAt(), "Дата создания не должна быть null");
        Assertions.assertEquals(userRequest.getName(), userResponse.getName(), "Имя пользователя не совпадает");
        Assertions.assertEquals(userRequest.getJob(), userResponse.getJob(), "Работа пользователя не совпадает");

        System.out.println("\nДанные ответа:");
        System.out.println("ID: " + userResponse.getId());
        System.out.println("Имя: " + userResponse.getName());
        System.out.println("Работа: " + userResponse.getJob());
        System.out.println("Дата создания: " + userResponse.getCreatedAt());
    }


    @Test
    @DisplayName("Проверка создания пользователя с измененными данными из JSON")
    public void testCreateUserWithModifiedJson() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/user.json");
        UserRequest userRequest = objectMapper.readValue(file, UserRequest.class);


        userRequest.setName("Tomato");
        userRequest.setJob("Eat maket");


        Response response = UserSteps.createUserResponse(userRequest.getName(), userRequest.getJob());
        UserResponse userResponse = response.as(UserResponse.class);


        response.then().statusCode(201);
        int statusCode = 201;

        Assertions.assertNotNull(userResponse.getId(), "ID пользователя не должен быть null");
        Assertions.assertNotNull(userResponse.getCreatedAt(), "Дата создания не должна быть null");
        Assertions.assertEquals(201, response.getStatusCode(),
                "Статус ответа должен быть 201 (Created)");
        Assertions.assertEquals("Tomato", userResponse.getName(),
                "Имя пользователя не соответствует ожидаемому");
        Assertions.assertEquals("Eat maket", userResponse.getJob(),
                "Работа пользователя не соответствует ожидаемой");

        System.out.println("Статус ответа: " + statusCode);
        System.out.println("\nДанные ответа:");
        System.out.println("ID: " + userResponse.getId());
        System.out.println("Имя: " + userResponse.getName());
        System.out.println("Работа: " + userResponse.getJob());
        System.out.println("Дата создания: " + userResponse.getCreatedAt());
    }
}
