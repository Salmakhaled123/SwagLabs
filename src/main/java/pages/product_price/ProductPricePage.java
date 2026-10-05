package pages.product_price;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.BasePage;

import java.util.List;

public class ProductPricePage extends BasePage {
    private Logger log = LogManager.getLogger(ProductPricePage.class);

    private final By productButton = By.xpath("//button[contains(@class,'btn_inventory')]");
    private final By productName = By.
            className("inventory_item_name");
    private final By productPrice = By.className("inventory_item_price");
    private final By subTotalPrice = By.className("summary_subtotal_label");
    private final By taxes = By.className("summary_tax_label");
    private final By summaryTotal = By.className("summary_total_label");
    public int count = 0;
    public int cartCount = 0;

    public ProductPricePage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getProductButton() {
        log.info("get Product Button");

        return findElements(productButton);
    }

    public List<WebElement> getProductName() {
        log.info("get Product Name");

        return findElements(productName);
    }

    public List<WebElement> getProductsPrice() {
        log.info("get Products Price");

        return findElements(productPrice);
    }

    public WebElement getSubTotalPrice()
    {
        log.info("get subtotal total price");
        return findElement(subTotalPrice);
    }

    public WebElement getTaxes() {
        log.info("get taxes");

        return findElement(taxes);
    }

    public WebElement getTotal() {
        log.info("get total");

        return findElement(summaryTotal);
    }


    public void chooseProducts(List<String> productsName) {
        log.info("at the start of choose products method");
        for (String productName : productsName) {
            List<WebElement> products =
                    getProductButton();
            for (WebElement product : products) {
                String productId = product.getAttribute("id");
                if (productId != null && productId.contains(productName)) {
                    product.click();
                    count++;
                    log.info("count :{}",count);
                    break;
                }
            }
        }
    }

    public void getProductsCart(List<String> productsName) {
        log.info("at the start of the get products cart method");

        List<WebElement> webElements = getProductName();

        for (WebElement webElement : webElements) {
            for (String productName : productsName) {
                if (webElement.getText().toLowerCase().contains(productName)) {
                    cartCount++;
                    break;
                }

            }
        }


    }

    public void getProductsPriceFromCartPage() {
        List<WebElement> priceList = getProductsPrice();
        double totalPrice = 0;
        for (WebElement price : priceList) {

            totalPrice += Double.parseDouble(price.getText().replace("$", ""));
        }
        String actualTotalPrice = getSubTotalPrice().getText();
        String actualTotalPriceValue = Double.toString(totalPrice);
        Assert.assertEquals(actualTotalPrice.split("\\$")[1], actualTotalPriceValue);
        String tax = getTaxes().getText();
        String taxAmount = tax.split("\\$")[1];
        double taxValue = Double.parseDouble(taxAmount);
        String finalTotal = String.format("%.2f", taxValue + totalPrice);
        String summaryTotalLabel = getTotal().getText();
        Assert.assertEquals(summaryTotalLabel.split("\\$")[1], finalTotal);


    }
}
