package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {

    String firstName = "Alex";

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void successfulRegistrationTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").val(firstName);
        $("#lastName").val("Egorov");
        $("#userEmail").val("alex@egorov.com");
        $("[name=gender][value=Other]").parent().click();
        /*
          $("#gender-radio-3").parent().click();
          $(byValue("Other")).parent().click();
          $(byText("Other")).click(); // gender
          $("#genterWrapper").$(byText("Other")).click();
         */
        $("#userNumber").val("1231231231");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month)").click();
        /*
        $$(".react-datepicker__day--028")
                .filter(not(cssClass(".react-datepicker__day--outside-month"))).first().click();
        $("[aria-label='Choose Thursday, July 28th, 2005']").click();
        $x("//*[contains(@aria-label, 'July 28th, 2005')]").click();
        */
        $("#subjectsInput").val("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("./img/1.png");
        $("#currentAddress").val("Qa guru street 7");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " Egorov"), text("alex@egorov.com"));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text("Alex Egorov"));
        $x("//td[text()='Student Name']").parent()
                .shouldHave(text("Alex Egorov"));

    }
}
