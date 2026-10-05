package DataProviderTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import utilities.ExcelFileManager;
import utilities.JSONFileManager;

import java.util.ArrayList;
import java.util.List;

public class DataProviderTest {
    private static  Logger log = LogManager.getLogger(DataProviderTest.class);
    JSONFileManager login = new JSONFileManager("src/main/resources/login.json");
    JSONFileManager invalidLogin = new JSONFileManager("src/main/resources/invalidlogin.json");
    JSONFileManager checkoutInfo = new JSONFileManager("src/main/resources/checkout.json");
    ExcelFileManager excelFileManager =
            new ExcelFileManager("src/main/resources/product.xlsx","Sheet1");

    @DataProvider(name = "credentials")
    public Object[][] getLoginCredentials() {
        log.info("valid login credentials is username=standard_user password=secret_sauce");
        return new Object[][]
                {
                        {
                                login.getValue("userName"),
                                login.getValue("password")
                        }
                };

    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] getInvalidLoginCredentials() {

       List userNames=(List) invalidLogin.getValue("username");
       List passwords=(List) invalidLogin.getValue("password");
        Object[][] newData = new Object[userNames.size()][2];

        for (int i = 0; i < userNames.size(); i++)
        {
            newData[i][0] = userNames.get(i);
            newData[i][1] = passwords.get(i);

        }
        return newData;

    }

    @DataProvider(name = "checkoutInfo")
    public Object[][] getCheckOutInfo() {
        return new Object[][]{{
                checkoutInfo.getValue("userName"),
                checkoutInfo.getValue("password"),
                checkoutInfo.getValue("firstName"),
                checkoutInfo.getValue("lastName"),
                checkoutInfo.getValue("postalCode")
        }};
    }
    @DataProvider(name = "products")
    public Object[][] getProducts()
    {
        log.info("products inside excel:bike,jacket");
       int rows=excelFileManager.getRowsCount();
       List<String>allProducts=new ArrayList<>();
       for(int i=1;i<rows;i++)
       {
           allProducts.add(excelFileManager.getSpecificCellValue(i,0));
       }
       log.info("all products from excel file:{}",allProducts.size());
       return new Object[][]{
               {allProducts}
       };
    }
}
