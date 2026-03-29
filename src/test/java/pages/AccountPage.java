package pages;

import config.Config;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage {

    @FindBy(linkText = "Account") private WebElement accountLink;

    // --- Login Form ---
    @FindBy(id = "username") private WebElement loginUsername;

    @FindBy(id = "password") private WebElement loginPassword;

    @FindBy(name = "login") private WebElement loginButton;

    @FindBy(linkText = "Logout") private WebElement logoutLink;

    // --- Register Form ---
    @FindBy(id = "reg_username") private WebElement regUsername;

    @FindBy(id = "reg_email") private WebElement regEmail;

    @FindBy(id = "reg_password") private WebElement regPassword;

    @FindBy(name = "register") private WebElement registerButton;

    // --- Error container (shared WooCommerce) ---
    @FindBy(css = ".woocommerce-error") private WebElement errorContainer;

    // --- Navigation ---
    public void open() {
        navigateTo(Config.BASE_URL);
        clickWhenClickable(accountLink);
    }

    // --- Login actions ---
    public void fillLogin(String username, String password) {
        typeLoginUsername(username);
        typeLoginPassword(password);
    }

    public void typeLoginUsername(String username) {
        loginUsername.clear();
        if (!username.isEmpty()) {
            loginUsername.sendKeys(username);
        }
    }

    public void typeLoginPassword(String password) {
        loginPassword.clear();
        if (!password.isEmpty()) {
            loginPassword.sendKeys(password);
        }
    }

    public void submitLogin() {
        clickWhenClickable(loginButton);
    }

    public boolean isLoggedIn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(logoutLink));
            return logoutLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginError() {
        return getTextWhenVisible(errorContainer);
    }

    // --- Register actions ---
    // Combined method (for DataTable scenarios)
    public void fillRegister(String username, String email, String password) {
        typeRegisterUsername(username);
        typeRegisterEmail(email);
        typeRegisterPassword(password);
    }

    // Separate methods (for Scenario Outline steps)
    public void typeRegisterUsername(String username) {
        regUsername.clear();
        if (!username.isEmpty()) {
            regUsername.sendKeys(username);
        }
    }

    public void typeRegisterEmail(String email) {
        regEmail.clear();
        if (!email.isEmpty()) {
            regEmail.sendKeys(email);
        }
    }

    public void typeRegisterPassword(String password) {
        regPassword.clear();
        if (!password.isEmpty()) {
            regPassword.sendKeys(password);
        }
    }

    public void submitRegister() {
        clickWhenClickable(registerButton);
    }

    public String getRegisterError() {
        return getTextWhenVisible(errorContainer);
    }
}
