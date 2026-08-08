package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();
    ResultComponent result = new ResultComponent();

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genterContainer = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement subjectContainer = $("#subjectsInput");
    private final SelenideElement pictureInput = $("input[type='file']");
    private final SelenideElement hobbiesCheckbox = $("#hobbiesWrapper");
    private final SelenideElement currentAddressTextarea = $("#currentAddress");
    private final SelenideElement stateSelect = $("#react-select-3-input");
    private final SelenideElement citySelect = $("#react-select-4-input");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement registrationResult = $("#example-modal-sizes-title-lg");

    @Step("Открыть форму регистрации /automation-practice-form")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public RegistrationFormPage removeBanners() {
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        return this;
    }
    @Step("Ввести Имя {value}")
    public RegistrationFormPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }
    @Step("Ввести фамилию {value}")
    public RegistrationFormPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }
    @Step("Ввести email {value}")
    public RegistrationFormPage typeEmail(String value) {
        emailInput.setValue(value);

        return this;
    }
    @Step("Ввести номер телефона {value}")
    public RegistrationFormPage typeUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }
    @Step("Выбрать пол {value}")
    public RegistrationFormPage setGender(String value) {
        genterContainer.$(byText(value))
                .shouldHave(text(value))
                .click();

        return this;
    }
    @Step("Выбрать дату рождения {day, month, year}")
    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);

        return this;
    }
    @Step("Выбрать предмет {value}")
    public RegistrationFormPage setSubject(String value) {
        subjectContainer.setValue(value).pressEnter();

        return this;
    }
    @Step("Выбрать хобби {value}")
    public RegistrationFormPage setHobbie(String value) {
        hobbiesCheckbox.$(byText(value)).click();

        return this;
    }
    @Step("Текущий адрес {value}")
    public RegistrationFormPage setCurrentAddress(String value) {
        currentAddressTextarea.setValue(value);

        return this;
    }
    @Step("Загрузить картинку {file}")
    public RegistrationFormPage uploadPicture(String file) {
        pictureInput.uploadFromClasspath(file);

        return this;
    }
    @Step("Выбрать штат {value}")
    public RegistrationFormPage setState(String value) {
        stateSelect.setValue(value).pressEnter();

        return this;
    }
    @Step("Выбрать город {value}")
    public RegistrationFormPage setCity(String value) {
        citySelect.setValue(value).pressEnter();

        return this;
    }
    @Step("Выбрать {state} и {city}")
    public RegistrationFormPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }
    @Step("Кликнуть по кнопке")
    public RegistrationFormPage clickButton() {
        submitButton.click();

        return this;
    }
    @Step("Проверить текст после регистрации")
    public RegistrationFormPage textRegistrationResult() {
        registrationResult.shouldHave(text("Thanks for submitting the form"));

        return this;
    }
    @Step("Проверить результат Label {key} и Values {value}")
    public RegistrationFormPage checkResult(String key, String value) {
        result.checkRow(key, value);

        return this;
    }
    @Step("Проверить пустую строку {value}")
    public RegistrationFormPage checkResultEmptyRow(String value) {
        result.checkEmptyRow(value);

        return this;
    }
    @Step("Проверить валидацию в поле State {selector}")
    public RegistrationFormPage checkStateTables(String selector) {
        $(selector).shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        return this;
    }
    @Step("Проверить валидацию в поле Gender")
    public RegistrationFormPage checkGenderError() {
        genterContainer.$$(".col-md-9 col-sm-12")
                .forEach(label ->
                        label.shouldHave(cssValue("border-color", "rgba(220, 53, 69)"))
                );
        return this;
    }

}
