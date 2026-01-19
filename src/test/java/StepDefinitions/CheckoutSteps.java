package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.StorePage;

import java.util.Map;

public class CheckoutSteps {

    private final StorePage storePage = new StorePage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @Given("I am on the store page")
    public void i_am_on_the_store_page() {
        // Using StorePage open keeps navigation consistent
        storePage.open();
    }

    @And("I add a product to the cart")
    public void i_add_a_product_to_the_cart() {
        // Adds product AND navigates to cart page
        storePage.addFirstProductToCartAndGoToCart();
    }

    @And("I navigate to the cart page")
    public void i_navigate_to_the_cart_page() {
        // No action needed – already on cart page
        // Step kept for BDD readability
    }

    @And("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage.clickProceedToCheckout();
        checkoutPage.waitForCheckoutPage();
    }

    @When("I fill the billing form with:")
    public void i_fill_the_billing_form_with(DataTable table) {
        Map<String, String> data = table.asMaps(String.class, String.class).get(0);

        checkoutPage.fillBillingDetails(
                data.get("firstName"),
                data.get("lastName"),
                data.get("country"),
                data.get("streetAddress"),
                data.get("city"),
                data.get("state"),
                data.get("zipcode"),
                data.get("email")
        );
    }

    @When("I enter billing first name {string}")
    public void i_enter_billing_first_name(String firstName) {
        checkoutPage.fillBillingDetails(firstName, "", "", "", "", "", "", "");
    }

    @When("I enter billing last name {string}")
    public void i_enter_billing_last_name(String lastName) {
        checkoutPage.fillBillingDetails("", lastName, "", "", "", "", "", "");
    }

    @When("I select billing country {string}")
    public void i_select_billing_country(String country) {
        checkoutPage.selectCountry(country);
    }

    @When("I enter billing street address {string}")
    public void i_enter_billing_street_address(String streetAddress) {
        checkoutPage.fillBillingDetails("", "", "", streetAddress, "", "", "", "");
    }

    @When("I enter billing city {string}")
    public void i_enter_billing_city(String city) {
        checkoutPage.fillBillingDetails("", "", "", "", city, "", "", "");
    }

    @When("I select billing state {string}")
    public void i_select_billing_state(String state) {
        checkoutPage.selectState(state);
    }

    @When("I enter billing zipcode {string}")
    public void i_enter_billing_zipcode(String zipcode) {
        checkoutPage.fillBillingDetails("", "", "", "", "", "", zipcode, "");
    }

    @When("I enter billing email {string}")
    public void i_enter_billing_email(String email) {
        checkoutPage.fillBillingDetails("", "", "", "", "", "", "", email);
    }

    @And("I submit the checkout form")
    public void i_submit_the_checkout_form() {
        checkoutPage.submitOrder();
    }

    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        Assert.assertTrue(
                "Order success message not displayed",
                checkoutPage.getSuccessMessage().contains("Thank you")
        );
    }

    @Then("I should see a checkout error message {string}")
    public void i_should_see_a_checkout_error_message(String expectedMessage) {
        Assert.assertTrue(
                "Expected checkout error not found. Actual errors: " + checkoutPage.getAllCheckoutErrors(),
                checkoutPage.hasCheckoutError(expectedMessage)
        );
    }

}