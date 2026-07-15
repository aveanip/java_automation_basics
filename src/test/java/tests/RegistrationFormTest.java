package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;
import tests.testsdata.TestData;

public class RegistrationFormTest extends BaseTest {
    RegistrationFormPage textRegistrationFormPage = new RegistrationFormPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Проверка формы регистрации при заполнении всех полей")
    void successfulRegistrationFormTest() {
        textRegistrationFormPage.openPage()
                .removeBanners()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.email)
                .setGender(testData.genter)
                .typeUserNumber(testData.userNumber)
                .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                .setSubject(testData.subject)
                .setHobbie(testData.hobbie)
                .uploadPicture(testData.picture)
                .setCurrentAddress(testData.address)
                .setStateAndCity(testData.state, testData.city)
                .clickButton()
                .textRegistrationResult()
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.email)
                .checkResult("Gender", testData.genter)
                .checkResult("Mobile", testData.userNumber)
                .checkResult("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.hobbie)
                .checkResult("Picture", testData.pictureName)
                .checkResult("Address", testData.address)
                .checkResult("State and City", testData.state + " " + testData.city);
    }

    @Test
    @DisplayName("Проверка формы регистрации при заполнении только обязательных полей")
    void registrationWithRequiredFieldsTest() {
        textRegistrationFormPage.openPage()
                .removeBanners()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(testData.genter)
                .typeUserNumber(testData.userNumber)
                .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                .clickButton()
                .textRegistrationResult()
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResultEmptyRow("Student Email")
                .checkResult("Gender", testData.genter)
                .checkResult("Mobile", testData.userNumber)
                .checkResult("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                .checkResultEmptyRow("Subjects")
                .checkResultEmptyRow("Hobbies")
                .checkResultEmptyRow("Picture")
                .checkResultEmptyRow("Address")
                .checkResultEmptyRow("State and City");
    }

    @Test
    @DisplayName("Проверка цвета рамки при невалидном номере телефона")
    void phoneFieldWithInvalidUserNumberShowsRedBorderColorTest() {
        textRegistrationFormPage.openPage()
                .removeBanners()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.email)
                .setGender(testData.genter)
                .typeUserNumber(testData.invalidPhone)
                .clickButton()
                .checkStateTables("#userNumber");
    }


    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без @")
    void wrongUserEmailMissingCharTest() {
        textRegistrationFormPage.openPage()
                .removeBanners()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.invalidEmailForRegistration)
                .setGender(testData.genter)
                .clickButton()
                .checkStateTables("#userEmail");
    }

    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без доменной части")
    void emailWithoutADomainTest() {
        textRegistrationFormPage.openPage()
                .removeBanners()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.emailWithoutDomain)
                .setGender(testData.genter)
                .clickButton()
                .checkStateTables("#userEmail");
    }

    @Test
    @DisplayName("Пустая форма регистрации")
    void emptyRegistrationForm() {
        textRegistrationFormPage.openPage()
                .removeBanners()
                .clickButton()
                .checkStateTables("#firstName")
                .checkStateTables("#lastName")
                .checkGenderError()
                .checkStateTables("#userNumber");
    }
}
