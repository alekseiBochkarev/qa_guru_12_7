package pages;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.partialLinkText;

public class AlekseiBochkarevPage {
    public void issueMenuClick (String value) {
        $(partialLinkText(value)).click();
    }
}
