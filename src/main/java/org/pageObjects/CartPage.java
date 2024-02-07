package org.pageObjects;

import abstractCompenent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    private WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> elementToBuy;

    @FindBy(css = ".totalRow button")
    WebElement checkOutButton;

    public boolean validateProduct(String product){
        waitForElementsToAppear(elementToBuy);
        return elementToBuy.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(product));
    }

    public CheckoutPage clickOnCheckOutButton(){
        checkOutButton.click();
        return new CheckoutPage(driver);
    }

}
