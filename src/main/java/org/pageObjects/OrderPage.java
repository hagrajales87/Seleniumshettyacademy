package org.pageObjects;

import abstractCompenent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    private WebDriver driver;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody//td[2]")
    List<WebElement> orderNames;


    public boolean validateOrderDisplayed(String productName){
        waitForElementsToAppear(orderNames);
        return orderNames.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
    }
}
