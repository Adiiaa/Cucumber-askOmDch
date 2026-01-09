package StepDefinitions;

import Hooks.Hook;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegisterSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterSteps(Hook hook){
        this.driver = hook.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I register with valid credentials")
    public void i_register_with_valid_credentials() {
        driver.findElement(By.linkText("Account")).click();
        driver.findElement(By.id("reg_username")).sendKeys("example3");
        driver.findElement(By.id("reg_email")).sendKeys("example@test3.com");
        driver.findElement(By.id("reg_password")).sendKeys("example@test3");
        driver.findElement(By.name("register")).click();

    }
    @Then("My account should be created successfully")
    public void my_account_should_be_created_successfully() {
        assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
    }
}
