package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.ConfigReader;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        return driver.get();
    }

    @BeforeMethod
    public void setup() {

        String browser =
                ConfigReader.getProperty("browser");

        WebDriver webDriver;

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                webDriver = new ChromeDriver();

                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();

                webDriver = new FirefoxDriver();

                break;

            default:

                throw new RuntimeException(
                        "Unsupported browser: " + browser);
        }

        driver.set(webDriver);

        getDriver().manage().window().maximize();

        getDriver().get(
                ConfigReader.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        WebDriver webDriver = getDriver();

        if (webDriver != null) {

            webDriver.quit();
        }
    }
}