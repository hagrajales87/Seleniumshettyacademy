package org.pageObjects;

import abstractCompenent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    private WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id ="userEmail")
    WebElement userEmail;

    @FindBy(id= "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = "#toast-container div[role='alert']")
    WebElement loginErrorMessage;

    public void setUserEmail(String username){
        userEmail.sendKeys(username);
    }

    public void userPassword(String password){
        userPassword.sendKeys(password);
    }

    public ProductCatalogue clickOnLoginButton(){
        loginButton.click();
        return new ProductCatalogue(driver);
    }

    public String getLoginErrorMessage(){
        waitForElementToAppear(loginErrorMessage);
        return loginErrorMessage.getText().trim();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue login(String username, String password){
        userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        loginButton.click();
        return new ProductCatalogue(driver);
    }

}
