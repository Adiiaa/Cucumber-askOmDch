package pages;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StorePage extends BasePage {

    // First product card in the store listing
    @FindBy(css = "ul.products li.product")
    private WebElement firstProductCard;

    // Product name inside a product card
    private By productNameInCard = By.cssSelector("h2.woocommerce-loop-product__title");

    // Add to cart button inside a product card
    private By addToCartButtonInCard = By.cssSelector("a.add_to_cart_button");

    // "View cart" link that appears after adding
    @FindBy(css = "a.added_to_cart")
    private WebElement viewCartLink;

    // Featured products section on home page (adjust selector if needed)
    @FindBy(css = "section[id*='featured'], section.featured-products, .wp-block-woocommerce-featured-products")
    private WebElement featuredSection;

    // First featured product card
    private By firstFeaturedProductCard = By.cssSelector("section[id*='featured'] ul.products li.product, .featured-products ul.products li.product");

    public void open() {
        navigateTo(Config.STORE_URL);
    }

    // Add first product from Store listing, return its name
    public String addFirstProductToCart() {
        waitForVisibility(firstProductCard);
        String name = firstProductCard.findElement(productNameInCard).getText();
        clickWhenClickable(firstProductCard.findElement(addToCartButtonInCard));
        return name;
    }

    // Add first product from Featured section on Home page, return its name
    public String addFeaturedProductToCart() {
        // Navigate to home first to ensure featured section is present
        navigateTo(Config.BASE_URL);
        waitForVisibility(featuredSection);

        WebElement firstFeaturedCard = featuredSection.findElement(firstFeaturedProductCard);
        String name = firstFeaturedCard.findElement(productNameInCard).getText();
        clickWhenClickable(firstFeaturedCard.findElement(addToCartButtonInCard));
        return name;
    }
    public String addFirstProductToCartAndGoToCart() {
        // wait for the first product card
        waitForVisibility(firstProductCard);

        // get product name from the card
        String productName = firstProductCard.findElement(productNameInCard).getText();

        // click add to cart button inside the card
        clickWhenClickable(firstProductCard.findElement(addToCartButtonInCard));

        // click view cart link
        clickWhenClickable(viewCartLink);

        return productName;
    }



    // Click the "View cart" link that appears after adding
    public void clickViewCart() {
        clickWhenClickable(viewCartLink);
    }
}
