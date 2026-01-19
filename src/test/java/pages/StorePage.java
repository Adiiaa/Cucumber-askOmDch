package pages;

import Factory.DriverFactory;
import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StorePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "ul.products li.product a.button")
    private WebElement firstAddToCartBtn;

    @FindBy(css = "a.added_to_cart")
    private WebElement viewCartLink;

    public StorePage() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(Config.STORE_URL);
    }

    public void addFirstProductToCartAndGoToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
    }
}
