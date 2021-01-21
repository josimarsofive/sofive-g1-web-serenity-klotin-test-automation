import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actions.Open
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class ExampleTests {

    var webDriver: WebDriver? = null

    @Test
    fun navigateToContactPage(){
        System.setProperty("webdriver.chrome.driver","C:\\repo\\browser\\chromedriver.exe")
        webDriver = ChromeDriver()

        val josimar = Actor.named("Josimar")
        josimar.can(BrowseTheWeb.with(webDriver))

        josimar.attemptsTo(Open.url("http://automationpractice.com/index.php?controller=contact"))


    }
}