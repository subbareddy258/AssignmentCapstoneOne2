package qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
    private WebDriver driver;

    public WebDriver init_driver() {
        if (driver != null) {
            return driver;
        }
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
return driver;
    }

}
