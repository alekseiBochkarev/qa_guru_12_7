package pages;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class SearchPage {

    public void linkedTextClick(String value) {
        $(linkText(value)).click();
    }

}
