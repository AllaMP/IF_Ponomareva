package UI_IF.testPage;

import UI_IF.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import UI_IF.webHooks.WebHooks;

public class TestJira extends WebHooks {

    static LoginPage loginPage = new LoginPage();
    private final JiraTestProject jiraTestProject = new JiraTestProject();
    private final NumberOfTasks numberOfTasks = new NumberOfTasks();
    private final CreateNewTest createNewTest = new CreateNewTest();
    private final ChangeStatusTask changeStatusTask = new ChangeStatusTask();
    private final OrderProcessor orderProcessor = new OrderProcessor();

    private static String username;
    private static String password;

    public static void setUsername(String username) {
        TestJira.username = username;
    }

    public static void setPassword(String password) {
        TestJira.password = password;
    }


    @Test
    @DisplayName("Аутентификация пользователя")
    public void testJiraLogin() {
        loginPage.login(username, password);
        Assertions.assertEquals("Проекты", JiraTestProject.getBrowseLink());
    }

    @Test
    @DisplayName("Переход в проект Тест")
    public void testJiraProject() {
        loginPage.login(username, password);
        jiraTestProject.selectProject();
        Assertions.assertEquals("Открытые задачи", JiraTestProject.getOpenTask());
    }

    @Test
    @DisplayName("Создание бага+счетчик задач")
    public void CreateNewTest() {
        loginPage.login(username, password);
        jiraTestProject.selectProject();
        numberOfTasks.tasks();
        createNewTest.create(numberOfTasks.getTotalTasks());
        Assertions.assertEquals("Test", CreateNewTest.getStatusTasks());
    }

    @Test
    @DisplayName("Изменение статуса")
    public void ChangeStatusTask() {
        loginPage.login(username, password);
        jiraTestProject.selectProject();
        changeStatusTask.status();
        Assertions.assertEquals("В РАБОТЕ",  ChangeStatusTask.getstatusChange());
    }

    @Test
    @DisplayName("Проверка задачи TestSeleniumATHomework")
    public void OrderProcessor() {
        loginPage.login(username, password);
        orderProcessor.order();
        Assertions.assertEquals("СДЕЛАТЬ", OrderProcessor.getStatusElement());
        Assertions.assertEquals("Version 2.0", OrderProcessor.getVersionElement());
    }
}
