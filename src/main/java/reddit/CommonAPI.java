package reddit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
    public static WebDriver driver;//WebDriver is interface

    public void sleepFor(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@AfterTest(alwaysRun = true)
    public void close() {
        sleepFor(3);
        driver.close();//closes the driver
        driver.quit();//quits instance of the driver
    }

    @BeforeTest(alwaysRun = true)
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://old.reddit.com");
        sleepFor(3);
    }

    public void clickOnElementByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void clickOnElementById(String locator){
        driver.findElement(By.id(locator)).click();
    }

    public void sendKeysByXpath(String locator, String keys){
        driver.findElement(By.xpath(locator)).sendKeys(keys);
    }

    public void sendKeysById(String locator, String keys){
        driver.findElement(By.id(locator)).sendKeys(keys);
    }

    public void sendKeysByClass(String locator, String keys) {
        driver.findElement(By.className(locator)).sendKeys(keys);
    }

    public void sendKeysByCSS(String locator, String keys) {
        driver.findElement(By.cssSelector(locator)).sendKeys(keys);
    }

    public void sendKeysByLinkText(String locator, String keys) {
        driver.findElement(By.linkText(locator)).sendKeys(keys);
    }

    public void sendKeysByPartialLinkText(String locator, String keys) {
        driver.findElement(By.partialLinkText(locator)).sendKeys(keys);
    }
    public void clickOnElementByClass(String locator) {
        driver.findElement(By.className(locator)).click();
    }

    public void clickOnElementByCSS(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void clickOnElementByLinkText(String locator) {
        driver.findElement(By.linkText(locator)).click();
    }

    public void clickonElementByPartialLinkText(String locator) {
        driver.findElement(By.partialLinkText(locator)).click();
    }

    public String getValueByXpath(String locator) {
        return driver.findElement(By.xpath(locator)).getText();
    }

    public boolean isElementDisplayed(String locator) {
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }

    public boolean isElementEnabled(String locator) {
        return driver.findElement(By.xpath(locator)).isEnabled();
    }

    public boolean isElementSelected(String locator) {
        return driver.findElement(By.xpath(locator)).isSelected();
    }

    public WebElement getElement(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        return element;
    }

    public static void takeScreenshot(String testCaseName) {
        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy-HH.mm.ss");
        Date date = new Date();
        String uniqueName = dateFormat.format(date);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/"
                    + testCaseName+uniqueName +".png"));
            System.out.println("SCREENSHOT CAPTURED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
