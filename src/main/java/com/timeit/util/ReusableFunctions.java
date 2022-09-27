package com.timeit.util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.timeit.base.TestBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;

public class ReusableFunctions extends TestBase{

    private static Logger logger = Logger.getLogger(ReusableFunctions.class.getName());
    private static int timeout;

    /**
     * This is reusable method to get the timeout property value from config file
     * @return - it returns global timeout value
     */
    private static int timeoutValue(){
        try{
            timeout = Integer.parseInt(prop.getProperty("Timeout"));
        }catch (Exception Ex) {
            logger.error("Exception Occurred While Getting Timeout Property:" + Ex.getMessage());
        }
        return timeout;
    }

    /**
     * This method checks for the presence of element on mobile app
     * @param locator - parameter for mobile app element locator
     * @return - it returns the boolean value for visibility of element
     */
    public static boolean waitForElementPresent(By locator){
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue());
        boolean flag = true;
        try{
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch(Exception Ex) {
            flag = false;
            logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
        }
        return flag;
    }

    /**
     * This method checks for the visibility of element on mobile app using element instead of locator
     * @param element - parameter for mobile app element
     * @return - it returns the boolean value for visibility of element
     */
    public static boolean waitForElementVisible(MobileElement element){
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue());
        boolean flag = true;
        try{
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.visibilityOf(element));
        }catch(Exception Ex) {
            flag = false;
            logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
        }
        return flag;
    }

    /**
     * This method checks for the visibility of element on mobile app using locator
     * @param locator - parameter for mobile app element
     * @return - it returns the boolean value for visibility of element
     */
    public static boolean waitForElementVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue());
        boolean flag = true;
        try{
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(Exception Ex) {
            flag = false;
            logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
        }
        return flag;
    }

    /**
     * This method checks for the invisibility of element on mobile app
     * @param locator - parameter for element locator
     * @return - it returns the boolean value for invisibility of element
     */
    public static boolean waitForInvisibilityOfElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue());
        boolean flag = true;
        try{
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }catch(Exception Ex) {
            flag = false;
            logger.error("Exception Occurred While Checking Invisibility of The Element: " + Ex.getMessage());
        }
        return flag;
    }

    /**
     * This method checks whether element is clickable or not
     * @param locator - parameter for element locator
     * @return - it returns the boolean value for element clickable condition
     */
    public static boolean waitForElementClickable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue());
        boolean flag = true;
        try{
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.elementToBeClickable(locator));
        }catch(Exception Ex) {
            flag = false;
            logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
        }
        return flag;
    }

    /**
     * This method checks for the all elements visibility on mobile app provided in the list
     * @param arrayList - List of all elements needs to check on application
     * @return - boolean value for each element located on app
     */
    public static boolean verifyElementsLocated(ArrayList<By> arrayList){
        boolean flag = true;
        try{
            for (By locator : arrayList){
                if (waitForElementPresent(locator)){
                    logger.info(locator.toString()+": element with class property displayed.");
                }else{
                    logger.error(locator.toString()+": element with class property isn't displayed.");
                    flag=false;
                }
            }
        }catch (Exception Ex){
            logger.error("Exception Occurred While Locating The Elements: " + Ex.getMessage());
            flag=false;
        }
        return flag;
    }

    /**
     * This is a reusable method to enter text in input elements
     * @param locator - parameter for element locator
     * @param value - text values to be entered in input elements
     */
    public static void enterText(By locator, String value){
        try{
            if (waitForElementPresent(locator)) {
                MobileElement element = (MobileElement)driver.findElement(locator);
                element.sendKeys(value);
                logger.info("Entering text to element: " + locator.toString());
            }
        }catch(Exception Ex) {
            logger.error("Exception Occurred While Entering The Text: " + Ex.getMessage());
        }

    }

    /**
     * This is a reusable method to send enter key
     * @param locator - parameter for element locator
     */
    public static void pressEnterKey(By locator){
        try{
            if (waitForElementVisible(locator)) {
                Thread.sleep(500);
                MobileElement element = (MobileElement)driver.findElement(locator);
                element.sendKeys(Keys.ENTER);
                Thread.sleep(500);
            }
        }catch (Exception Ex) {
            logger.error("Exception Occurred While Sending Enter Key" + Ex.getMessage());
        }
    }

    /**
     * This is a reusable method to click on mobile elements
     * @param locator - parameter for element locator
     */
    public static void mouseClick(By locator){
        try{
            if (waitForElementClickable(locator)){
                MobileElement element = (MobileElement)driver.findElement(locator);
                element.click();
                logger.info("Clicking on element: " + locator.toString());
            }
        }catch(Exception Ex) {
            logger.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
        }

    }

    /**
     * This is a reusable method to move and click on mobile elements
     * @param locator - parameter for element locator
     */
    public static void moveToElementAndClick(By locator, long timeout){
        try{
            if (waitForElementClickable(locator)){
                MobileElement element = (MobileElement)driver.findElement(locator);
                Actions action = new Actions(driver);
                action.moveToElement(element).wait(timeout);
                action.click();
                logger.info("Clicking on element: " + locator.toString());
            }
        }catch(Exception Ex) {
            logger.error("Exception Occurred While Clicking The Element: " + Ex.getMessage());
        }

    }

    /**
     * This is a reusable method to get the attribute value from mobile elements
     * @param locator - parameter for element locator
     * @return - it returns the attribute value
     */
    public static String getTextByAttributeValue(By locator){
        String text = null;
        try{
            if (waitForElementPresent(locator)) {
                MobileElement element = (MobileElement)driver.findElement(locator);
                text = element.getAttribute("value");
                logger.info("Getting Text From locator: " + text);
            }
        }catch(Exception Ex) {
            logger.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
        }
        return text;
    }

    /**
     * This is a reusable method to get the inner text value from mobile elements
     * @param locator - parameter for element locator
     * @return - it returns the text value
     */
    public static String getTextByInnerText(By locator){
        String text = null;
        try{
            if (waitForElementPresent(locator)) {
                MobileElement element = (MobileElement)driver.findElement(locator);
                text = element.getText();
                logger.info("Getting Text From locator: " + text);
            }
        }catch(Exception Ex) {
            logger.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
        }
        return text;

    }

    /**
     * This is a reusable methos to capture the screenshot for reporting
     * @param screenshotName - parameter for name of the screenshot usually appended with timestamp
     * @return - it returns the destination file path for the captured screenshot
     */
    public static String takeScreenShot(String screenshotName) {
        String destination = null;
        try{
            String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            destination = System.getProperty("user.dir") + "/screenshots/"+screenshotName+dateName+".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);

            destination = finalDestination.getAbsolutePath();
            logger.info("Saving screenshot at " + destination);

        }catch (Exception Ex){
            logger.error("Exception Occurred While Getting The Text: " + Ex.getMessage());
        }
        return destination;
    }

    /**
     * This method verifies the actual and expected text values
     * @param actualText - parameter for actual text value
     * @param expectedText - parameter for expected text value
     * @return - returns the boolean value for text match
     */
    public static boolean verifyTextMatch(String actualText, String expectedText){
        boolean flag = false;
        try {
            logger.info("Actual Text From Application UI --> :: " + actualText);
            logger.info("Expected Text From Application UI --> :: " + expectedText);

            if(actualText.equals(expectedText)){
                logger.info("### VERIFICATION TEXT MATCHED !!!");
                flag = true;
            }else{
                logger.error("### VERIFICATION TEXT DOES NOT MATCHED !!!");
            }

        }catch (Exception Ex){
            logger.error("Exception Occurred While Verifying The Text Match: " + Ex.getMessage());
        }
        return flag;
    }

    /**
     * Creates a File if the file does not exist, or returns reference to the File if it already exists.
     * @param target - directory path
     * @param fileName - name of the file
     * @return - it returns the file path
     */
    public static String createOrRetrieveFiles(String target, String fileName) {
        File directory = new File(target);
        String filePath= null;
        if (!directory.exists()){
            boolean dirStatus = directory.mkdirs();
            if (dirStatus){
                logger.info("Target directory /" + target + "/ will be created.");
            }else{
                logger.error("Target directory \"" + target + "\" not created.");
            }
        }
        filePath = target+ File.separatorChar + fileName;
        return filePath;
    }

    /**
     * This function is used for vertical scroll
     * @param scrollView - class name for scrollView
     * @param className - class name for text view
     * @param text - text of the element
     */
	public static void verticalSwipe(String scrollView, String className) {
		try {

			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)" + ".className(\"" + scrollView
							+ "\")).scrollIntoView(new UiSelector()" + ".className(\"" + className + "\"))"));
			logger.info("Vertically scrolling into the view.");
		} catch (Exception Ex) {
			logger.error("Exception occurred while vertically scrolling into the view: " + Ex.getMessage());
		}

	}
	
	/**
	 * This function is used for swipe from down to top
	 */
	public static void SwipeDownToTop() {

		try {
			logger.info("Veritically scrolling into the view.");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
			logger.info("Swipe was Successfully from donw to top");
		} catch (Exception Ex) {
			logger.error("Exception occurred while vertically scrolling into the view: " + Ex.getMessage());
		}

	}
	
	/**
     * This function is used for swipe from top to down
     */
	public static void SwipeTopToDown() {
		try {
			logger.info("Veritically scrolling into the view.");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "up");
			js.executeScript("mobile: scroll", scrollObject);
			logger.info("Swipe was Successfully from top to down");
		} catch (Exception Ex) {
			logger.error("Exception occurred while vertically scrolling into the view: " + Ex.getMessage());
		}

	}

    /**
     * This function is used for horizontal swipe
     * @param scrollView - class name for scrollView
     * @param className - class name for text view
     * @param text - text of the element
     */
    public static void horizontalSwipe(String scrollView, String className, String text){
        try{
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)" +
                            ".className(\"" + scrollView + "\")).setAsHorizontalList().scrollIntoView(new UiSelector()" +
                            ".className(\"" + className + "\").text(\"" + text + "\"))"));

            logger.info("Horizontally scrolling into the view.");

        }catch(Exception Ex){
            logger.error("Exception occurred while horizontally scrolling into the view: " + Ex.getMessage());
        }

    }
    
    
	public static String iOS_getCodeFromBrowser(String OrgName) {
		logger.info("Getting Code from Browser");
		driver.activateApp("com.apple.mobilesafari");
		driver.get("https://timesheetapp.crm3.dynamics.com/main.aspx");
		logger.info("Logging into Sharepoint");
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeTextField[@name=\"someone@example.com\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"someone@example.com\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"someone@example.com\"]"))
				.sendKeys("nawaz.shaik@mir-services.com");
		driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Next\"])[1]")).click();
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeSecureTextField[@name=\"Enter the password for nawaz.shaik@mir-services.com\"]"));
		driver.findElement(By.xpath(
				"//XCUIElementTypeSecureTextField[@name=\"Enter the password for nawaz.shaik@mir-services.com\"]")).click();
		driver.findElement(By.xpath(
				"//XCUIElementTypeSecureTextField[@name=\"Enter the password for nawaz.shaik@mir-services.com\"]"))
				.sendKeys("Arfa@123");
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Sign in\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"T&A\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"T&A\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeMenuItem[@name=\"Search\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeMenuItem[@name=\"Search\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeTextField[@name=\"Search box\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Search box\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Search box\"]")).sendKeys(OrgName + "\n");
		ReusableFunctions.waitForElementClickable(By.xpath("(//XCUIElementTypeOther[@name=\"Contacts\"])[2]/XCUIElementTypeOther"));
		driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Contacts\"])[2]/XCUIElementTypeOther")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"First Name\"]"));
		String code = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Reset Password Code\"]")).getText();
		logger.info("Reset Code retrival successfull");
		return (code);
	}
	
	public static void iOS_clearSafariHistory() {
		logger.info("Clearing browser history");
		driver.activateApp("com.apple.Preferences");
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeCell[@name=\"Safari\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeCell[@name=\"Safari\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Safari\"]"));
		ReusableFunctions.SwipeDownToTop();
		driver.findElement(By.xpath("//XCUIElementTypeCell[@name=\"Clear History and Website Data\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Clear History and Data\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Safari\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Settings\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Settings\"]"));
		logger.info("Clearing history successfull");
	}
	
	public static void iOS_closeCurrentBrowserTab() {
		logger.info("Closing current browser tab");
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeButton[@name=\"TabOverviewButton\"]"));
		MobileElement ele = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"TabOverviewButton\"]")); 
		TouchAction action = new TouchAction(driver);
		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(ele)).withDuration(Duration.ofMillis(500))).release().perform();
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Close This Tab\"]")).click();
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeButton[@name=\"TabOverviewButton\"]"));
		logger.info("Current browser tab closed successfull");
	}

	public static void navigateBackToTimeIt() {
		driver.activateApp("com.mir.timeitapp");
	}
}
