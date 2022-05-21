import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.TakeScreenshot;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.BaseSetup;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.AlekseiBochkarevPage;
import pages.IssuesPage;
import pages.SearchPage;
import pages.StartPage;

import java.nio.charset.StandardCharsets;

import static io.qameta.allure.Allure.step;

public class SelenideAllureTest extends BaseSetup {
    /***pages***/
    StartPage startPage = new StartPage();
    SearchPage searchPage = new SearchPage();
    AlekseiBochkarevPage alekseiBochkarevPage = new AlekseiBochkarevPage();
    IssuesPage issuesPage = new IssuesPage();
    /***TestData***/
    public static final String REPOSITORY = "alekseiBochkarev/junit-lesson-project";
    public static final String TABNAME = "Issues";
    public static final String ISSUENUMBER = "#3";

    @BeforeEach
    void openPage () {
        step ("открываем главную страницу", () -> {
            startPage.openPage();
                });
    }

    @Test
    @Owner("AlekseiBochkarev")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Задачи в репозитории")
    @Story("Просмотр задач в репозитории")
    @DisplayName("Мой любимый тест")
    @Link(value = "репозиторий с тестом", url = "https://github.com/alekseiBochkarev/qa_guru_12_7")
    void githubIssueListenerTest () {
        SelenideLogger.addListener("allure", new AllureSelenide());
        startPage.searchValue("alekseiBochkarev/junit-lesson-project");
        searchPage.linkedTextClick("alekseiBochkarev/junit-lesson-project");
        alekseiBochkarevPage.issueMenuClick("Issues");
        issuesPage.issueNumberCheck("#3");
        Allure.getLifecycle().addAttachment(
                "Исходники страницы",
                "test/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
        );
    }

    @Test
    @IssueShow
    void githubIssueStepTest () {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Ищем репозиторий " + REPOSITORY, () -> {
            startPage.searchValue(REPOSITORY);

        });
        step("Переходим по ссылке репозитория" + REPOSITORY, () -> {
            searchPage.linkedTextClick(REPOSITORY);
            attachScreenshot();
        });
        step("Кликаем на таб ISSUES", () -> {
            alekseiBochkarevPage.issueMenuClick("Issues");
        });
        step("Проверяем ISSUE с номером " + ISSUENUMBER, () -> {
            issuesPage.issueNumberCheck(ISSUENUMBER);
        });
    }

    @Test
    @IssueShow
    void githubIssueWebSteps () {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.searchValue(REPOSITORY);
        steps.linkedTextClick(REPOSITORY);
        steps.issueMenuClick(TABNAME);
        steps.issueNumberCheck(ISSUENUMBER);
    }

    @Attachment (value = "мой супер скриншот", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot () {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Owner("AlekseiBochkarev")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Задачи в репозитории")
    @Story("Просмотр задач в репозитории")
    public @interface IssueShow {

    }

    @Test
    void testCode () {
        Allure.label("owner", "abochkarev");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.feature("new tasks");
        Allure.story("new story");
        Allure.link("репозиторий с тестом", "https://github.com/alekseiBochkarev/qa_guru_12_7");
    }

    @Test
    void annotatedTest () {
        Allure.feature("new tasks");
        Allure.story("annotateParameter story");
        Allure.parameter("Регион", "Московская область");
        Allure.parameter("Город", "Москва");
    }

    @CsvSource({
            "firstValue, firstExpected",
            "secondValue, secondExpected"
    })
    @ParameterizedTest(name ="Проверяем {0} Ожидаем {1}")
    @Feature("new tasks")
    @Story("parameteredTest and parametered annotation")
    void parameterTest (String testData, String expectedResult) {
        Allure.parameter(testData, expectedResult);
    }
}
