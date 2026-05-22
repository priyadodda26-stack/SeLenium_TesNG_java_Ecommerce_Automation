package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Cart icon
    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    // Checkout button
    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    // First Name
    @FindBy(id = "first-name")
    WebElement firstName;

    // Last Name
    @FindBy(id = "last-name")
    WebElement lastName;

    // Zip Code
    @FindBy(id = "postal-code")
    WebElement zipCode;

    // Continue button
    @FindBy(id = "continue")
    WebElement continueBtn;

    // Finish button
    @FindBy(id = "finish")
    WebElement finishBtn;

    // Confirmation message
    @FindBy(className = "complete-header")
    WebElement successMsg;
    @FindBy(className = "back-to-products")
    WebElement backtoProducts;


    // Actions

    public void openCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }

    public void clickCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        checkoutBtn.click();
    }

    public void enterCheckoutDetails(String fn, String ln, String zip) {
        firstName.sendKeys(fn);
        lastName.sendKeys(ln);
        zipCode.sendKeys(zip);
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public void clickFinish() {
        finishBtn.click();
    }

    public String getSuccessMessage() {

        return successMsg.getText();
    }
    public void getBacktoProducts() {
         backtoProducts.click();
    }
}