package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultComponent {

    public SelenideElement textresult = $(".table-responsive");

    public ResultComponent checkRow(String name, String value) {
        textresult.$(byText(name)).parent().shouldHave(text(value));
        return this;
    }

    public ResultComponent checkEmptyRow(String name) {
        textresult.$(byText(name)).sibling(0).shouldBe(empty);
        return this;
    }
}
