package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import tests.testsdata.TestData;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static tests.testsdata.TestData.*;


public class BoxTest extends BaseTest {
    @Test
    @DisplayName("Проверка формы TextBox при заполнении всех полей валидными данными")
    void successfulFillFormTest() {
        step("Открыть форму Text Box", () -> {
            textBoxPage.openPage()
                    .removeBanners();
        });
        step("Заполнить все поля формы валидными данными", () -> {
            textBoxPage
                    .typeUserName(testData.userName)
                    .typeUserEmail(testData.userEmail)
                    .currentAddressTextarea(testData.currentAddress)
                    .permanentAddressTextarea(testData.permanentAddress);
        });
        step("Нажать на кнопку Submit", () -> {
            textBoxPage
                    .submitForm();
        });
        step("Проверить заполненные данные в модальном окне", () -> {
            textBoxPage
                    .checkFirld("email", testData.userEmail)
                    .checkFirld("currentAddress", testData.currentAddress)
                    .checkFirld("permanentAddress", testData.permanentAddress);
        });
    }

    @Test
    @DisplayName("Проверка формы TextBox при заполнении поля email невалидным значением")
    void checkingFormWithIncorrectEmail() {
        step("Открыть форму Text Box", () -> {
            textBoxPage.openPage()
                    .removeBanners();
        });
        step("Заполнить поле User name валидными данными", () -> {
            textBoxPage
                    .typeUserName(testData.userName);
        });
        step("Заполнить поле Email name невалидными данными", () -> {
            textBoxPage
                    .typeUserEmail(testData.invalidMail);
        });
        step("Заполнить поле User name валидными данными", () -> {
            textBoxPage
                    .currentAddressTextarea(testData.currentAddress)
                    .permanentAddressTextarea(testData.permanentAddress);
        });
        step("Нажать на кнопку Submit", () -> {
            textBoxPage
                    .submitForm();
        });
        step("Проверить что в модальном окне поле email подсвечено красным,", () -> {
            textBoxPage
                    .dataValidation();
        });
    }
}
