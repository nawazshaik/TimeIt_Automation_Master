package com.timeit.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.timeit.base.TestBase;
import com.timeit.util.ReusableFunctions;

public class Android_DashboardPage extends TestBase{
	
	private static Logger logger = Logger.getLogger(Android_DashboardPage.class.getName());
	
	// Dashboard Page locators
    private static final By dashboardTab = By.xpath("//XCUIElementTypeButton[@name=\"Dashboard\"]");
    private static final By settingTab = By.xpath("//XCUIElementTypeButton[@name=\"Settings\"]");
    private static final By settingsHeader = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Settings\"]");
    private static final By timeLogTab = By.xpath("//XCUIElementTypeButton[@name=\"Time Log\"]");
    private static final By weeklyRibbon = By.xpath("*//XCUIElementTypeCollectionView");
    private static final By approvalTab = By.xpath("//XCUIElementTypeButton[@name=\"Approval\"]");
    private static final By approvalHeader = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Approval\"]");
    
    
	public void naviagteToSettingTab() {
		logger.info("Navigating to Setting Screen");
		ReusableFunctions.waitForElementVisible(dashboardTab);
		ReusableFunctions.waitForElementClickable(settingTab);
		driver.findElement(settingTab).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(settingsHeader), "Failed to navigate Setting screen");
	}

	public void navigateToTimeLogTab() {
		logger.info("Navigating to TimeLog Screen");
		ReusableFunctions.waitForElementClickable(timeLogTab);
		driver.findElement(timeLogTab).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(weeklyRibbon), "Failed to navigate TimeLog screen");
	}

	public void navigateToApprovalTab() {
		logger.info("Navigating to TimeLog Screen");
		ReusableFunctions.waitForElementClickable(approvalTab);
		driver.findElement(approvalTab).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(approvalHeader), "Failed to navigate Approval screen");
	}
}
