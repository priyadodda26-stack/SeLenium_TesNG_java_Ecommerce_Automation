package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginBtn;
    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;
    public static final String INVALID_LOGIN_ERROR =
            "Epic sadface: Sorry, this user has been locked out";

    public void loginApplication(String user, String pass) {
        username.clear();
        username.sendKeys(user);

        password.clear();
        password.sendKeys(pass);

        loginBtn.click();
    }
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}