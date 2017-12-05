package myprojects.automation.assignment5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ShoppingCartPage {
    private EventFiringWebDriver driver;
    private By quantityField= By.name("product-quantity-spin");
    private By createOrderButton = By.cssSelector(".cart-detailed-actions .text-xs-center a");


    public ShoppingCartPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField));
    }

    public void checkQuantity() {
        waitForPageLoad();
        Assert.assertTrue(driver.findElement(quantityField).getAttribute("value").toString().equals("1"), "Quantity on cart is not equal to ordered");
    }

    public void createOrder() {
        waitForPageLoad();
        driver.findElement(createOrderButton).click();
    }
}
