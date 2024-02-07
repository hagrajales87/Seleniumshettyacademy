package org.example;

import org.openqa.selenium.WebElement;
import org.pageObjects.CartPage;
import org.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = "ErrorHandling")
    public void submitOrder() throws IOException, InterruptedException
    {
        landingPage.setUserEmail("hagrajales87@gmail.com");
        landingPage.userPassword("Test123");
        landingPage.clickOnLoginButton();
        Assert.assertEquals(landingPage.getLoginErrorMessage(),"Incorrect email or password.",
                "Error to get Incorrect Email or password message");

    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException
    {
        String productName = "ZARA COAT 3";

        landingPage.setUserEmail("hagrajales87@yahoo.com");
        landingPage.userPassword("Test123!");
        ProductCatalogue productCatalogue = landingPage.clickOnLoginButton();

        WebElement desireProduct =productCatalogue.getProductByName(productName);
        productCatalogue.clickOnAddToCart(desireProduct);
        CartPage cartPage = productCatalogue.clickOnAddToCart();
        boolean match = cartPage.validateProduct("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
