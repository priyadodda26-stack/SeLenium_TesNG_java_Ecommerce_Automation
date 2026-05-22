package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenShot;

import java.time.Duration;

public class AddToCart {

    WebDriver driver;
    WebDriverWait wait;

    public AddToCart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpack;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    public WebElement bikeLight;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public void addBackpack() {
        wait.until(ExpectedConditions.elementToBeClickable(backpack)).click();
    }

    public void addBikeLight() {

                wait.until(ExpectedConditions
                        .elementToBeClickable(bikeLight)).click();

    }

    public String getCartCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    public String addBackpackAndCapture(WebDriver driver) {

        backpack.click();

        return ScreenShot.capture(driver, "add_backpack");
    }
}