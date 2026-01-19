package pages;

import Factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "billing_first_name")
    private WebElement firstName;

    @FindBy(id = "billing_last_name")
    private WebElement lastName;

    @FindBy(id = "billing_address_1")
    private WebElement address;

    @FindBy(id = "billing_city")
    private WebElement city;

    @FindBy(id = "billing_postcode")
    private WebElement postcode;

    @FindBy(id = "billing_email")
    private WebElement email;

    @FindBy(id = "place_order")
    private WebElement placeOrderBtn;

    @FindBy(css = ".woocommerce-thankyou-order-received")
    private WebElement successMsg;

    @FindBy(css = ".woocommerce-error")
    private WebElement errorMsg;

    public CheckoutPage() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void waitForCheckoutPage() {
        wait.until(ExpectedConditions.visibilityOf(firstName));
    }

    public void fillBillingForm() {
        firstName.sendKeys("Adia");
        lastName.sendKeys("Uwase");
        address.sendKeys("KG 123 Street");
        city.sendKeys("Kigali");
        postcode.sendKeys("00000");
        email.sendKeys("adia@example.com");
    }

    public void placeOrder() {
        placeOrderBtn.click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successMsg)).getText();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMsg)).getText();
    }
}
