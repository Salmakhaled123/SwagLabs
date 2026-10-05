package login;

import DataProviderTest.DataProviderTest;
import baseTest.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.product.ProductPage;

public class LoginTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test(dataProvider = "credentials",dataProviderClass = DataProviderTest.class)
    public void validLoginTest(String userName,String password) {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        ProductPage productPage=new ProductPage(driver);
        String title = productPage.getTitle().getText();
        Assert.assertEquals(title, "Products");
        log.info("Logging in with {} and {}",userName,password);
        log.info("Logging in with {} and {}",
                configHandler.getValue("userName"),
                configHandler.getValue("password"));
        log.info("cli :{}",System.getProperty("ENVIRONMENT"));
        log.info("cli :{}",System.getProperty("BROWSERNAME"));


    }


    @Test(dataProvider = "invalidCredentials",dataProviderClass = DataProviderTest.class)
    public void invalidLoginTest(String userName,String password) {
       LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
       loginPage.clickLoginButton();
        boolean displayed = loginPage.getErrorMessage().isDisplayed();
        Assert.assertTrue(displayed);
        log.info("Logging in with {} and {} failed",userName,password);



    }


}
