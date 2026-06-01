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

public class RegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    @DisplayName("Проверка формы регистрации при заполнении всех полей")
    void successfulRegistrationFormTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue("Ivan");
        $("[id=lastName]").setValue("Ivanov");
        $("[id=userEmail]").setValue("ivanovi@gmail.com");

        $("#genterWrapper").$("[value='Female']").click();

        $("[id=userNumber]").setValue("8999410911");
        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").$(byText("March")).click();
        $(".react-datepicker__year-select").$(byText("1996")).click();
        $(".react-datepicker__month").$(byText("24")).click();

        $("[id=subjectsInput]").setValue("Physics").pressEnter();
        $("[id=hobbies-checkbox-2]").click();

        $("input[type='file']").uploadFromClasspath("files/foto.jpg");

        $("[id=currentAddress]").setValue("Moscow");
        $("[id=react-select-3-input]").setValue("Uttar Pradesh").pressEnter();
        $("[id=react-select-4-input]").setValue("Agra").pressEnter();

        $("[id=submit]").click();
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));


        $(".table-responsive").shouldHave(text("Ivan Ivanov"));
        $(".table-responsive").shouldHave(text("ivanovi@gmail.com"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("8999410911"));
        $(".table-responsive").shouldHave(text("24 March,1996"));
        $(".table-responsive").shouldHave(text("Physics"));
        $(".table-responsive").shouldHave(text("Reading"));
        $(".table-responsive").shouldHave(text("foto.jpg"));
        $(".table-responsive").shouldHave(text("Moscow"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh Agra"));
    }

    @Test
    @DisplayName("Проверка формы регистрации при заполнении только обязательных полей")
    void registrationWithRequiredFieldsTest() {
        open("/automation-practice-form");

        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue("Anna");
        $("[id=lastName]").setValue("Smernova");
        $("[id=userEmail]").setValue("smernovaa@gmail.com");

        $("#genterWrapper").$("[value='Male']").click();


        $("[id=userNumber]").setValue("4123456891");

//        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Anna Smernova"));
        $(".table-responsive").shouldHave(text("smernovaa@gmail.com"));
        $(".table-responsive").shouldHave(text("4123456891"));
    }

    @Test
    @DisplayName("Проверка цвета рамки при невалидном номере телефона")
    void phoneFieldWithInvalidUserNumberShowsRedBorderColorTest() {

        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);

        $("[id=firstName]").setValue("Anna");
        $("[id=lastName]").setValue("Smernova");
        $("[id=userEmail]").setValue("smernovaa@gmail.com");

        $("#genterWrapper").$("[value='Male']").click();

        $("[id=userNumber]").setValue("41");
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

        $("[id=firstName]").setValue("Anna");
        $("[id=lastName]").setValue("Smernova");
        $("[id=userEmail]").setValue("smernovaagmail.com");

        $("#genterWrapper").$("[value='Male']").click();

        $("[id=userNumber]").setValue("4123456987");

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

        $("[id=firstName]").setValue("Anna");
        $("[id=lastName]").setValue("Smernova");
        $("[id=userEmail]").setValue("smernovaa@.com");

        $("#genterWrapper").$("[value='Male']").click();

        $("[id=userNumber]").setValue("4123456987");

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
        $("#genterWrapper").$("[value='Male']")
                .shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$("[value='Female']")
                .shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$("[value='Other']")
                .shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
