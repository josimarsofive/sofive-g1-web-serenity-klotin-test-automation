import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actions.Open
import net.thucydides.core.annotations.Managed
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

@RunWith(SerenityRunner::class)
class ExampleTests {

    @Managed
    var webDriver: WebDriver? = null

    @Test
    fun navigateToContactPage(){

        val josimar = Actor.named("Josimar")
        josimar.can(BrowseTheWeb.with(webDriver))

        josimar.attemptsTo(Open.url("http://automationpractice.com/index.php?controller=contact"))


    }
}