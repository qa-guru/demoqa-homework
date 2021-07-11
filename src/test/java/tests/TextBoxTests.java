package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        open("/text-box");
        $("#userName").setValue("Alex");
        $("#userEmail").setValue("alex@qaguru.com");
        $("#currentAddress").setValue("some street 1");
        $("#permanentAddress").setValue("another street 2");
        $("#submit").click();

        $("#name").shouldHave(text("Alex"));
        $("#email").shouldHave(text("alex@qaguru.com"));
        $("#currentAddress", 1).shouldHave(text("some street 1"));
        $("p#permanentAddress").shouldHave(text("another street 2"));
    }
}
