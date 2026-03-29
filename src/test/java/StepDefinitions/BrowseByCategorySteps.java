package StepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.StorePage;

public class BrowseByCategorySteps {

    private final StorePage storePage = new StorePage();

    @Given("I am on the store page")
    public void i_am_on_the_store_page() {
        storePage.open();
    }

    @When("I select the {string} category")
    public void i_select_the_category(String category) {
        storePage.selectCategory(category);
    }

    @Then("Only the products from {string} should be displayed")
    public void only_the_products_from_should_be_displayed(String category) {
        Assert.assertTrue(
                "Products from wrong category are displayed",
                storePage.allProductsBelongToCategory(category)
        );
    }
}
