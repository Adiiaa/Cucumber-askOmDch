package StepDefinitions;

import Hooks.Hook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BrowseByCategorySteps {

    private WebDriver driver;
    private WebDriverWait wait;
    public By categoryDropdown = By.id("product_cat");

    public BrowseByCategorySteps(Hook hook){
        this.driver = hook.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I select any product category")
    public void i_select_any_product_category() {
        driver.findElement(By.linkText("Store")).click();
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(categoryDropdown)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", dropdown);

        Select select = new Select(dropdown);
        select.selectByValue("men");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Men')]")
                )
        );

    }
    @Then("Only the products from that category should be displayed")
    public void only_the_products_from_that_category_should_be_displayed() {
        Assert.assertTrue(
        driver.findElement(
                By.xpath("//h1[contains(text(),'Men')]")
        ).isDisplayed()
        );
    }
}
