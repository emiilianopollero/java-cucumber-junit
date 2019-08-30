package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GoogleHomePage;


public class GoogleSteps {

    public WebDriver driver;

    public GoogleSteps() {
        driver = Hooks.driver;
    }

    @Given("I open google homepage")
    public void iOpenGoogleHomepage() {
        driver.get("https://www.google.com");
    }

    @When("I search for (.+)")
    public void iSearchFor(String query) {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver);
        googleHomePage.searchQuery(query);
//        checkForBrokenLinks(driver);
    }

    @Then("I can see (.+) in the results")
    public void iCanSeeInTheResults(String query) {
        boolean found = false;
        for (WebElement element : driver.findElements(By.cssSelector(".ellip"))) {
            found = element.getText().toLowerCase().contains(query.toLowerCase());
            if (found) break;
        }
        Assert.assertTrue(found);
    }
}
