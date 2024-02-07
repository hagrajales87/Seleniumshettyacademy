package org.pageObjects;

import abstractCompenent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {

    private WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//input[@placeholder='Select Country']")
    WebElement selectCountryField;

    @FindBy(css = ".ta-results")
    List<WebElement> countriesAvailable;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement country;
    @FindBy(css = "div[class='actions'] a")
    WebElement placeOrder;
    public void setCountry(String country){
        Actions actions = new Actions(driver);
        actions.sendKeys(selectCountryField,country).build().perform();
    }

    public void clickOnCountry(){
        waitForElementsToAppear(countriesAvailable);
        country.click();
    }
    public ConfirmationPage clickPlaceOrder(){
        placeOrder.click();
        return new ConfirmationPage(driver);
    }



}
