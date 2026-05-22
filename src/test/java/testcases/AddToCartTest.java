package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AddToCart;
import pageObject.LoginPage;


public class AddToCartTest extends BaseTest {

    @Test
    public void Tc002_verifyCartCount() {

        WebDriver driver = getDriver();

        LoginPage login = new LoginPage(driver);
        AddToCart cart = new AddToCart(driver);

        login.loginApplication("standard_user", "secret_sauce");
        String imgPath = cart.addBackpackAndCapture(driver);

        Assert.assertEquals(cart.getCartCount(), "1");


        //cart.addBikeLight();
        //Assert.assertEquals(cart.getCartCount(), "2");
    }
}