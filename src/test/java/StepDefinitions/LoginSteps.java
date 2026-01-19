package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AccountPage;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private final AccountPage accountPage = new AccountPage();

    @Given("the user is on the account page")
    public void the_user_is_on_the_account_page() {
        accountPage.open();
    }

    @When("the user fills the login form with:")
    public void the_user_fills_the_login_form_with(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);
        accountPage.fillLogin(data.get("username"), data.get("password"));
    }

    @When("the user submit the login form")
    public void the_user_submit_the_login_form() {
        accountPage.submitLogin();
    }

    @When("the user enters login username {string}")
    public void the_user_enters_login_username(String username) {
        accountPage.typeLoginUsername(username);
    }

    @When("the user enters login password {string}")
    public void the_user_enters_login_password(String password) {
        accountPage.typeLoginPassword(password);
    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        assertTrue(accountPage.isLoggedIn());
    }

    @Then("the user should see a login error message {string}")
    public void the_user_should_see_a_login_error_message(String errorMessage) {
        String actualMessage = accountPage.getLoginError();
        Assert.assertTrue("Expected error not found. Got: " + actualMessage,
                actualMessage.toLowerCase().contains(errorMessage.toLowerCase()));
    }
}
