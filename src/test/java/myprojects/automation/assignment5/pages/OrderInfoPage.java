package myprojects.automation.assignment5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrderInfoPage {
    private EventFiringWebDriver driver;
    private WebDriverWait wait;
    private By orderProductInfos= By.className("order-line");
    private By confirmationText = By.cssSelector("#content-hook_order_confirmation .card-title");
    private By priceField = By.xpath("//*[@id=\"order-items\"]//table[2]//tr[3]/td[2]");
    private By productNameField = By.cssSelector("#order-items .order-line .details");


    public OrderInfoPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationText));
    }

    public void testSuccessNotificationText() {
        waitForPageLoad();
        Assert.assertEquals(driver.findElement(confirmationText).getText(), "\uE876Ваш заказ подтверждён".toUpperCase());
    }

    public void testOnSingleProductKind() {
        waitForPageLoad();
        Assert.assertEquals(driver.findElements(orderProductInfos).size(), 1);
    }

    public void testProductDetailsEquality(String productPrice, String productName) {
        waitForPageLoad();
        Assert.assertEquals(driver.findElement(priceField).getText().split(" ")[0].replace(',', '.'), productPrice);
        Assert.assertEquals(driver.findElement(productNameField).getText().split(" - Size :")[0].toUpperCase(), productName.toUpperCase());
    }
}
