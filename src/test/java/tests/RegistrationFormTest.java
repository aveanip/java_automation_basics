package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;
import tests.testsdata.TestData;

import static io.qameta.allure.Allure.step;

public class RegistrationFormTest extends BaseTest {
    @Test
    @DisplayName("Проверка формы регистрации при заполнении всех полей")
    void successfulRegistrationFormTest() {
        step("Открываем форму регистрации", () -> {
            textRegistrationFormPage
                    .openPage()
                    .removeBanners();
        });
        step("Заполнить все поля формы", () -> {
            textRegistrationFormPage.typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .typeEmail(testData.email)
                    .setGender(testData.genter)
                    .typeUserNumber(testData.userNumber)
                    .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                    .setSubject(testData.subject)
                    .setHobbie(testData.hobbie)
                    .uploadPicture(testData.picture)
                    .setCurrentAddress(testData.address)
                    .setStateAndCity(testData.state, testData.city);
        });
        step("Кликнуть на кнопку и проверить текст после регистрации", () -> {
            textRegistrationFormPage
                    .clickButton()
                    .textRegistrationResult();
        });
        step("Проверить данные в модальном окне", () -> {
            textRegistrationFormPage
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

        });
    }

    @Test
    @DisplayName("Проверка формы регистрации при заполнении только обязательных полей")
    void registrationWithRequiredFieldsTest() {
        step("Открываем форму регистрации", () -> {
            textRegistrationFormPage
                    .openPage()
                    .removeBanners();
        });

        step("Заполнить только обязательные поля формы", () -> {
            textRegistrationFormPage
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .setGender(testData.genter)
                    .typeUserNumber(testData.userNumber)
                    .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear);
        });
        step("Кликнуть на кнопку и проверить текст после регистрации", () -> {
            textRegistrationFormPage
                    .clickButton()
                    .textRegistrationResult();
        });
              step("Проверить заполненные данные в модульном окне",() -> {
                  textRegistrationFormPage
                          .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                          .checkResultEmptyRow("Student Email")
                          .checkResult("Gender", testData.genter)
                          .checkResult("Mobile", testData.userNumber)
                          .checkResult("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear);
        });
                step("Проверить, что не обязательные поля пустые ", () -> {
                    textRegistrationFormPage
                            .checkResultEmptyRow("Subjects")
                            .checkResultEmptyRow("Hobbies")
                            .checkResultEmptyRow("Picture")
                            .checkResultEmptyRow("Address")
                            .checkResultEmptyRow("State and City");
                });
    }

    @Test
    @DisplayName("Проверка цвета рамки при невалидном номере телефона")
    void phoneFieldWithInvalidUserNumberShowsRedBorderColorTest() {
        step("Открываем форму регистрации", () -> {
            textRegistrationFormPage
                    .openPage()
                    .removeBanners();
        });
        step("Заполнить поля валидными данными", () -> {
            textRegistrationFormPage
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName)
                    .typeEmail(testData.email)
                    .setGender(testData.genter);
        });
        step("Заполнить поле User name не валидным значением", () -> {
            textRegistrationFormPage
                    .typeUserNumber(testData.invalidPhone);
        });
        step("Нажать на кнопку submit", () -> {
            textRegistrationFormPage
                    .clickButton();
        });
        step("Проверить, что после отправки формы регистрации поле user number подсвечивается красным", () -> {
            textRegistrationFormPage
                    .checkStateTables("#userNumber");
        });
    }


    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без @")
    void wrongUserEmailMissingCharTest() {
        step("Открываем форму регистрации", () -> {
            textRegistrationFormPage
                    .openPage()
                    .removeBanners();
        });
        step("Заполнить поля валидными данными", () -> {
            textRegistrationFormPage
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName);
        });
        step("Заполнить поле email без @", () -> {
            textRegistrationFormPage
                    .typeEmail(testData.invalidEmailForRegistration);
        });
        step("Выбрать gender", () -> {
            textRegistrationFormPage
                    .setGender(testData.genter);
        });
        step("Нажать на кнопку submit", () -> {
            textRegistrationFormPage
                    .clickButton();
        });
        step("Проверить, что после отправки формы регистрации поле email подсвечивается красным", () -> {
            textRegistrationFormPage
                    .checkStateTables("#userEmail");

        });
    }

    @Test
    @DisplayName("Проверка цвета рамки поля почты при вводе без доменной части")
    void emailWithoutADomainTest() {
        step("Открываем форму регистрации", () -> {
            textRegistrationFormPage
                    .openPage()
                    .removeBanners();
        });
        step("Заполнить поля валидными данными", () -> {
            textRegistrationFormPage
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.lastName);
        });
        step("Заполнить поле email без @", () -> {
            textRegistrationFormPage
                    .typeEmail(testData.invalidEmailForRegistration);
        });
        step("Выбрать gender", () -> {
            textRegistrationFormPage
                    .setGender(testData.genter);
        });
        step("Нажать на кнопку submit", () -> {
            textRegistrationFormPage
                    .clickButton();
        });
        step("Проверить, что после отправки формы регистрации поле email подсвечивается красным", () -> {
            textRegistrationFormPage
                    .checkStateTables("#userEmail");

        });
    }

    @Test
    @DisplayName("Пустая форма регистрации")
    void emptyRegistrationForm() {
        step("Открываем форму регистрации", () -> {
            textRegistrationFormPage
                    .openPage()
                    .removeBanners();
        });
        step("Нажать на кнопку submit", () -> {
            textRegistrationFormPage
                    .clickButton();
        });
        step("Проверить, что обязательные поля подсвечиваются красным", () -> {
            textRegistrationFormPage
                    .checkStateTables("#firstName")
                    .checkStateTables("#lastName")
                    .checkGenderError()
                    .checkStateTables("#userNumber");
        });
    }
}
