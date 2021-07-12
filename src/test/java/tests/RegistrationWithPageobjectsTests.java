package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationWithPageobjectsTests {

    RegistrationPage registrationPage = new RegistrationPage();



    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void successfulRegistrationStepsTest() {
        String firstName = "Alex";

        registrationPage.openPage();

        registrationPage.typeFirstName("Alex");
        registrationPage.typeLastName("Egorov");

        registrationPage.typeEmail("alex@egorov.com")
                .selectGenger("Other")
                .typePhone("1231231231")
                .setDateOfBirth("28", "July", "2005");

        $("#subjectsInput").val("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("./img/1.png");
        $("#currentAddress").val("Qa guru street 7");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue(firstName + " Egorov");
        registrationPage.checkResultsValue("alex@egorov.com");
    }

/* pseudocode

    @Test
    void successfulRegistrationScenarioTest() {
        String firstName = "Alex";
        String lastName = "Egorov";


        registrationPage.openPage();

        registrationPage.fillForm(firstName, lastName, "alex@egorov.com"); // and 50 other parameters

        registrationPage.checkResults(firstName, lastName, "alex@egorov.com");
    }

 */

/* pseudocode

    @Test
    void successfulRegistrationScenarioWIthHashmapTest() {
        Map<String, String> testData  = new HashMap<>() {{
            put("First Name", "Alex");
            put("Last Name", "Egorov");
            put("Email", "alex@egorov.com");
            // ...
        }};

        Map<String, String> expectedData  = new HashMap<>() {{
            put("Student Name", firstName + " " + lastName);
            put("Student Email", email);
            // ...
        }};

        registrationPage.openPage();

        registrationPage.fillForm(testData);

        registrationPage.checkResults(expectedData);
    }

*/

}
