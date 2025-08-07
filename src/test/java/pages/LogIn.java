package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LogIn {


    public LogIn() {
        PageFactory.initElements(Driver.getDriver(), this);



    }

    @FindBy(id = "user-name")
    public WebElement userNameInputField;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement userPasswordInputField;

    @FindBy(id = "login-button")
    public WebElement loginButton;









}
