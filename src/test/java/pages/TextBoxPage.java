package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;


public class TextBoxPage {

    private final SelenideElement userNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement currentAddressTextarea = $("#currentAddress");
    private final SelenideElement permanentAddressTextarea = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputResults = $("#output");

    public TextBoxPage openPage() {
        open("/text-box");

        return this;
    }

    public TextBoxPage removeBanners() {
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

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

    public TextBoxPage dataValidation() {
        userEmailInput.shouldHave(cssValue("border-color",
                "rgb(255, 0, 0)"));

        return this;
    }
}
