package stepDefinitions;

import Pages.ManagerLoginPages;
import Pages.TestData;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utlis.ConfigReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static stepDefinitions.CustomerStepDefinition.report;
import static stepDefinitions.CustomerStepDefinition.test;

public class ManagerStepDefinition {
    static WebDriver driver;
    private ConfigReader configReader;
    String firstName,postcard,lastName;
    private ManagerLoginPages managerLoginPages;


    @And("I click on Bank manger Login button")
    public void iClickOnBankMangerLoginButton() {
       test=report.startTest("Manager Login");

        managerLoginPages.mangerLogin();

        test.log(LogStatus.INFO,"Manager succcessfully logged in");

    }

    @Given("I am on the bank login page")
    public void iAmOnTheBankLoginPage() {
        driver = DriverSetup.getWebDriver();
        configReader = new ConfigReader();
        driver.get(configReader.getBankingURL());
        managerLoginPages = new ManagerLoginPages(driver);
        System.out.println(driver.getTitle());
        test.log(LogStatus.INFO,"browser lauched and maximized");



    }


    @When("user fills the form from given sheetname {string} and rownumber {string}")
    public void userFillsTheFormFromGivenSheetnameAndRownumber(String sheetName, String rowNumber) throws IOException, InvalidFormatException {
        TestData reader = new TestData();
        List<Map<String,String>> testData =
                reader.getData("TestData.xlsx", sheetName);

        firstName = testData.get(Integer.parseInt(rowNumber)).get("firstName");
        lastName = testData.get(Integer.parseInt(rowNumber)).get("lastName");
        postcard = testData.get(Integer.parseInt(rowNumber)).get("postcard");

        managerLoginPages.setAccoutDetails(firstName, lastName, postcard);

        test.log(LogStatus.INFO,"readed the data from excel and passed the values");


    }

    @When("I click on open account the open account page lauched")
    public void iClickOnOpenAccountTheOpenAccountPageLauched() {
        managerLoginPages.setOpenAccountClick();
    }

    @And("I Select the customer details and currency from dropdown")
    public void iSelectTheCustomerDetailsAndCurrencyFromDropdown() {
        managerLoginPages.customerDropdown();
        managerLoginPages.currencyDropdown();
        test.log(LogStatus.INFO,"selected curreny dropdown");

    }

    @And("click on process")
    public void clickOnProcess() {
managerLoginPages.processButton();
        test.log(LogStatus.INFO,"clicked on process button");

    }

    @Then("Verify alert message displayed")
    public void verifyAlertMessageDisplayed() {
managerLoginPages.alertMessages();
        test.log(LogStatus.INFO,"validated alert message");

    }

    @Then("click on customer and delete added customer")
    public void clickOnCustomerAndDeleteAddedCustomer() {
        test.log(LogStatus.INFO,"added account deleted");
        managerLoginPages.customerDelete();

    }
}
