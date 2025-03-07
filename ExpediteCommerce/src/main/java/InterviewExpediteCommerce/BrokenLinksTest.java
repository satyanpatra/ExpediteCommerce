package InterviewExpediteCommerce;

import base.BaseTest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.ReportUtils;

import java.io.IOException;
import java.util.List;

public class BrokenLinksTest extends BaseTest {

    @Test
    public void testBrokenLinks() {
        driver.get("http://www.deadlinkcity.com/"); // Replace with your application URL
        checkLinks();

        //Add more pages if needed.
        //driver.findElement(By.linkText("Next Page")).click();
        //checkLinks();
    }
    private void checkLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + links.size());
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty() && !url.startsWith("javascript")) {
                verifyLink(url);
            }
        }
    }
    private void verifyLink(String url) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 400) {
                ReportUtils.logBrokenLink(url, statusCode);
            }
        } catch (IOException e) {
            ReportUtils.logBrokenLink(url, -1); // -1 for exception
            System.err.println("Error checking link: " + url + " - " + e.getMessage());
        }
    }

}