package StepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.CartPage;
import pages.StorePage;
import pages.CheckoutPage;
import config.Config;
import Factory.DriverFactory;

public class AddToCartSteps {

    private final StorePage storePage = new StorePage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();
    private String selectedProductName;

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        DriverFactory.getDriver().get(Config.BASE_URL);
    }

    @When("I navigate to the store page")
    public void i_navigate_to_the_store_page() {
        storePage.open();
    }

    @When("I add a product to the cart from the store")
    public void i_add_a_product_to_the_cart_from_the_store() {
        selectedProductName = storePage.addFirstProductToCart();
        storePage.clickViewCart(); // go to cart after adding
    }

    @When("I add a featured product to the cart")
    public void i_add_a_featured_product_to_the_cart() {
        selectedProductName = storePage.addFeaturedProductToCart();
        storePage.clickViewCart(); // go to cart after adding
    }

    @Then("the product should be visible in the cart")
    public void the_product_should_be_visible_in_the_cart() {
        String cartProductName = cartPage.getCartProductName();
        Assert.assertTrue(
                "Expected product not found in cart",
                cartProductName.contains(selectedProductName)
        );
    }

    // --- Extension for checkout flow ---
    @And("I proceed to checkout from the cart")
    public void i_proceed_to_checkout_from_the_cart() {
        cartPage.clickProceedToCheckout();
        checkoutPage.waitForCheckoutPage();
    }
}
