package abstractCompenent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.CartPage;
import org.pageObjects.OrderPage;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {

    private WebDriver driver;
    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(css ="button[routerlink*='myorders']")
    WebElement ordersButton;
    public void waitForElementsToAppear(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementToAppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void waitForInvisibilityOfElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void waitForElementBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForInvisibilityOfElementBy(By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public CartPage clickOnAddToCart(){
        waitForElementBeClickable(cartButton);
        cartButton.click();
        return new CartPage(driver);
    }

    public OrderPage clickOnOrders(){
        waitForElementBeClickable(ordersButton);
        ordersButton.click();
        return  new OrderPage(driver);
    }
}
