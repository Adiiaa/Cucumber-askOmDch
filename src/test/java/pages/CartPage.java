package pages;

import Factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Constants;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By productNameCells = By.cssSelector("td.product-name");

    public CartPage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT_SECONDS));
    }

    // Navigate to cart and wait for products to appear
    public void open() {
        driver.get("https://askomdch.com/cart");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productNameCells));
    }

    public String getCartProductName() {
        List<WebElement> products = driver.findElements(By.cssSelector("td.product-name"));

        if (products.isEmpty()) {
            return "";
        }

        return products.get(0).getText(); // first product in cart
    }
}
