package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class DriverSetup {
     static WebDriver driver;

    public static WebDriver getWebDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(edgeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        if(!SystemUtils.IS_OS_WINDOWS)
        {
            edgeOptions.addArguments("--headless=new");
        }
        return driver;
    }


    public static void waited()
    {
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    }

