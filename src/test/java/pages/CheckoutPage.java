package pages;

import Factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "billing_first_name")
    private WebElement firstName;

    @FindBy(id = "billing_last_name")
    private WebElement lastName;

    @FindBy(id = "billing_country")
    private WebElement countryDropdown;

    @FindBy(id = "billing_address_1")
    private WebElement address;

    @FindBy(id = "billing_city")
    private WebElement city;

    @FindBy(id = "billing_state")
    private WebElement stateInput; // INPUT, NOT SELECT

    @FindBy(id = "billing_postcode")
    private WebElement postcode;

    @FindBy(id = "billing_email")
    private WebElement email;

    @FindBy(id = "place_order")
    private WebElement placeOrderBtn;

    @FindBy(css = ".woocommerce-thankyou-order-received")
    private WebElement successMsg;

    @FindBy(css = ".woocommerce-error li")
    private WebElement errorMsg;

    public CheckoutPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void waitForCheckoutPage() {
        wait.until(ExpectedConditions.visibilityOf(firstName));
    }

    public void fillBillingDetails(String f, String l, String c,
                                   String a, String ci, String s,
                                   String z, String e) {
        typeFirstName(f);
        typeLastName(l);
        selectCountry(c);
        typeAddress(a);
        typeCity(ci);
        typeState(s);
        typePostcode(z);
        typeEmail(e);
    }

    public void typeFirstName(String v) { firstName.clear(); if (!v.isEmpty()) firstName.sendKeys(v); }
    public void typeLastName(String v) { lastName.clear(); if (!v.isEmpty()) lastName.sendKeys(v); }

    public void selectCountry(String v) {
        if (!v.isEmpty()) new Select(countryDropdown).selectByVisibleText(v);
    }

    public void typeAddress(String v) { address.clear(); if (!v.isEmpty()) address.sendKeys(v); }
    public void typeCity(String v) { city.clear(); if (!v.isEmpty()) city.sendKeys(v); }

    public void typeState(String v) {
        stateInput.clear();
        if (!v.isEmpty()) {
            stateInput.sendKeys(v);
            stateInput.sendKeys(Keys.TAB);
        }
    }

    public void typePostcode(String v) { postcode.clear(); if (!v.isEmpty()) postcode.sendKeys(v); }
    public void typeEmail(String v) { email.clear(); if (!v.isEmpty()) email.sendKeys(v); }

    public void placeOrder() { placeOrderBtn.click(); }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMsg));
        return successMsg.getText();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMsg));
        return errorMsg.getText();
    }
}
