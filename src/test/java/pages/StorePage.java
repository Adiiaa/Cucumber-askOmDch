package pages;

import Factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import util.Constants;

import java.time.Duration;
import java.util.List;

public class StorePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Category ----------
    @FindBy(id = "product_cat")
    private WebElement categoryDropdown;

    @FindBy(css = "ul.products li.product")
    private List<WebElement> products;

    // ---------- Add to cart ----------
    @FindBy(css = "ul.products li.product a.add_to_cart_button")
    private WebElement firstAddToCartBtn;

    @FindBy(css = "a.wc-forward")
    private WebElement viewCartLink;

    public StorePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://askomdch.com/store");
        wait.until(ExpectedConditions.visibilityOf(categoryDropdown));
    }


    public String addFirstProductToCart() {
        WebElement firstProduct = driver.findElement(By.cssSelector("ul.products li.product"));


        WebElement firstProductTitle = firstProduct.findElement(By.cssSelector("h2.woocommerce-loop-product__title"));
        String productName = firstProductTitle.getText();

        if (productName == null || productName.isEmpty()) {
            throw new RuntimeException("Product title not found!");
        }


        WebElement addToCartBtn = firstProduct.findElement(By.cssSelector("a.add_to_cart_button"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

        return productName;
    }



    public void addFirstProductToCartAndOpenCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(viewCartLink)).click();
    }

    public void selectCategory(String value) {
        new Select(categoryDropdown).selectByValue(value);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("ul.products li.product")
        ));
    }
}
