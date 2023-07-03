package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ManagerLoginPages {

    private WebDriver driver;
    private By addCustomerButton = By.xpath("//button[@ng-click='addCust()']");
    private By bankManagerLoginButton = By.xpath("//button[@ng-click='manager()']");
    private By firstNameField = By.xpath("//input[@ng-model='fName']");

    private By lastNameField = By.xpath("//input[@ng-model='lName']");

    private By postCardField = By.xpath("//input[@ng-model='postCd']");
    private By addCustomerSubmitButton = By.xpath("//button[@type='submit']");
    private By openAccountClick = By.xpath("//button[@ng-click='openAccount()']");

    private By dropdownSelectForUsers = By.id("userSelect");
    private By dropdownCurrency = By.id("currency");
    private By showCustomerDetails = By.xpath("//button[@ng-click='showCust()']");


    public ManagerLoginPages(WebDriver driver) {
        this.driver = driver;
    }


    public void mangerLogin() {
        driver.findElement(bankManagerLoginButton).click();
        driver.findElement(addCustomerButton).click();
    }

    public void setAccoutDetails(String firstName, String lastName, String postcard) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postCardField).sendKeys(postcard);
        driver.findElement(addCustomerSubmitButton).submit();
        Alert alert = driver.switchTo().alert();
        String addCustomerAlert = alert.getText();
        System.out.println("added customer" + addCustomerAlert);
        Assert.assertEquals("Customer added successfully with customer id :6", addCustomerAlert);
        alert.accept();

    }

    public void setOpenAccountClick() {
        driver.findElement(openAccountClick).click();
    }

    public void customerDropdown() {
        Select select = new Select(driver.findElement(dropdownSelectForUsers));
        List<WebElement> dropdownOptions = select.getOptions();
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

    public void currencyDropdown() {
        Select select = new Select(driver.findElement(dropdownCurrency));
        List<WebElement> dropdownOptions = select.getOptions();
        for (WebElement option : dropdownOptions) {
            // Get the text of the option
            String optionText = option.getText();

            // Check if the option text matches the desired option
            if (optionText.contains("Dollar")) {
                // Select the option
                select.selectByVisibleText(optionText);
                break; // Exit the loop after selecting the desired option
            }
        }

    }

    public void processButton() {
        driver.findElement(addCustomerSubmitButton).submit();
    }

    public void alertMessages() {
        Alert alert = driver.switchTo().alert();
        String openAccount = alert.getText();
        System.out.println("added customer" + openAccount);
        Assert.assertEquals("Account created successfully with account Number :1016", openAccount);
        alert.accept();
    }

    public void customerDelete() {
        driver.findElement(showCustomerDetails).click();
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered table-striped']/tbody/tr"));
        int lastCustomerIndex = rows.size() - 1;
        if (lastCustomerIndex >= 0) {
            WebElement lastCustomerRow = rows.get(lastCustomerIndex);
            WebElement deleteButton = lastCustomerRow.findElement(By.xpath(".//button[text()='Delete']"));
            deleteButton.click();
        } else {
            System.out.println("No customers found in the table.");
        }
    }
}
