package testRunner;
        import io.cucumber.testng.CucumberOptions;
        import io.cucumber.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/report/cucumber.html","json:target/report/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {


}

