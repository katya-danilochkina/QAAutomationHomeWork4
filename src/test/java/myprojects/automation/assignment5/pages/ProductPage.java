package myprojects.automation.assignment5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductPage {
    private EventFiringWebDriver driver;
    private By addToCartButton = By.cssSelector("button.add-to-cart");
    private By cartPageLink = By.cssSelector(".cart-content .btn-primary");
    private By productPrice = By.cssSelector(".product-price .current-price");
    private By productName = By.xpath("//*[@id=\"wrapper\"]/div/nav/ol/li[5]/a/span");
    private By detailedOrderInfoLink = By.cssSelector("[href=\"#product-details\"]");
    private By productQuantity = By.cssSelector(".product-quantities span");

    public ProductPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
    }

    private void waitForAddToCartPopupLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageLink));
    }

    public void addToCart() {
        waitForPageLoad();
        driver.findElement(addToCartButton).click();
    }

    public void clickCartPageLink() {
        waitForAddToCartPopupLoad();
        driver.findElement(cartPageLink).click();
    }

    public void switchToDetailedOrderInfo() {
        waitForPageLoad();
        driver.findElement(detailedOrderInfoLink).click();
    }

    public String readQuantityOfOpenedProduct() {
        waitForPageLoad();
        WebElement quantityElement = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(productQuantity));
        return quantityElement.getText().split(" ")[0];
    }

    public String readPriceOfOPenedProduct() {
        return driver.findElement(productPrice).getText().split(" ")[0];
    }

    public String readNameOfOPenedProduct() {
        return driver.findElement(productName).getText();
    }
}
