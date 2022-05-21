package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class IssuesPage {
    public void issueNumberCheck(String value) {
        $(withText(value)).shouldBe(Condition.visible);
    }
}
