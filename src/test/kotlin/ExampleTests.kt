import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.actions.SelectFromOptions
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers
import net.serenitybdd.screenplay.questions.Text
import net.serenitybdd.screenplay.questions.WebElementQuestion
import net.serenitybdd.screenplay.targets.Target
import net.serenitybdd.screenplay.waits.WaitUntil
import net.thucydides.core.annotations.Managed
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

@RunWith(SerenityRunner::class)
class ExampleTests {

    @Managed
    var webDriver: WebDriver? = null

    @Test
    fun navigateToContactPage(){

        val emailAddress = Target.the("email address")
            .located(By.cssSelector("input#email"))

        val orderReference = Target.the("order reference")
            .located(By.cssSelector("input#id_order"))

            val message = Target.the("message")
            .located(By.cssSelector("textarea#message"))

        val subjectHeading = Target.the("subject heading")
            .located(By.cssSelector("select#id_contact"))

        val send = Target.the("send")
            .located(By.cssSelector("button#submitMessage"))

        val josimar = Actor.named("Josimar")
        josimar.can(BrowseTheWeb.with(webDriver))

        josimar.attemptsTo(
            Open.url("http://automationpractice.com/index.php?controller=contact"),
            Enter.theValue("sofive@gmail.com").into(emailAddress),
            Enter.theValue("el mensaje").into(message),
            Enter.theValue("55555555").into(orderReference),
            SelectFromOptions.byVisibleText("Customer service").from(subjectHeading),
            Click.on(send)

            )


    }
    @Test
    fun invalidEmail(){
        val josimar = Actor.named("Josimar")
        josimar.can(BrowseTheWeb.with(webDriver))

        val send = Target.the("send")
            .located(By.cssSelector("button#submitMessage"))

        val errorMessage = Target.the("Error message")
            .located(By.cssSelector("div.alert-danger"))

        josimar.attemptsTo(
            Open.url("http://automationpractice.com/index.php?controller=contact"),
            Click.on(send)
        )

        val error1 = errorMessage.resolveFor(josimar).text
        val error2 = Text.of(errorMessage).asAString().answeredBy(josimar)

        println("el valor de error 1 es $error1")
        println("el valor de error 2 es $error2")

        josimar.should(seeThat(
            WebElementQuestion.the(errorMessage),
            WebElementStateMatchers.isVisible()
        ))

        josimar.attemptsTo(WaitUntil.the(errorMessage, WebElementStateMatchers.isVisible())
            .forNoMoreThan(12)
            .seconds())
    }
}