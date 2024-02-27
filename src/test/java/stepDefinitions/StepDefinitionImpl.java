package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.testng.Assert;
import testComponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public  ProductCatalogue productCatalogue;
    public ConfirmationPage page;
    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.*) and password (.*)$")
    public void logged_in_with_username_and_password(String username, String password) {
        productCatalogue = landingPage.login(username, password);
    }

    @When("^I add product (.*) to Cart$")
    public void i_add_product_to_cart(String product) {
        WebElement desireProduct =productCatalogue.getProductByName(product);
        productCatalogue.clickOnAddToCart(desireProduct);
    }

    @And("^Checkout (.*) and submit the order$")
    public void checkout_and_submit_the_order(String product){
        CartPage cartPage = productCatalogue.clickOnAddToCart();
        boolean isProductPresent = cartPage.validateProduct(product);
        Assert.assertTrue(isProductPresent, "Element is not present in My Cart section");


        CheckoutPage checkoutPage = cartPage.clickOnCheckOutButton();
        checkoutPage.setCountry("india");
        checkoutPage.clickOnCountry();
        page = checkoutPage.clickPlaceOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_confirmation_page(String confirmationMessage) {

        System.out.println(page.getTitle());
        Assert.assertTrue(page.getTitle().equalsIgnoreCase(confirmationMessage), "Invalid Hero title");
        driver.close();
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String errorMessage) {
        Assert.assertEquals(landingPage.getLoginErrorMessage(),errorMessage,
                "Error to get Incorrect Email or password message");
        driver.close();
    }
}