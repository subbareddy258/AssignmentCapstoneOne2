package Pages;

import io.cucumber.datatable.DataTable;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CustomerPages {
    private  WebDriver driver;
    private By customerLoginButton = By.xpath("//button[contains(text(),'Customer Login')]");
    private By dropdownSelectForUsers = By.id("userSelect");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");
    private By welcomeMessage = By.xpath("//strong[contains(text(),'Welcome')]");
    private By customerName = By.xpath("//span[contains(text(),'Hermoine Granger')]");
    private By accountNumber = By.xpath("//strong[@class='ng-binding'][1]");
    private By balance = By.xpath("//strong[@class='ng-binding'][2]");
    private By  accountDeposits =By.xpath("//div[@ng-hide='noAccount']/button");
    private By depositAmount = By.xpath("//input[@placeholder='amount']");
    private By clickDeposit = By.xpath("//button[@type='submit']");
    private By depositMessage =By.xpath("//span[@ng-show='message']");
    private  By Home =By.xpath("//button[@ng-click='home()']");
    private By getBalance = By.xpath("//div[@ng-hide='noAccount']/strong[2]");
    private By transactions =By.xpath("//button[@ng-click='transactions()']");
    private By logoutButton = By.xpath("//button[@ng-show='logout']");

    public CustomerPages(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnCustomerLoginButton()
    {
        driver.findElement(customerLoginButton).click();

    }
    SoftAssert softassert = new SoftAssert();

    public void dropDownSelect() {
        Select select = new Select(driver.findElement(dropdownSelectForUsers));
        List<WebElement>  dropdownOptions=select.getOptions();
        for (WebElement option : dropdownOptions) {
            // Get the text of the option
            String optionText = option.getText();

            // Check if the option text matches the desired option
            if (optionText.contains("Hermoine Granger")) {
                // Select the option
                select.selectByVisibleText(optionText);
                break; // Exit the loop after selecting the desired option
            }
        }


    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();

       }

    public void validateCustomerDetails() {
        softassert.assertEquals("Welcome Hermoine Granger !!",driver.findElement(welcomeMessage).getText(),"welcome message not displayed");
        softassert.assertEquals("Hermoine Granger",driver.findElement(customerName).getText(),"invalid name");
        softassert.assertEquals("1001",driver.findElement(accountNumber).getText(),"invalid account number");
        softassert.assertEquals("5096",driver.findElement(balance).getText(),"invalid balance");
        softassert.assertAll();

    }
    public void depositSelect(String type) {
        List<WebElement>  dropdownOptions=driver.findElements(accountDeposits);
        for (WebElement option : dropdownOptions) {
            // Get the text of the option
            String optionText = option.getText();
            // Check if the option text matches the desired option
            if (optionText.contains(type)) {
                // Select the option
                option.click();
                break; // Exit the loop after selecting the desired option
            }
           }
    }

    public void setDepositAmount(String amount)
    {

        driver.findElement(depositAmount).sendKeys(amount);
        driver.findElement(clickDeposit).submit();
       String message= driver.findElement(depositMessage).getText();
        assertThat(message, anyOf(equalTo("Deposit Successful"), equalTo(""),equalTo("Transaction successful"),equalTo("Transaction Failed. You can not withdraw amount more than the balance")));
String getAmount = driver.findElement(getBalance).getText();
assertThat(getAmount,anyOf(equalTo("5096"),equalTo("4996"),equalTo("15096")));

    }

    public void clickOnTransactions() throws InterruptedException {
        driver.findElement(transactions).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(5000);
        Assert.assertEquals("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/listTx",driver.getCurrentUrl());
        driver.findElement(By.xpath("//div[@class='marTop tbStruct border box ng-scope']/div/table")).isDisplayed();
    }
    public void logoutButton()
    {

        driver.findElement(logoutButton).click();
        driver.findElement(Home).click();

    }

}
