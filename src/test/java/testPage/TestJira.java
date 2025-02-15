package testPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import webHooks.WebHooks;

public class TestJira extends WebHooks {

    public static String username;
    public static String password;

    static LoginPage loginPage = new LoginPage();
    private final JiraTestProject jiraTestProject = new JiraTestProject();
    private final NumberOfTasks numberOfTasks = new NumberOfTasks();
    private final CreateNewTest createNewTest = new CreateNewTest();
    private final ChangeStatusTask changeStatusTask = new ChangeStatusTask();
    private final OrderProcessor orderProcessor = new OrderProcessor();


    @DisplayName("Аутентификация пользователя")
    @Test
    public void testJiraLogin() {
        loginPage.login(username, password);
        Assertions.assertEquals("Проекты", JiraTestProject.getBrowseLink());
    }

    @DisplayName("Переход в проект Тест")
    @Test
    public void testJiraProject() {
        loginPage.login(username, password);
        jiraTestProject.selectProject();
        Assertions.assertEquals("Открытые задачи", JiraTestProject.getOpenTask());
    }

    @DisplayName("Создание бага+счетчик задач")
    @Test
    public void CreateNewTest() {
        loginPage.login(username, password);
        jiraTestProject.selectProject();
        numberOfTasks.tasks();
        createNewTest.create(numberOfTasks.getTotalTasks());
        Assertions.assertEquals("Test", CreateNewTest.getStatusTasks());
    }

    @DisplayName("Изменение статуса")
    @Test
    public void ChangeStatusTask() {
        loginPage.login(username, password);
        jiraTestProject.selectProject();
        changeStatusTask.status();
        Assertions.assertEquals("В РАБОТЕ",  ChangeStatusTask.getstatusChange());
    }

    @DisplayName("Проверка задачи TestSeleniumATHomework")
    @Test
    public void OrderProcessor() {
        loginPage.login(username, password);
        orderProcessor.order();
        Assertions.assertEquals("СДЕЛАТЬ", OrderProcessor.getStatusElement());
        Assertions.assertEquals("Version 2.0", OrderProcessor.getVersionElement());
    }
}
