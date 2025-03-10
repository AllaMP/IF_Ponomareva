package reqres.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import reqres.pojoObject.UserRequest;
import reqres.pojoObject.UserResponse;
import reqres.reqresApiClient.ReqresApiClient;

import java.io.File;
import java.io.IOException;

public class CreatingUserFromJsonSteps {

    private UserRequest userRequest;
    private Response response;
    private UserResponse userResponse;

    private final ReqresApiClient reqresApiClient = new ReqresApiClient();


    @Дано("Пользователь с данными из JSON-файла {string}")
    public void createUserRequestFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        userRequest = objectMapper.readValue(file, UserRequest.class);
    }

    @Тогда("Отправляется запрос на создание нового пользователя")
    public void sendCreateUserRequest() {
        response = reqresApiClient.createUser(userRequest);
    }


    @Тогда("Ожидаю статус-код ответа {int}")
    public void checkStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Неверный статус-код ответа");
    }

    @И("Пользователь успешно создан")
    public void deserializeResponse() {
        userResponse = response.as(UserResponse.class);
        Assertions.assertNotNull(userResponse.getId(), "ID пользователя не должен быть null");
        Assertions.assertNotNull(userResponse.getCreatedAt(), "Дата создания не должна быть null");
    }

    @Тогда("Вывожу данные ответа")
    public void printResponseData() {
        System.out.println("\nДанные ответа:");
        System.out.println("ID: " + userResponse.getId());
        System.out.println("Имя: " + userResponse.getName());
        System.out.println("Работа: " + userResponse.getJob());
        System.out.println("Дата создания: " + userResponse.getCreatedAt());
    }
}
