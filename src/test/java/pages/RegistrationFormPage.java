package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();
    ResultComponent result = new ResultComponent();

    private SelenideElement firstNameInput = $("[id=firstName]");
    private SelenideElement lastNameInput = $("[id=lastName]");
    private SelenideElement emailInput = $("[id=userEmail]");
    private SelenideElement genterContainer = $("#genterWrapper");
    private SelenideElement userNumberInput = $("[id=userNumber]");
    private SelenideElement subjectContainer = $("[id=subjectsInput]");
    private SelenideElement pictureInput = $("input[type='file']");
    private SelenideElement hobbiesCheckbox = $("#hobbiesWrapper");
    private SelenideElement currentAddressTextarea = $("[id=currentAddress]");
    private SelenideElement stateSelect = $("[id=react-select-3-input]");
    private SelenideElement citySelect = $("[id=react-select-4-input]");
    private SelenideElement submitButton = $("[id=submit]");
    private SelenideElement registrationResult = $("[id=example-modal-sizes-title-lg]");

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        return this;
    }

    public RegistrationFormPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationFormPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationFormPage typeEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationFormPage typeUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genterContainer.$(byText(value))
                .shouldHave(text(value))
                .click();

        return this;
    }

    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        $("[id=dateOfBirthInput]").click();
        calendar.setDate(day, month, year);

        return this;
    }

    public RegistrationFormPage setSubject(String value) {
        subjectContainer.setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage setHobbie(String value) {
        hobbiesCheckbox.$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setcurrentAddress(String value) {
        currentAddressTextarea.setValue(value);

        return this;
    }

    public RegistrationFormPage uploadPicture(String file) {
        pictureInput.uploadFromClasspath(file);

        return this;
    }

    public RegistrationFormPage setState(String value) {
        stateSelect.setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage setCity(String value) {
        citySelect.setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    public RegistrationFormPage clickButton() {
        submitButton.click();

        return this;
    }

    public RegistrationFormPage textRegistrationResult() {
        registrationResult.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        result.checkRow(key, value);

        return this;
    }

    public RegistrationFormPage checkStateTables(String selector) {
        $(selector).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        return this;
    }

    public RegistrationFormPage checkGenderError() {
        genterContainer.$$(".col-md-9 col-sm-12")
                .forEach(label ->
                label.shouldHave(cssValue("border-color", "rgba(220, 53, 69)"))
        );
        return this;
    }

}
