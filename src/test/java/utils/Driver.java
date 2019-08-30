package utils;

import enums.EDrivers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static ConfigFileReader reader = new ConfigFileReader();

    // Creates driver object using browser variable
    private static WebDriver createDriver(String browserName) {
        WebDriver driver = null;
        OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
        switch (ostype) {
            case Windows:
                switch (browserName.toLowerCase()) {
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                                + reader.getDriverPath(EDrivers.WINDOWS_CHROME));
                        driver = new ChromeDriver();
                        break;
                    case "headless":
                        File file = new File(System.getProperty("user.dir") +
                                reader.getDriverPath(EDrivers.WINDOWS_CHROME));
                        String path = file.getAbsolutePath();
                        System.out.println(path);
                        System.setProperty("webdriver.chrome.driver", path);
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        driver = new ChromeDriver(chromeOptions);
                        break;
                    case "edge":
                        file = new File(System.getProperty("user.dir")
                                + reader.getDriverPath(EDrivers.WINDOWS_EDGE));
                        path = file.getAbsolutePath();
                        System.out.println(path);
                        System.setProperty("webdriver.edge.driver", path);
                        EdgeOptions options = new EdgeOptions();
                        options.setCapability("InPrivate", true);
                        driver = new EdgeDriver(options);
                        break;
                }
                break;
            case Linux:
                switch (browserName){
                    case "chrome":
                        Path path = Paths.get(System.getProperty("user.dir")
                                + reader.getDriverPath(EDrivers.LINUX_CHROME));
                        try {
                            Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("r-xr-xr-x"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                                + reader.getDriverPath(EDrivers.LINUX_CHROME));
                        driver = new ChromeDriver();
                        break;
                    case "headless":
                        File file = new File(System.getProperty("user.dir") +
                                reader.getDriverPath(EDrivers.LINUX_CHROME));
                        String stringPath = file.getAbsolutePath();
                        System.setProperty("webdriver.chrome.driver", stringPath);
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        driver = new ChromeDriver(chromeOptions);
                        break;
                }
            case MacOS:
                switch (browserName)
                {
                    case "Chrome": //CHROME DRIVER
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                                + reader.getDriverPath(EDrivers.MAC_CHROME));
                        driver = new ChromeDriver();
                        break;
                    case "Headless":
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                                + reader.getDriverPath(EDrivers.MAC_CHROME));
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        driver = new ChromeDriver(chromeOptions);
                }
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        return driver;
    }
    public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
