package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features/",
                    glue = {"org.satyan.stepdefs"},
                    plugin = {"pretty", "html:target/cucumber-reports, json:target/cucumber.json"},
                    monochrome = true,
                    publish = true)

//If you want to run the test in parallel then we have to extend the runner class AbstractTestNGCucumberTests
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
