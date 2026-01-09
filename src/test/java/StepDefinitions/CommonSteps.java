package StepDefinitions;

import Hooks.Hook;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class CommonSteps {

    private WebDriver driver;

    public CommonSteps(Hook hook){
        this.driver = hook.driver;

    }


    @Given("I am on the AskOmDch account page")
    public void i_am_on_the_ask_om_dch_account_page() {
        driver.get("https://askomdch.com/");
    }


    @Given("I am on the store page")
    public void i_am_on_the_store_page(){
        driver.get("https://askomdch.com/");
    }

}
