package totalPrice;
import DataProviderTest.DataProviderTest;
import baseTest.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.checkout.CheckOutPage;
import pages.login.LoginPage;
import pages.product.ProductPage;
import pages.product_price.ProductPricePage;

import java.util.List;

public class TotalPriceTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TotalPriceTest.class);

    @Test(dataProvider = "credentials",dataProviderClass = DataProviderTest.class)
    public  void checkTotalPrice(String userName,String password)
    {
        LoginPage loginPage=new LoginPage(driver);
       loginPage.enterUserName(userName);
       loginPage.enterPassword(password);
       loginPage.clickLoginButton();
        ProductPage productPage=new ProductPage(driver);
        String title=productPage.getTitle().getText();;
        Assert.assertEquals(title,"Products");
        List<String> productsName = List.of("backpack", "bike", "t-shirt", "jacket");
        ProductPricePage productPricePage=new ProductPricePage(driver);
        productPricePage.chooseProducts(productsName);
        Assert.assertEquals(
                Integer.parseInt(productPage.getShoppingCartBadge().getText())
                , productPricePage.count);
        productPage.getShoppingCartBadge().click();
       productPricePage.getProductName();
       productPricePage.getProductsCart(productsName);
        Assert.assertEquals(productPricePage.cartCount, productsName.size());
        productPage.getShoppingCartBadge().click();
        CheckOutPage checkOutPage=new CheckOutPage(driver);
        checkOutPage.getCheckOutButton().click();
        checkOutPage.getFirstName().sendKeys("first name test");
        checkOutPage.getLastName().sendKeys("last name test");
        checkOutPage.getPostalCode().sendKeys("123");
        checkOutPage.getContinueButton().click();
       productPricePage. getProductsPriceFromCartPage();
       log.info("checkTotalPrice executed");



    }



}
