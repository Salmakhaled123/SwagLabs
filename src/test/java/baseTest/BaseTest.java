package baseTest;

import driverFactory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BaseTest {
    private Logger log = LogManager.getLogger(BaseTest.class);

    public static   WebDriver driver;
   public SoftAssert softAssert;
   public WebDriverWait wait;
   String browserName="Edge";
   public ConfigHandler configHandler;
   public JSONFileManager jsonFileManager;
   public  JSONFileManager checkoutInfo;
   public  JSONFileManager invalidLogin;
   public ExcelFileManager excelFileManager;
   public CSVFileManager csvFileManager;
   public  JSONFileManager login;
    @BeforeMethod
    public void setUp() {
        csvFileManager=new CSVFileManager("src/main/resources/products.csv");
        login=new JSONFileManager("src/main/resources/login.json");
        invalidLogin=new JSONFileManager("src/main/resources/invalidlogin.json");
        jsonFileManager=new JSONFileManager("src/main/resources/product.json");
        excelFileManager=new ExcelFileManager("src/main/resources/product.xlsx","Sheet1");
        checkoutInfo=new JSONFileManager("src/main/resources/checkout.json");
        configHandler=new ConfigHandler("src/main/resources/config.properties");
        driver = DriverFactory.getWebDriver(configHandler.getValue("browserName"));
        driver.get(configHandler.getValue("url"));
        driver.manage().window().maximize();
         log.info("setup driver Successfully");


    }
    @AfterMethod
     public  void failedTestCases(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE)
        {
           File image= ScreenShot.takeScreenShot(driver);
            FileInputStream fis=new FileInputStream(image);
            Allure.addAttachment("Failure screenshot for Tc : "
                            +result.getName(),"image/png",
                    fis,"png");
        }
    }
    @AfterMethod
    public void tearDown()
    {
      DriverFactory.quitWebDriver(configHandler.getValue("browserName"));
      driver=null;
      log.info(" driver is null now in teardown");

    }
}
