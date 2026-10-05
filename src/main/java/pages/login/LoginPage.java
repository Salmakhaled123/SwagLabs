package pages.login;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class LoginPage extends BasePage {
    private Logger log = LogManager.getLogger(LoginPage.class);

    private  final By userNameField=By.id("user-name");
    private  final By passwordField=By.id("password");
    private  final By loginButton=By.id("login-button");
    private  final By errorContainer=By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public WebElement getUserNameField()
    {
        log.info("Getting username field");

        return findElement(userNameField);
    }
    public WebElement getPasswordField()
    {
        log.info("Getting password field");

        return findElement(passwordField);
    }
    public WebElement getLoginButton()
    {
        log.info("Getting login button");

        return findElement(loginButton);
    }
    public  void enterUserName(String userName)
    {
        getUserNameField().clear();
        getUserNameField().sendKeys(userName);
        log.debug("Enter Username: {}", userName);
    }
    public  void enterPassword(String password)
    {
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
        log.debug("Enter password: {}",password);
    }
    public  void clickLoginButton()
    {

        getLoginButton().click();
        log.info("login button clicked");
    }
    public  WebElement getErrorMessage()
    {
        log.info("get error message");
        return  findElement(errorContainer);
    }


}
