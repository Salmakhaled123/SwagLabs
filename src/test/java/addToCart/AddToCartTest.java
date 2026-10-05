package addToCart;

import DataProviderTest.DataProviderTest;
import baseTest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.login.LoginPage;
import pages.product.ProductPage;
import java.util.List;
import java.util.Map;

public class AddToCartTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(AddToCartTest.class);


    @Test(dataProvider = "credentials", dataProviderClass = DataProviderTest.class)
    public void addOneProductToCartAndContinueShopping(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        ProductPage productPage = new ProductPage(driver);
        String title = productPage.getTitle().getText();
        Assert.assertEquals(title, "Products");
        //  productPage.waitToAddProductInCart(Duration.ofSeconds(10));
        productPage.clickOnProduct().click();
        productPage.getShoppingCartBadge().click();
        productPage.getContinueShopping().click();
        Assert.assertEquals(title, "Products");
    }

    @Test(dataProvider = "products", dataProviderClass = DataProviderTest.class)
    public void addMultipleProductsToCart(List<String> products) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(configHandler.getValue("userName"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        ProductPage productPage = new ProductPage(driver);
        String title = productPage.getTitle().getText();
        Assert.assertEquals(title, "Products", "Title is not matched");
        productPage.getProductsList(products);
        Assert.assertEquals(
                Integer.parseInt(productPage.getShoppingCartBadge().getText())
                , productPage.getCount());
        productPage.getShoppingCartBadge().click();
        productPage.getWebProductsElements(products);
        Assert.assertEquals(productPage.getCartCount(), products.size());
        log.info("product:{}", products);
        log.info("products size in add multiple products to cart:{}", products.size());
        Map<String, List<String>> csvProducts = csvFileManager.getColumnsWithData();
        List<String> productsList = csvProducts.get("productName");
        productPage.getProductsFrequency(productsList);
        productPage.getMoreFrequentProducts();

    }





@Test(dataProvider = "credentials", dataProviderClass = DataProviderTest.class)
public void RemoveProductsFromTheCart(String userName, String password) {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUserName(userName);
    loginPage.enterPassword(password);
    loginPage.clickLoginButton();
    ProductPage productPage = new ProductPage(driver);
    String title = productPage.getTitle().getText();
    Assert.assertEquals(title, "Products", "Title is not matched");
    List<String> productsName = List.of("backpack", "bike", "t-shirt", "jacket");
    productPage.getProductsList(productsName);
    Assert.assertEquals(
            Integer.parseInt(productPage.getShoppingCartBadge().getText())
            , productPage.getCount());
    productPage.getShoppingCartBadge().click();
    productPage.removeProductsFromTheCart();


}


@Test(dataProvider = "credentials", dataProviderClass = DataProviderTest.class)
public void addProductsAndGoToSingleProductPageOfEach
        (String userName, String password) {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUserName(userName);
    loginPage.enterPassword(password);
    loginPage.clickLoginButton();
    ProductPage productPage = new ProductPage(driver);
    String title = productPage.getTitle().getText();
    Assert.assertEquals(title, "Products", "Title is not matched");
    List<String> productsName = List.of("backpack", "bike");
    SoftAssert softAssert = new SoftAssert();
    productPage.getHomeProductsList(productsName, softAssert);
    softAssert.assertAll();

}

@Test(dataProvider = "credentials", dataProviderClass = DataProviderTest.class)
public void FilterProducts(String userName, String password) {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUserName(userName);
    loginPage.enterPassword(password);
    loginPage.clickLoginButton();
    ProductPage productPage = new ProductPage(driver);
    String title = productPage.getTitle().getText();
    Assert.assertEquals(title, "Products", "Title is not matched");
    SoftAssert softAssert = new SoftAssert();
    productPage.filterProductsAlphabeticallyFromAToZ(softAssert);
    softAssert.assertAll();


}
}
