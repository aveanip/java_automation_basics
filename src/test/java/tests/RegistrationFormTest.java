package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;
import static tests.testsdata.TestData.*;

public class RegistrationFormTest extends BaseTest {
    RegistrationFormPage textRegistrationFormPage = new RegistrationFormPage();

    @Test
    @DisplayName("Проверка формы регистрации при заполнении всех полей")
    void successfulRegistrationFormTest() {
        textRegistrationFormPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .setGender(genter)
                .typeUserNumber(userNumber)
                .setDateOfBirth(birthDay, birthMonth, birthYear)
                .setSubject(subject)
                .setHobbie(hobbie)
                .uploadPicture(picture)
                .setcurrentAddress(address)
                .setStateAndCity(state, city)
                .clickButton()
                .textRegistrationResult()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", genter)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobbie)
                .checkResult("Picture", pictureName)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    @DisplayName("Проверка формы регистрации при заполнении только обязательных полей")
    void registrationWithRequiredFieldsTest() {
        textRegistrationFormPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .setGender(genter)
                .typeUserNumber(userNumber)
                .clickButton()
                .textRegistrationResult()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", genter)
                .checkResult("Mobile", userNumber);
    }

    @Test
    @DisplayName("Проверка цвета рамки при невалидном номере телефона")
    void phoneFieldWithInvalidUserNumberShowsRedBorderColorTest() {
        textRegistrationFormPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .setGender(genter)
                .typeUserNumber(invalidPhone)
                .clickButton()
                .checkStateTables("#userNumber");
    }


    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без @")
    void wrongUserEmailMissingCharTest() {
        textRegistrationFormPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(invalidEmailForRegistration)
                .setGender(genter)
                .clickButton()
                .checkStateTables("#userEmail");
    }

    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без доменной части")
    void emailWithoutADomainTest() {
        textRegistrationFormPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(invalidEmailForRegistration)
                .setGender(genter)
                .clickButton()
                .checkStateTables("#userEmail");
    }

    @Test
    @DisplayName("Пустая форма регистрации")
    void emptyRegistrationForm() {
        textRegistrationFormPage.openPage()
                .clickButton()
                .checkStateTables("#firstName")
                .checkStateTables("#lastName")
                .checkGenderError()
                .checkStateTables("#userNumber");
    }
}
