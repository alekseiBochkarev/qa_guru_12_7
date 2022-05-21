import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {
    SelenideElement searchInputField =$("input[data-test-selector=nav-search-input]");

    @Step("Ищем репозиторий {value}")
    public void searchValue (String value) {
        searchInputField.click();
        searchInputField.setValue(value).submit();
    }

    @Step("Переходим по ссылке репозитораия {value}")
    public void linkedTextClick(String value) {
        $(linkText(value)).click();
    }


    @Step("Кликаем на таб {value}")
    public void issueMenuClick(String value) {
        $(partialLinkText(value)).shouldBe(Condition.visible).click();
    }

    @Step("Проверяем наличие issue с номером {value}")
    public void issueNumberCheck(String value) {
        $(withText(value)).shouldBe(Condition.visible);
    }
}
