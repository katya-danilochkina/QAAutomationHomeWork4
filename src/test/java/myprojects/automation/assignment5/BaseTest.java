package myprojects.automation.assignment5;

import myprojects.automation.assignment5.utils.DriverFactory;
import myprojects.automation.assignment5.utils.logging.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseTest {
    protected EventFiringWebDriver driver;
    protected GeneralActions actions;
    protected boolean isMobileTesting;

//    private String getResource(String resourceName) {
//        try {
//            return Paths.get(BaseTest.class.getResource(resourceName).toURI()).toFile().getPath();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return resourceName;
//    }

//    private WebDriver getDriver(String browser) {
//        if (browser.equals("firefox")) {
//            System.setProperty("webdriver.gecko.driver", getResource("geckodriver"));
//            return new FirefoxDriver();
//        } else if (browser.equals("safari")) {
//            return new SafariDriver();
//        } else if (browser.equals("chrome_mobile")) {
//            System.setProperty("webdriver.gecko.driver", getResource("chromedriver"));
//            Map<String, String> mobileEmulator = new HashMap<>();
//            mobileEmulator.put("deviceName", "iPhone 6");
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulator);
//            return new ChromeDriver(chromeOptions);
//        } else {
//            System.setProperty("webdriver.gecko.driver", getResource("chromedriver"));
//            return new ChromeDriver();
//        }
//    }


    /**
     * Prepares {@link WebDriver} instance with timeout and browser window configurations.
     *
     * Driver type is based on passed parameters to the automation project,
     * creates {@link ChromeDriver} instance by default.
     *
     */
    @BeforeClass
    @Parameters({"selenium.browser", "selenium.grid"})
    public void setUp(@Optional("chrome") String browser, @Optional("") String gridUrl) {
        // TODO create WebDriver instance according to passed parameters
        // driver = new EventFiringWebDriver(....);
        // driver.register(new EventHandler());
        // ...
//        driver = new EventFiringWebDriver(DriverFactory.initDriver(browser, gridUrl));
        driver = new EventFiringWebDriver(DriverFactory.initDriver(browser));
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        // unable to maximize window in mobile mode
        if (!isMobileTesting(browser))
            driver.manage().window().maximize();

        isMobileTesting = isMobileTesting(browser);

        actions = new GeneralActions(driver);
    }

    /**
     * Closes driver instance after test class execution.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     *
     * @return Whether required browser displays content in mobile mode.
     */
    private boolean isMobileTesting(String browser) {
        switch (browser) {
            case "android":
                return true;
            case "firefox":
            case "ie":
            case "internet explorer":
            case "chrome":
            case "chrome_mobile":
            case "phantomjs":
            default:
                return false;
        }
    }
}
