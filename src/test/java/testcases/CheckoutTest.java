package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AddToCart;
import pageObject.CheckoutPage;
import pageObject.LoginPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutFlow() {


        AddToCart cart = new AddToCart(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CheckoutPage checkout = new CheckoutPage(getDriver());
        loginPage.loginApplication("standard_user","secret_sauce");


        cart.addBackpack();

        cart.addBikeLight();

        // Step 2: Open cart
        checkout.openCart();

        // Step 3: Click checkout
        checkout.clickCheckout();

        // Step 4: Enter details
        checkout.enterCheckoutDetails("John", "Doe", "12345");

        // Step 5: Continue
        checkout.clickContinue();

        // Step 6: Finish order
        checkout.clickFinish();

        // Step 7: Validate success message
        Assert.assertEquals(checkout.getSuccessMessage(), "Thank you for your order!");
     //checkout.getBacktoProducts();
    }

}