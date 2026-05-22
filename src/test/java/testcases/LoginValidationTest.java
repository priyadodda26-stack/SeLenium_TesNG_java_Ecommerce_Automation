package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import utils.ExcelReader;

import java.time.Duration;

import static pageObject.LoginPage.INVALID_LOGIN_ERROR;

@Listeners(utils.TestListener.class)
public class LoginValidationTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws Exception {
        return ExcelReader.getLoginData();
    }

    @Test(dataProvider = "loginData")
    public void TC001_validLogin(String username,
                                 String password,
                                 String expectedResult) {

        WebDriver driver = getDriver();

        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);

        login.loginApplication(username, password);

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(10));

        boolean loginSuccess;

        try {

            wait.until(ExpectedConditions
                    .urlContains("inventory.html"));

            loginSuccess = true;

        } catch (Exception e) {

            loginSuccess = false;
        }

        if (expectedResult.equalsIgnoreCase("valid")) {

            Assert.assertTrue(loginSuccess);

            home.openMenu();
            home.logout();

        } else {

            Assert.assertFalse(
                    loginSuccess,
                    "Expected invalid login but login succeeded");

            String actualError =
                    login.getErrorMessage();

            Assert.assertTrue(
                    actualError.contains(
                            INVALID_LOGIN_ERROR));
        }
    }
}