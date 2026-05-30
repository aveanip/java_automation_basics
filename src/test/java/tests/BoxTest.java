package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BoxTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void successfulFillFormTest() {
        open("/text-box");

        $("[id=userName]").setValue("Ivanov Ivan");
        $("[id=userEmail]").setValue("ivanovi@ya.ru");
        $("[id=currentAddress]").setValue("221b, Baker Street, London");
        $("[id=permanentAddress]").setValue("220, Oxford Street, London");

//    $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Ivanov Ivan"));
        $("[id=output] [id=email]").shouldHave(text("ivanovi@ya.ru"));
        $("[id=output] [id=currentAddress]").shouldHave(text("221b, Baker Street, London"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("220, Oxford Street, London"));
    }

    @Test
    void checkingFormWithIncorrectEmail() {
        open("/text-box");

        $("[id=userName]").setValue("Ivanov Ivan");
        $("[id=userEmail]").setValue("ivanoviya.ru");
        $("[id=currentAddress]").setValue("221b, Baker Street, London");
        $("[id=permanentAddress]").setValue("220, Oxford Street, London");
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(255, 0, 0)"));
    }

    @AfterEach
    void tearDown () {
        Selenide.closeWebDriver();
    }
}
