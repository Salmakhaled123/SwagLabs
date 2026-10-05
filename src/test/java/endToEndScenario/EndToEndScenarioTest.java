package endToEndScenario;

import baseTest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.checkout.CheckOutPage;
import pages.login.LoginPage;
import pages.product.ProductPage;

public class EndToEndScenarioTest extends BaseTest {
    private Logger log = LogManager.getLogger(EndToEndScenarioTest.class);


    @Test
    public void endToEndTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(checkoutInfo.getValue("userName").toString());
        loginPage.enterPassword(checkoutInfo.getValue("password").toString());
        loginPage.clickLoginButton();
        ProductPage productPage=new ProductPage(driver);
        String title = productPage.getTitle().getText();
        Assert.assertEquals(title, "Products");
        productPage.clickOnProduct().click();
        productPage.getShoppingCartBadge().click();
        CheckOutPage checkOutPage=new CheckOutPage(driver);
        checkOutPage.getCheckOutButton().click();
        checkOutPage.getFirstName().sendKeys(checkoutInfo.getValue("firstName").toString());
        checkOutPage.getLastName().sendKeys(checkoutInfo.getValue("lastName").toString());
        checkOutPage.getPostalCode().sendKeys(checkoutInfo.getValue("postalCode").toString());
        checkOutPage.getContinueButton().click();
        checkOutPage.getFinishButton().click();
        Assert.assertEquals(checkOutPage.getThankYouForYourOrderMessage().getText(),
                "Thank you for your order!");
        log.info("end to end scenario happened ");

    }

}
