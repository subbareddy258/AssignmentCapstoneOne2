package stepDefinitions;

import Pages.CustomerPages;
import com.relevantcodes.extentreports.ExtentReports;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utlis.ConfigReader;
        import com.relevantcodes.extentreports.ExtentTest;
        import com.relevantcodes.extentreports.LogStatus;

public class CustomerStepDefinition {
    static WebDriver driver;
    private CustomerPages customerPages;
    private ConfigReader configReader;
    private static final Logger LOGGER = LogManager.getLogger(CustomerStepDefinition.class);
    static ExtentReports report;
    static ExtentTest test;


    @Given("I am on the login page")
    public void iAmOnTheLoginPage() throws InterruptedException {
        Thread.sleep(5000);
        driver = DriverSetup.getWebDriver();
        configReader = new ConfigReader();
        driver.get(configReader.getBankingURL());
        customerPages = new CustomerPages(driver);
        System.out.println(driver.getTitle());
        report =new ExtentReports("report/report.html",true);
        test=report.startTest("Customer login page");
                test.log(LogStatus.INFO,"browser lauched and maximized");

      }

    @When("I click on login button")
    public void iClickOnLoginButton() {
        customerPages.clickOnCustomerLoginButton();
        customerPages.dropDownSelect();
        customerPages.clickOnLoginButton();
        customerPages.validateCustomerDetails();
        test.log(LogStatus.INFO,"SuccessFully added customer details");
    }


    @Then("Dropdown display with Customer Names {string} and {string}")
    public void dropdownDisplayWithCustomerNamesAnd(String amount, String type) throws InterruptedException {

        customerPages.depositSelect(type);
        test.log(LogStatus.INFO,"selected type of customer");

        customerPages.setDepositAmount(amount);
        test.log(LogStatus.INFO,"amount value is setted");


    }

    @And("click on transcations")
    public void clickOnTranscations() throws InterruptedException {
        customerPages.clickOnTransactions();
        Thread.sleep(5000);

        test.log(LogStatus.INFO,"clicked on transcations");

    }

    @Then("click on LogoutButton")
    public void clickOnLogoutButton() {
        customerPages.logoutButton();
        test.log(LogStatus.INFO,"customer logged out successfully");
    }
}