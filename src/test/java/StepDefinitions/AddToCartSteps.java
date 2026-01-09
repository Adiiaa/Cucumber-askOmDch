package StepDefinitions;

import Hooks.Hook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartSteps {
    private WebDriver driver;

    public AddToCartSteps(Hook hook){
        this.driver = hook.driver;
    }


    @When("I click add to product button on the product")
    public void i_click_add_to_product_button_on_the_product() {
        driver.findElement(By.linkText("Store")).click();
        driver.findElement(By.cssSelector("a[data-product_id='1198']")).click();


    }
    @Then("then the product should be visible to cart")
    public void then_the_product_should_be_visible_to_cart() {
        driver.findElement(By.linkText("View cart")).click();
        driver.findElement(By.xpath("//h1[text()='Cart']")).isDisplayed();


    }
}
