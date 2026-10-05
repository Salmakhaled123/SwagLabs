package pages.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.BasePage;

import java.time.Duration;
import java.util.*;

public class ProductPage extends BasePage {
    private static Logger log = LogManager.getLogger(ProductPage.class);

    private final By title = By.className("title");
    private final By productsList = By.xpath("//button[contains(@class,'btn_inventory')]");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By webProductsElements = By.className("inventory_item_name");
    private final By addProductToCart = By.id("add-to-cart-sauce-labs-backpack");
    private final By continueShopping = By.id("continue-shopping");
    private final By shoppingCartContainer = By.id("shopping_cart_container");
    private final By cartProductsList = By.xpath(".//div[@class='cart_item']");
    private final By emptyCart = By.xpath(".//div[@aria-label='Cart, empty']");
    private final By clickableProductName = By.xpath("//div[@class='inventory_item_name ']");
    private final By removeButton = By.id("remove");
    private final By backToProducts = By.id("back-to-products");
    private final By filterOptions = By.className("product_sort_container");
    private int cartCount = 0;
    private int count = 0;
    private final By checkoutButton = By.
            xpath("//button[@class='btn btn_action btn_medium checkout_button ']");
    private Map<String, Integer> productsCount = new HashMap<>();

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitle() {
        log.info("product title:{}", title);
        return findElement(title);
    }

    public int getCount() {
        log.info("count:{}", count);
        return count;
    }

    public List<WebElement> getcartProductsList() {
        log.info("cartProductsList");
        return findElements(cartProductsList, Duration.ofSeconds(10));
    }

    public int getCartCount() {
        log.info("get cart count:{}", cartCount);
        return cartCount;
    }

    public WebElement getEmptyCart() {
        log.info("getti empty cart");
        return findElement(emptyCart, Duration.ofSeconds(10));
    }

    public WebElement getRemoveButton() {
        log.info("get remove button");
        return findElement(removeButton);
    }

    public void getProductsList(List<String> productsName) {
        for (String productName : productsName) {
            List<WebElement> products =
                    findElements(
                            productsList);
            for (WebElement product : products) {
                String productId = product.getAttribute("id");

                if (productId != null && productId.contains(productName)) {
                    product.click();
                    count++;
                    break;
                }
            }
        }
        log.info("productsName :{}", productsName);
    }

    public WebElement getShoppingCartBadge() {
        log.info("get shopping cart badge");
        return findElement(shoppingCartBadge);
    }

    public void getWebProductsElements(List<String> productsName) {
        List<WebElement> webElements = findElements(webProductsElements);

        for (WebElement webElement : webElements) {
            for (String productName : productsName) {
                if (webElement.getText().toLowerCase().contains(productName)) {
                    cartCount++;
                    break;
                }

            }
        }
        log.info("products inside cart:{}", cartCount);
    }

    public WebElement getClickableProductName() {
        log.info("get clickable product name");
        return findElement(clickableProductName);
    }

    public WebElement clickOnProduct() {
        log.debug("click on product");
        return findElement(addProductToCart);
    }

    public WebElement getContinueShopping() {
        log.info("get continue shopping");
        return findElement(continueShopping);
    }

    public WebElement getShoppingCartContainer(Duration duration) {
        log.info("get shopping cart container");
        return findElement(shoppingCartContainer, duration);
    }

    public void removeProductsFromTheCart() {

        List<WebElement> productsCart = getcartProductsList();
        int cartCount = productsCart.size();

        for (WebElement webElement : productsCart) {
            String productName = webElement.findElement(By.xpath(
                            ".//div[@class='inventory_item_name']"))
                    .getText().toLowerCase();
            WebElement removeButton = webElement.findElement
                    (By.xpath(".//button[contains(@id,'remove')]"));
            String buttonId = removeButton.getAttribute("id").toLowerCase();
            log.info("buttonId:{}", buttonId);

            String formattedProductName = productName.replace(" ", "-");
            log.info("formatted product name :{}", formattedProductName);

            if (buttonId.contains(formattedProductName)) {
                removeButton.click();
                cartCount--;
                //  productsCart.remove(webElement);

            }

        }
        Assert.assertEquals(cartCount, 0);

    }

    public WebElement getBackToProducts() {
        log.info("get back to products");
        return findElement(backToProducts);
    }

    public void getHomeProductsList(List<String> productsName, SoftAssert softAssert) {
        log.info("at start of get home product list");
        for (String productName : productsName) {
            List<WebElement> products =
                    findElements(
                            productsList);
            for (WebElement product : products) {
                String productId = product.getAttribute("id");
                if (productId != null && productId.contains(productName)) {
                    product.click();
                    getClickableProductName().click();
                    softAssert.assertTrue(getRemoveButton().isDisplayed(),
                            "Remove button is not  displayed");
                    getBackToProducts().click();
                    count++;
                    break;
                }
            }
        }
    }

    public WebElement getFilterOptions() {
        log.info("get filter options");
        return findElement(filterOptions);
    }

    public void filterProductsAlphabeticallyFromAToZ(SoftAssert softAssert) {
        log.info(" at start of filter products alphabetically method");
        Select option = new Select(getFilterOptions());
        option.selectByValue("az");
        List<WebElement> products = findElements(productsList);
        ArrayList<String> allProducts = new ArrayList<>();
        for (WebElement product : products) {
            String productId = product.getAttribute("id");
            if (productId != null) {
                productId = productId.split("add-to-cart-")[1];
                log.info("product id :{}", productId);
                allProducts.add(productId);


            }
        }
        ArrayList<String> sortedProducts = new ArrayList<>(allProducts);
        Collections.sort(sortedProducts);
        softAssert.assertEquals(allProducts, sortedProducts);


    }

    public WebElement getCheckOutButton() {
        log.info("get check out button");
        return findElement(checkoutButton);
    }


    public void getProductsFrequency(List<String> productsList)
                                      {
        if (productsList != null) {
         for(String product:productsList)
         {
             productsCount.put(product.toLowerCase(),
                     productsCount.getOrDefault(product.toLowerCase(),0)+1);
         }
            log.info("products frequency: {} ",productsCount);

        }
    }

    public void getMoreFrequentProducts() {
       int maxCount=Collections.max(productsCount.values());
       log.info("maxCount :{}",maxCount);
       Map<String,Integer>moreFrequentProducts=new HashMap<>();
       for(Map.Entry<String,Integer> entry:productsCount.entrySet())
       {
           if(entry.getValue()==maxCount)
           {
               moreFrequentProducts.put(entry.getKey(),entry.getValue());
           }
       }
        log.info("More frequent Products :{}", moreFrequentProducts);
    }

}








