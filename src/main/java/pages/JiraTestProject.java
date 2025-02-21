package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class JiraTestProject {

    public static final SelenideElement projectLink = $x("//a[@id='browse_link']").
            as("Ссылка на проекты");
    public static final SelenideElement testProjectLink = $x("//a[@id='admin_main_proj_link_lnk']").
            as("Ссылка на проект 'Test'");
    private static final SelenideElement openTask = $x("//span[@id='issues-subnavigation-title']").
            as("Элемент 'Открытые задачи'");
}

