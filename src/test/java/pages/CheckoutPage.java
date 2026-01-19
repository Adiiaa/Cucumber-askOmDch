package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {

    // Billing fields
    @FindBy(id = "billing_first_name") private WebElement billingFirstName;
    @FindBy(id = "billing_last_name") private WebElement billingLastName;
    @FindBy(id = "billing_country") private WebElement billingCountry;
    @FindBy(id = "billing_address_1") private WebElement billingStreet;
    @FindBy(id = "billing_city") private WebElement billingCity;
    @FindBy(id = "billing_state") private WebElement billingState;
    @FindBy(id = "billing_postcode") private WebElement billingPostcode;
    @FindBy(id = "billing_email") private WebElement billingEmail;

    // Buttons & messages
    @FindBy(id = "place_order") private WebElement placeOrderButton;
    @FindBy(css = ".woocommerce-error") private WebElement errorContainer;
    @FindBy(css = ".woocommerce-thankyou-order-received") private WebElement successMessage;

    public void waitForCheckoutPage() {
        waitForVisibility(billingFirstName);
    }

    // Combined (DataTable)
    public void fillBillingDetails(String firstName, String lastName, String country,
                                   String street, String city, String state,
                                   String postcode, String email) {
        typeBillingFirstName(firstName);
        typeBillingLastName(lastName);
        selectCountry(country);
        typeBillingStreet(street);
        typeBillingCity(city);
        selectState(state);
        typeBillingPostcode(postcode);
        typeBillingEmail(email);
    }

    // Single-field methods (Scenario Outline)
    public void typeBillingFirstName(String value) { type(billingFirstName, value); }
    public void typeBillingLastName(String value) { type(billingLastName, value); }
    public void typeBillingStreet(String value) { type(billingStreet, value); }
    public void typeBillingCity(String value) { type(billingCity, value); }
    public void typeBillingPostcode(String value) { type(billingPostcode, value); }
    public void typeBillingEmail(String value) { type(billingEmail, value); }

    // --- Country selection (Select2) ---
    public void selectCountry(String country) {
        if (country == null || country.isEmpty()) return;

        WebElement countrySelect2 = driver.findElement(By.cssSelector("#billing_country + .select2 .select2-selection"));
        countrySelect2.click();

        WebElement searchBox = driver.findElement(By.cssSelector(".select2-container .select2-search__field"));
        searchBox.sendKeys(country);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[contains(@class,'select2-results__option') and contains(normalize-space(),'" + country + "')]")));
        result.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));
    }

    // --- State selection (select vs input) ---
    public void selectState(String state) {
        if (state == null || state.isEmpty()) return;

        WebElement stateElem = driver.findElement(By.id("billing_state"));
        String tag = stateElem.getTagName();

        if ("select".equalsIgnoreCase(tag)) {
            try {
                new Select(stateElem).selectByVisibleText(state);
            } catch (Exception e) {
                // fallback if option not found
                stateElem.clear();
                stateElem.sendKeys(state);
            }
        } else {
            stateElem.clear();
            stateElem.sendKeys(state);
        }
    }

    // --- Submit order ---
    public void submitOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();

        // Wait for either success or error
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-notices-wrapper .woocommerce-error")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-order"))
        ));
    }

    // --- Success & error messages ---
    public String getSuccessMessage() {
        return getTextWhenVisible(successMessage);
    }

    public List<String> getAllCheckoutErrors() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-notices-wrapper .woocommerce-error")));
        List<WebElement> errors = driver.findElements(By.cssSelector(".woocommerce-notices-wrapper .woocommerce-error li"));
        return errors.stream().map(e -> e.getText().trim()).toList();
    }

    public boolean hasCheckoutError(String expected) {
        return getAllCheckoutErrors().stream()
                .anyMatch(msg -> msg.toLowerCase().contains(expected.toLowerCase()));
    }

    // --- Helper ---
    private void type(WebElement element, String value) {
        element.clear();
        if (value != null && !value.isEmpty()) {
            element.sendKeys(value);
        }
    }
}
