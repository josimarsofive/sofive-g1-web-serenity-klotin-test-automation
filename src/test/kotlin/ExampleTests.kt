import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.actions.SelectFromOptions
import net.serenitybdd.screenplay.targets.Target
import net.thucydides.core.annotations.Managed
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

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
}