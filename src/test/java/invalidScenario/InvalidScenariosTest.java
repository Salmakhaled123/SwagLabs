package invalidScenario;

import DataProviderTest.DataProviderTest;
import baseTest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.checkout.CheckOutPage;
import pages.login.LoginPage;
import pages.product.ProductPage;

import java.time.Duration;


public class InvalidScenariosTest extends BaseTest {
    private Logger log = LogManager.getLogger(InvalidScenariosTest.class);


    @Test(dataProvider = "credentials", dataProviderClass = DataProviderTest.class)
    public void CheckOutWithAddingTwoFieldsOnly(String userName,String password) {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        ProductPage productPage=new ProductPage(driver);
        String title = productPage.getTitle().getText();
        Assert.assertEquals(title, "Products");
        productPage.getShoppingCartContainer(Duration.ofSeconds(10)).click();
        Assert.assertTrue(productPage.getCheckOutButton()
                .isDisplayed());
        productPage.getCheckOutButton().click();
        CheckOutPage checkOutPage=new CheckOutPage(driver);
        checkOutPage.getFirstName().sendKeys("toty");
        checkOutPage.getLastName().sendKeys("khaled");
        checkOutPage.getSubmitButton().click();
        Assert.assertTrue(checkOutPage.getCheckoutErrorMessage().isDisplayed());
        log.info("CheckOutWithAddingTwoFieldsOnly executed");

    }

    @Test(dataProvider = "credentials",dataProviderClass = DataProviderTest.class)
    public void finishTheCheckOutProcessWithOutAddingProduct(String userName,String password)
    {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
         ProductPage productPage=new ProductPage(driver);
        String title = productPage.getTitle().getText();
        Assert.assertEquals(title, "Products");
        productPage.getShoppingCartContainer(Duration.ofSeconds(5)).click();
        CheckOutPage checkOutPage=new CheckOutPage(driver);
        checkOutPage.getCheckOut()
                .click();
        checkOutPage.getFirstName().sendKeys("test");
        checkOutPage.getLastName().sendKeys("test");
        checkOutPage.getPostalCode().sendKeys("123");

        checkOutPage.getContinueButton().click();
        checkOutPage.getFinishButton().click();
        log.info("finishTheCheckOutProcessWithOutAddingProduct executed");

    }


    @Test(dataProvider = "credentials", dataProviderClass = DataProviderTest.class)
    public void CheckOutWithOutAddingAnyField(String userName, String password) {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        ProductPage productPage=new ProductPage(driver);
        String title = productPage.getTitle().getText();
        Assert.assertEquals(title, "Products");
        CheckOutPage checkOutPage=new CheckOutPage(driver);
        productPage.getShoppingCartContainer(Duration.ofSeconds(10)).click();
       productPage.getCheckOutButton().click();
       checkOutPage.getContinueButton().click();
       checkOutPage.getCheckoutErrorMessage();
        Assert.assertTrue(checkOutPage.getCheckoutErrorMessage().isDisplayed());
        log.info("CheckOutWithOutAddingAnyField executed");

    }
}
