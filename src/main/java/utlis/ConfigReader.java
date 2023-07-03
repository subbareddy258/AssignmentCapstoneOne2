package utlis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/config/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getURL() {
        return properties.getProperty("url");
    }

    public String getForgotURL() {
        return properties.getProperty("forgotUrl");

    }

    public String getDemoURL() {
        return properties.getProperty("demoUrl");
    }

    public String getBankingURL() {
        return properties.getProperty("BankingUrl");

    }

}

