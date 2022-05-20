import com.beust.ah.A;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.BaseSetup;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AlekseiBochkarevPage;
import pages.IssuesPage;
import pages.SearchPage;
import pages.StartPage;

public class SelenideAllureTest extends BaseSetup {
    /***pages***/
    StartPage startPage = new StartPage();
    SearchPage searchPage = new SearchPage();
    AlekseiBochkarevPage alekseiBochkarevPage = new AlekseiBochkarevPage();
    IssuesPage issuesPage = new IssuesPage();

    @BeforeEach
    void openPage () {
        startPage.openPage();
    }

    @Test
    void githubIssueListenerTest () {
        SelenideLogger.addListener("allure", new AllureSelenide());
        startPage.searchValue("alekseiBochkarev/junit-lesson-project");
        searchPage.linkedTextClick("alekseiBochkarev/junit-lesson-project");
        alekseiBochkarevPage.issueMenuClick("Issues");
        issuesPage.issueNumberClick("#3");
    }
}
