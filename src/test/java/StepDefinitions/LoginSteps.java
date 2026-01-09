package StepDefinitions;


import Hooks.Hook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginSteps{
    private WebDriver driver;

    public LoginSteps(Hook hook){
        this.driver = hook.driver;
    }


    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        driver.findElement(By.linkText("Account")).click();
        driver.findElement(By.id("username")).sendKeys("Testing Web");
        driver.findElement(By.id("password")).sendKeys("testing@12");
        driver.findElement(By.name("login")).click();

    }
    @Then("I should be on dashboard page")
    public void i_should_be_on_dashboard_page() {

        assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

    }

}