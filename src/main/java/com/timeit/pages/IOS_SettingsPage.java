package com.timeit.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.timeit.base.TestBase;
import com.timeit.util.ReusableFunctions;

public class IOS_SettingsPage extends TestBase {

	private static Logger logger = Logger.getLogger(IOS_SettingsPage.class.getName());

	private static final By settingsHeader = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Settings\"]");
	private static final By signOutButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Sign out\"]");
	private static final By signOutPopupConfirmText = By.xpath("//XCUIElementTypeStaticText[@name=\"Are you sure you want to sign out?\"]");
	private static final By signOutPopupButton = By.xpath("//XCUIElementTypeButton[@name=\"Sign out\"]");
	private static final By loginButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Log in\"]");
	private static final By settingTab = By.xpath("//XCUIElementTypeButton[@name=\"Settings\"]");

	
	public void signOutOfApp() {
		logger.info("Signing out of the App");
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(settingTab), "Setting Tab is not visible on the screen. First navigate to Dashboard screen");
		ReusableFunctions.waitForElementClickable(settingTab);
		driver.findElement(settingTab).click();
		ReusableFunctions.waitForElementVisible(settingsHeader);
		ReusableFunctions.SwipeDownToTop();
		driver.findElement(signOutButton).click();
		driver.findElement(signOutPopupConfirmText).isDisplayed();
		driver.findElement(signOutPopupButton).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(loginButton), "Failed to sign out of app");
		logger.info("Sign out Successfull");
    }
}
