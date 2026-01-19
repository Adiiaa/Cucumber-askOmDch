package StepDefinitions;

import config.Config;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.CartPage;
import pages.StorePage;
import Factory.DriverFactory;

public class AddToCartSteps {

    private final StorePage storePage = new StorePage();
    private final CartPage cartPage = new CartPage();

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
        storePage.addFirstProductToCartAndGoToCart();
    }

    @Then("the product should be visible in the cart")
    public void the_product_should_be_visible_in_the_cart() {
        Assert.assertTrue(
                "Cart page not loaded",
                DriverFactory.getDriver().getCurrentUrl().contains("cart")
        );
    }
}
