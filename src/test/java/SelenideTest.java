import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    //Задача №1
    //Есть ли разница между $("h1 div"); и $("h1").$("div"); - может ли привести к тому что, поиск найдёт разные элементы?
    //Да, разница есть. $("h1 div") будет искать первый родительский элемент h1 в котором будет содержатся div.
    //$("h1").$("div") найдет первый родительский h1 элемент и внутрий него будет искать div.
    // Если элемент div внутри содержаться не будет, то появится ошибка "Element not found"

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    //Задача №2
    @Test
    void checkCodeForJunit5() {

        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(".js-wiki-more-pages-link").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $(".Layout-sidebar").$(byText("SoftAssertions")).click();
        $(".markdown-body").$("#user-content-3-using-junit5-extend-test-class")
                .parent()
                .shouldHave(text("Using JUnit5 extend test class"));
    }

    //Задача 3
    @Test
    void checkDragAndDropWithSelenideMethod() {
        //Метод работает
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Disabled("Some problem with moveToElement method")
    @Test
    void checkDragAndDropWithSelenideActions() {
        //К сожалению, при использовании actions, нажатие и удержание ЛКМ есть, но элемент не перемещается
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}
