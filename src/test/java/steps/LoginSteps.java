package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LogIn;
import utilities.Config;
import utilities.Driver;

import java.lang.module.Configuration;

public class LoginSteps {
    LogIn logIn = new LogIn();



    @Given("user is on log in page")
    public void user_is_on_log_in_page() {
        Driver.getDriver().get(Config.getProperty("SauceDemo"));

    }
    @When("user provides a valid passowrd")
    public void user_provides_a_valid_passowrd() {
        logIn.userNameInputField.sendKeys(Config.getProperty("UserName"));

    }
    @When("user provides a valid username")
    public void user_provides_a_valid_username() {
        logIn.userPasswordInputField.sendKeys(Config.getProperty("userPassword"));

    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        logIn.loginButton.click();

    }
    @Then("verify user is loged in")
    public void verify_user_is_loged_in() {

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectedUrl, Driver.getDriver().getCurrentUrl());

    }
}
