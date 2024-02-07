package org.example;

import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";


    @Test(dataProvider = "getData", groups={"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {


        String country = "india";


        //landingPage.setUserEmail("hagrajales87@gmail.com");
        //landingPage.userPassword("Test123!");
        //ProductCatalogue productCatalogue = landingPage.clickOnLoginButton();

        ProductCatalogue productCatalogue = landingPage.login(input.get("email"), input.get("password"));
        WebElement desireProduct =productCatalogue.getProductByName(input.get("product"));
        productCatalogue.clickOnAddToCart(desireProduct);


        CartPage cartPage = productCatalogue.clickOnAddToCart();
        boolean isProductPresent = cartPage.validateProduct(input.get("product"));
        Assert.assertTrue(isProductPresent, "Element is not present in My Cart section");


        CheckoutPage checkoutPage = cartPage.clickOnCheckOutButton();
        checkoutPage.setCountry(country);
        checkoutPage.clickOnCountry();


        ConfirmationPage page = checkoutPage.clickPlaceOrder();
        System.out.println(page.getTitle());
        Assert.assertTrue(page.getTitle().equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Invalid Hero title");
    }

    // To verify ZARA COAT 3 is displaying in orders page
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest(){
        //"ZARA COAT 3"

        //landingPage.setUserEmail("hagrajales87@gmail.com");
        //landingPage.userPassword("Test123!");
        //ProductCatalogue productCatalogue = landingPage.clickOnLoginButton();
        ProductCatalogue productCatalogue = landingPage.login("hagrajales87@gmail.com", "Test123!");
        OrderPage orderPage = productCatalogue.clickOnOrders();
        Assert.assertTrue(orderPage.validateOrderDisplayed(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        /*
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "hagrajales87@gmail.com");
        map.put("password", "Test123!");
        map.put("product", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "hagrajales87@yahoo.com");
        map1.put("password", "Test123!");
        map1.put("product", "ADIDAS ORIGINAL");
         */

        List<HashMap<String, String>> data =getJsonDataToMap("src/test/java/data/PurchaseOrder.json");
        return new Object[][]{
                {data.get(0)},
                {data.get(1)}
        };
    }

}
