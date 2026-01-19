package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(css = ".cart_item .product-name a")
    private WebElement cartProductName;

    @FindBy(css = ".checkout-button")
    private WebElement proceedToCheckoutButton;


    public String getCartProductName() {
        return getTextWhenVisible(cartProductName);
    }

    public void clickProceedToCheckout() {
        clickWhenClickable(proceedToCheckoutButton);
    }
}
