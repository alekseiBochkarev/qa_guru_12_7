package pages;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class IssuesPage {
    public void issueNumberClick(String value) {
        $(withText(value)).click();
    }
}
