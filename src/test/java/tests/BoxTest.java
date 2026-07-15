package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import tests.testsdata.TestData;

import static com.codeborne.selenide.Selenide.*;
import static tests.testsdata.TestData.*;


public class BoxTest extends BaseTest {
    TextBoxPage textBoxPage = new TextBoxPage();
    TestData testData = new TestData();

    @Test
    void successfulFillFormTest() {
        textBoxPage.openPage()
                .removeBanners()
                .typeUserName(testData.userName)
                .typeUserEmail(testData.userEmail)
                .currentAddressTextarea(testData.currentAddress)
                .permanentAddressTextarea(testData.permanentAddress)
                .submitForm()
                .checkFirld("name", testData.userName)
                .checkFirld("email", testData.userEmail)
                .checkFirld("currentAddress", testData.currentAddress)
                .checkFirld("permanentAddress", testData.permanentAddress);
    }

    @Test
    void checkingFormWithIncorrectEmail() {
        textBoxPage.openPage()
                .removeBanners()
                .typeUserName(testData.userName)
                .typeUserEmail(testData.invalidMail)
                .currentAddressTextarea(testData.currentAddress)
                .permanentAddressTextarea(testData.permanentAddress)
                .submitForm()
                .dataValidation();

    }
}
