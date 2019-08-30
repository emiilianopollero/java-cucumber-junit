package pages;

import enums.DriverType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.DriverManager;
import utils.DriverManagerFactory;

public class GoogleHomePage{
    @FindBy(name = "q")
    private WebElement searchInput;
    @FindBy(name = "btnK")
    private WebElement searchBtn;

    public GoogleHomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void searchQuery(String query){
        searchInput.clear();
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
    }
}
