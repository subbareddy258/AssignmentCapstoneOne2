package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static stepDefinitions.CustomerStepDefinition.report;
import static stepDefinitions.CustomerStepDefinition.test;
import static stepDefinitions.DriverSetup.driver;


public class Hooks
{
    @Before
    public void setup() {
        // Perform setup actions here
        System.out.println("lauch the browesr");
    }

    @After
    public void teardown(io.cucumber.java.Scenario scenario) {
        // Perform teardown actions here
        if (scenario.isFailed()) {
            // Take screenshot if scenario fails
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        } else {
            // Take screenshot if scenario passes (optional)
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Passed Screenshot");
        }
        report.endTest(test);
        report.flush();

        driver.quit();
    }

}
