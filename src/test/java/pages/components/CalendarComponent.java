package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement clickCalendarButton = $("#dateOfBirthInput");
    private final SelenideElement selectMonth = $(".react-datepicker__month-select");
    private final SelenideElement selectYear = $(".react-datepicker__year-select");
    private final SelenideElement selectDay = $(".react-datepicker__month");

    public void setDate(String day, String month, String year) {
        clickCalendarButton.click();
        selectMonth.$(byText(month)).click();
        selectYear.$(byText(year)).click();
        selectDay.$(byText(day)).click();

    }
}
