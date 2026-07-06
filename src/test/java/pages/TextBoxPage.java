package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TextBoxPage {

    private SelenideElement userNameInput = $("#userName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement currentAddressTextarea = $("#currentAddress");
    private SelenideElement permanentAddressTextarea = $("#permanentAddress");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement outputResults = $("#output");

    public TextBoxPage openPage() {
        open("/text-box");

        return this;
    }

    public TextBoxPage typeUserName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    public TextBoxPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxPage currentAddressTextarea(String value) {
        currentAddressTextarea.setValue(value);

        return this;
    }

    public TextBoxPage permanentAddressTextarea(String value) {
        permanentAddressTextarea.setValue(value);

        return this;
    }

    public TextBoxPage submitForm() {
        submitButton.click();

        return this;
    }

    public TextBoxPage checkFirld(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));

        return this;
    }

    public TextBoxPage dataValidation(){
        userEmailInput.shouldHave(cssValue("border-color",
                "rgb(255, 0, 0)"));

        return this;
    }
}
