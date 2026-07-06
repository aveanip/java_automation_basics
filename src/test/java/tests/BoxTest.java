package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static com.codeborne.selenide.Selenide.*;
import static tests.testsdata.TestData.*;


public class BoxTest extends BaseTest {
    TextBoxPage textBoxPage = new TextBoxPage();


    @Test
    void successfulFillFormTest() {
        textBoxPage.openPage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .currentAddressTextarea(currentAddress)
                .permanentAddressTextarea(permanentAddress)
                .submitForm()
                .checkFirld("name", userName)
                .checkFirld("email", userEmail)
                .checkFirld("currentAddress", currentAddress)
                .checkFirld("permanentAddress", permanentAddress);
    }

    @Test
    void checkingFormWithIncorrectEmail() {
        textBoxPage.openPage()
                .typeUserName(userName)
                .typeUserEmail(invalidMail)
                .currentAddressTextarea(currentAddress)
                .permanentAddressTextarea(permanentAddress)
                .submitForm()
                .dataValidation();

    }
}
