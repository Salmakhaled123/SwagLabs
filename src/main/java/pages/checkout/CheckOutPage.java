package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class CheckOutPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(CheckOutPage.class);

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    private  final By checkOutButton=By.xpath("//button[@id='checkout']");
    private  final  By firstName=By.id("first-name");
    private  final  By lastName=By.id("last-name");
    private  final  By postalCode=By.id("postal-code");
    private  final  By continueButton=By.id("continue");
    private  final  By checkOut=By.id("checkout");
    private  final  By finishButton=By.xpath("//button[@class='btn btn_action btn_medium cart_button']");
    private  final  By thankYouForYourOrderMessage=By.className("complete-header");
    private  final  By submitButton=By.xpath("//input[@class='submit-button btn btn_primary cart_button btn_action']");
    private final  By checkOutErrorMessage=By.xpath("//div[@class='error-message-container error']");

    public WebElement getCheckOutButton()
    {
        log.info("get checkout button");
        return  findElement(checkOutButton);
    }
    public  WebElement getFirstName()
    {
        log.info("get first name");
        return  findElement(firstName);
    }
    public  WebElement getLastName()
    {
        log.info("get last name");
        return  findElement(lastName);
    }
    public  WebElement getPostalCode()
    {
        log.info("get postal code");
        return  findElement(postalCode);
    }

    public  WebElement getContinueButton()
    {
        log.info("get continue button");
        return  findElement(continueButton);
    }
    public  WebElement getFinishButton()
    {
        log.info("get Finish Button");
        return  findElement(finishButton);
    }
    public  WebElement getThankYouForYourOrderMessage()
    {
        log.info("get Thank You For Your Order Message");

        return  findElement(thankYouForYourOrderMessage);
    }
    public  WebElement getCheckOut()
    {
        log.info("get CheckOut");

        return  findElement(checkOut);
    }
    public  WebElement getSubmitButton()
    {
        log.info("get Submit Button");

        return  findElement(submitButton);
    }
    public  WebElement getCheckoutErrorMessage()
    {
        log.info("get Checkout Error Message");
        return  findElement(checkOutErrorMessage);
    }
}

