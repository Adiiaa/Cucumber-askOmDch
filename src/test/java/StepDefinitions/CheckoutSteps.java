package StepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.StorePage;

public class CheckoutSteps {

    StorePage storePage = new StorePage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @Given("I am on the store page")
    public void i_am_on_the_store_page() {
        storePage.open();
    }

    @And("I add a product to the cart for checkout")
    public void i_add_a_product_to_the_cart_for_checkout() {
        storePage.addFirstProductToCartAndGoToCart();
    }

    @And("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage.proceedToCheckout();
        checkoutPage.waitForCheckoutPage();
    }

    @When("I fill the billing form with valid data")
    public void i_fill_the_billing_form_with_valid_data() {
        checkoutPage.fillBillingForm();
    }

    @And("I submit the checkout form")
    public void i_submit_the_checkout_form() {
        checkoutPage.placeOrder();
    }

    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        Assert.assertTrue(
                checkoutPage.getSuccessMessage().contains("Thank you")
        );
    }
}
