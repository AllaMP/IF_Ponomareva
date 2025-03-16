package reqres.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import reqres.pojoObject.UserRequest;
import reqres.pojoObject.UserResponse;
import reqres.reqresApiClient.ReqresApiClient;


public class ChangedByUserDataSteps {

    private UserRequest userRequest;
    private Response response;
    private UserResponse userResponse;

    private final ReqresApiClient reqresApiClient = new ReqresApiClient();

    @Дано("Пользователь с именем {string} и должностью {string}")
    @Step("Пользователь с именем {string} и должностью {string}")
    public void createUserRequest(String name, String job) {
        userRequest = new UserRequest(name, job);
    }

    @Тогда("Отправляется запрос на создание пользователя")
    @Step("Отправляется запрос на создание пользователя")
    public void sendCreateUserRequest() {
        response = reqresApiClient.createUser(userRequest);
    }

    @И("Получаю статус-код ответа {int}")
    @Step("Получаю статус-код ответа {int}")
    public void checkStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Неверный статус-код ответа");
    }

    @И("Подтверждаю, что пользователь успешно создан")
    @Step("Подтверждаю, что пользователь успешно создан")
    public void confirmUserCreated() {
        userResponse = response.as(UserResponse.class);
        Assertions.assertNotNull(userResponse.getId(), "ID пользователя не должен быть null");
        Assertions.assertNotNull(userResponse.getCreatedAt(), "Дата создания не должна быть null");
    }

    @И("Получаю имя пользователя {string}")
    @Step("Получаю имя пользователя {string}")
    public void checkUserName(String expectedName) {
        Assertions.assertEquals(expectedName, userResponse.getName(), "Имя пользователя не совпадает");
    }

    @И("Получаю должность пользователя {string}")
    @Step("Получаю должность пользователя {string}")
    public void checkUserJob(String expectedJob) {
        Assertions.assertEquals(expectedJob, userResponse.getJob(), "Работа пользователя не совпадает");
    }

    @Тогда("Предоставляю данные ответа")
    @Step("Предоставляю данные ответа")
    public void printResponseData() {
        System.out.println("\nДанные ответа:");
        System.out.println("ID: " + userResponse.getId());
        System.out.println("Имя: " + userResponse.getName());
        System.out.println("Работа: " + userResponse.getJob());
        System.out.println("Дата создания: " + userResponse.getCreatedAt());
    }
}