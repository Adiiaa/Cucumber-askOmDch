package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AccountPage;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RegisterSteps {

    private final AccountPage accountPage = new AccountPage();

    @When("the user fills the registration form with:")
    public void the_user_fills_the_registration_form_with(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);
        accountPage.open();
        accountPage.fillRegister(data.get("username"), data.get("email"), data.get("password"));
    }

    @When("the user submits the registration form")
    public void the_user_submits_the_registration_form() {
        accountPage.submitRegister();
    }

    @Then("the account should be created successfully")
    public void the_account_should_be_created_successfully() {
        Assert.assertTrue("User was not logged in after registration",
                accountPage.isLoggedIn());
    }

    @When("the user enters username {string}")
    public void the_user_enters_username(String username) {
        accountPage.typeRegisterUsername(username);
    }

    @When("the user enters email {string}")
    public void the_user_enters_email(String email) {
        accountPage.typeRegisterEmail(email);
    }

    @When("the user enters password {string}")
    public void the_user_enters_password(String password) {
        accountPage.typeRegisterPassword(password);
    }


    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String errorMessage) {
        String actualMessage = accountPage.getRegisterError();
        Assert.assertTrue("Expected error not found. Got: " + actualMessage,
                actualMessage.toLowerCase().contains(errorMessage.toLowerCase()));
    }
}
