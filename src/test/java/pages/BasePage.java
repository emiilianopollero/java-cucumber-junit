package pages;

import enums.DriverType;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManagerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class BasePage {
    // Goes through page gathering all links and checks that the response code of them when opening the link is not >= 400
    public static void checkForBrokenLinks(WebDriver driver) {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        String url;
        int respCode;
        for (WebElement link : links) {
            url = link.getAttribute("href");
            try {
                if (url.startsWith("mailto") || url.startsWith("tel")) {
                    System.out.println(url + " link is a mailto or tel");
                } else {
                    System.out.println(url);
                    if (url.isEmpty()) {
                        System.out.println("URL is either not configured for anchor tag or it is empty");
                        continue;
                    }
                    try {
                        HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(url));
                        respCode = urlresp.getStatusLine().getStatusCode();
                        if (respCode >= 400 && respCode != 999) {
                            throw new AssertionError(url + " is a broken link. Status code is " + respCode);
                        }                     } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

}
