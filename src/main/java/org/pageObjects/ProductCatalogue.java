package org.pageObjects;

import abstractCompenent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    private WebDriver driver;

    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css =".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ngx-spinner-overlay div[class*='ball-scale']")
    WebElement loadingIcon;




    By addToCartButton = By.cssSelector(".card-body button:last-of-type");
    By productNameBy = By.cssSelector("h5 b");


    public List<WebElement> getProductList(){
        waitForElementsToAppear(products);
        return products;
    }

    public WebElement getProductByName(String productName){
        return  getProductList().stream().filter( product -> product.findElement(productNameBy).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }

    public void clickOnAddToCart(WebElement element){
        element.findElement(addToCartButton).click();
        waitForElementToAppear(loadingIcon);
        waitForInvisibilityOfElementBy(By.cssSelector("div[class*='ngx-spinner-overlay']"));

    }





}
