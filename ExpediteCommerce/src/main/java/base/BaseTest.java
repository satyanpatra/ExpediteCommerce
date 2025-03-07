package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserUtils;
import utils.ReportUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BaseTest {
    protected WebDriver driver;
    protected String browser;

    @BeforeMethod
    public void setup() throws MalformedURLException, URISyntaxException {
        browser = System.getProperty("browser");
        System.out.println("The browser name is " + browser);
        driver = BrowserUtils.initializeDriver(browser);
        ReportUtils.startReport();
    }

    @AfterMethod
    public void tearDown() {
        ReportUtils.endReport();
        BrowserUtils.quitDriver(driver);
    }
}
