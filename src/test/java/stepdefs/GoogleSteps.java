package stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.DriverType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GoogleHomePage;
import utils.Driver;
import utils.DriverManager;
import utils.DriverManagerFactory;

public class GoogleSteps{

    DriverManager driverManager;
    WebDriver driver;

    @Before
    public void before(){
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
    }

    @Given("I open google homepage")
    public void iOpenGoogleHomepage() {
        driver.get("https://www.google.com");
    }

    @When("I search for (.+)")
    public void iSearchFor(String query) {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver);
        googleHomePage.searchQuery(query);
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

    @After
    public void after(){
        driverManager.quitDriver();
    }
}
