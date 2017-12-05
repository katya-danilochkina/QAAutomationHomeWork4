package myprojects.automation.assignment5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsListPage {
    private EventFiringWebDriver driver;
    private By productElementName = By.cssSelector(".product-description .product-title");
    private By searchField = By.className("ui-autocomplete-input");
    private By submitSearchButton = By.cssSelector("#search_widget button[type=submit]");

    public ProductsListPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private void waitForProductLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productElementName));
    }

    public void randomProductClick() {
        waitForProductLoad();
        driver.findElement(productElementName).click();
    }

    public void findCreatedProduct(String productName) {
        waitForProductLoad();
        driver.findElement(searchField).sendKeys(productName);
        driver.findElement(submitSearchButton).click();
    }
}
