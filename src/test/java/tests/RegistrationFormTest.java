package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.Scroll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
import static tests.testsdata.TestData.*;

public class RegistrationFormTest extends BaseTest{

    @Test
    @DisplayName("Проверка формы регистрации при заполнении всех полей")
    void successfulRegistrationFormTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(email);

        $("#genterWrapper").$(byText(genter)).click();

        $("[id=userNumber]").setValue(userNumber);
        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").$(byText(birthMonth)).click();
        $(".react-datepicker__year-select").$(byText(birthYear)).click();
        $(".react-datepicker__month").$(byText(birthDay)).click();

        $("[id=subjectsInput]").setValue(subject).pressEnter();

        $("#hobbiesWrapper").$(byText(hobbie)).click();


        $("input[type='file']").uploadFromClasspath("files/foto.jpg");

        $("[id=currentAddress]").setValue(address);
        $("[id=react-select-3-input]").setValue(state).pressEnter();
        $("[id=react-select-4-input]").setValue(city).pressEnter();

        $("[id=submit]").click();
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));


        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text(genter));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text(birthDay + " " + birthMonth + "," + birthYear));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text(hobbie));
        $(".table-responsive").shouldHave(text("foto.jpg"));
        $(".table-responsive").shouldHave(text(address));
        $(".table-responsive").shouldHave(text(state + " " + city));
    }

    @Test
    @DisplayName("Проверка формы регистрации при заполнении только обязательных полей")
    void registrationWithRequiredFieldsTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(email);

        $("#genterWrapper").$(byText(genter)).click();


        $("[id=userNumber]").setValue(userNumber);

//        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text(userNumber));
    }

    @Test
    @DisplayName("Проверка цвета рамки при невалидном номере телефона")
    void phoneFieldWithInvalidUserNumberShowsRedBorderColorTest() {

        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(email);

        $("#genterWrapper").$(byText(genter)).click();

        $("[id=userNumber]").setValue(invalidPhone);
//        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }


    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без @")
    void wrongUserEmailMissingCharTest() {

        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(invalidEmailForRegistration);

        $("#genterWrapper").$(byText(genter)).click();

        $("[id=userNumber]").setValue(userNumber);

//        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без доменной части")
    void emailWithoutADomainTest() {

        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(emailWithoutDomain);

        $("#genterWrapper").$(byText(genter)).click();

        $("[id=userNumber]").setValue(userNumber);

//        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    @DisplayName("Пустая форма регистрации")
    void emptyRegistrationForm() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

//        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("[id=firstName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=lastName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$(byText("Male"))
                .shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$(byText("Female"))
                .shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$(byText("Other"))
                .shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

    }
}
