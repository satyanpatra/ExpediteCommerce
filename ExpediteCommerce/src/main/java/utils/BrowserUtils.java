package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowserUtils {
    public static WebDriver initializeDriver(String browser) throws URISyntaxException, MalformedURLException {
        WebDriver driver;
        String seleniumHub ;
        seleniumHub = System.getProperty("hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browser.toLowerCase()) {
            case "chrome":
                System.out.println("Inside Chrome");
                capabilities.setBrowserName("chrome");
                break;
            case "firefox":
                capabilities.setBrowserName("firefox");
                break;
            case "edge":
                capabilities.setBrowserName("edge");
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        System.out.println("Creating the Remote WebDriver");
        driver = new RemoteWebDriver(new URI(seleniumHub).toURL(),capabilities);
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
