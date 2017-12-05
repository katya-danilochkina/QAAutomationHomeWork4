package myprojects.automation.assignment5;


import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.pages.*;
import myprojects.automation.assignment5.utils.logging.CustomReporter;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private EventFiringWebDriver driver;
    private ProductData createdProduct;

    public GeneralActions(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void openRandomProduct() {
        RootPage rootPage = new RootPage(driver);
        rootPage.open();
        rootPage.clickProductsListLink();

        ProductsListPage productsListPage = new ProductsListPage(driver);
        productsListPage.randomProductClick();
    }

    /**
     * Extracts product information from opened product details page.
     *
     * @return
     */
    private ProductData getOpenedProductInfo() {
        CustomReporter.logAction("Get information about currently opened product");
        ProductPage productPage = new ProductPage(driver);
        productPage.switchToDetailedOrderInfo();

        int quantity = Integer.parseInt(productPage.readQuantityOfOpenedProduct());
        String name = productPage.readNameOfOPenedProduct();
        Float price = Float.parseFloat(productPage.readPriceOfOPenedProduct().replace(',', '.'));

        return new ProductData(name, quantity, price);
    }

    public void saveProductParameters() {
        this.createdProduct = getOpenedProductInfo();
    }

    public void fillOrderInfo() {
        OrderCreationPage orderCreationPage = new OrderCreationPage(driver);
        orderCreationPage.clickPersonalInfoLink();
        orderCreationPage.fillPersonalInfo();
        orderCreationPage.fillAddressInfo();
        orderCreationPage.fillDeliveryInfo();
        orderCreationPage.fillPaymentInfo();
    }

    public void checkCreatedOrderInfo() {
        OrderInfoPage orderInfoPage = new OrderInfoPage(driver);
        orderInfoPage.testSuccessNotificationText();
        orderInfoPage.testOnSingleProductKind();
        orderInfoPage.testProductDetailsEquality(Float.toString(createdProduct.getPrice()), createdProduct.getName());
    }

    public void checkProductCountChange() {
        RootPage rootPage = new RootPage(driver);
        rootPage.open();
        rootPage.clickProductsListLink();

        ProductsListPage productsListPage = new ProductsListPage(driver);
        productsListPage.findCreatedProduct(createdProduct.getName());

        ProductsFindingPage productsFindingPage = new ProductsFindingPage(driver);
        productsFindingPage.goToElementShow(createdProduct.getName());

        ProductPage productPage = new ProductPage(driver);
        productPage.switchToDetailedOrderInfo();

        Assert.assertEquals(Integer.parseInt(productPage.readQuantityOfOpenedProduct()), createdProduct.getQty() - 1);
    }

    public void addProductToCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.clickCartPageLink();
    }

    public void proceedOrderCreation() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.checkQuantity();
        shoppingCartPage.createOrder();
    }


    public void checkSiteVersion(String version) {
        RootPage rootPage = new RootPage(driver);
        rootPage.open();

        if (version.equals("mobile")) {
            rootPage.checkSiteMobileVersion();
        } else {
            rootPage.checkDesktopVersion();
        }
    }
}
