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
        storePage.open();
    }

    @And("I add a product to the cart for checkout")
    public void i_add_a_product_to_the_cart_for_checkout() {
        storePage.addFirstProductToCartAndOpenCart();
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

    @And("I submit the checkout form")
    public void i_submit_the_checkout_form() {
        checkoutPage.placeOrder();
    }

    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        Assert.assertTrue(checkoutPage.getSuccessMessage().contains("Thank you"));
    }

    @Then("I should see a checkout error message {string}")
    public void i_should_see_a_checkout_error_message(String expected) {
        Assert.assertTrue(checkoutPage.getErrorMessage().contains(expected));
    }
}
