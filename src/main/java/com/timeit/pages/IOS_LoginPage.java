package com.timeit.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.timeit.base.TestBase;
import com.timeit.testdata.DataReader;
import com.timeit.util.ReusableFunctions;

public class IOS_LoginPage extends TestBase {
	
	private static Logger logger = Logger.getLogger(IOS_LoginPage.class.getName());

    // Login Page locators
    private static final By loginHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Log in \"]");
    private static final By loginInfoText = By.xpath("//XCUIElementTypeStaticText[@name=\"Please enter your account here\"]");
    private static final By emailInput = By.xpath("*//XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    private static final By emailLabel = By.xpath("//XCUIElementTypeStaticText[@name=\"Email address\"]");
    private static final By passwordLabel = By.xpath("//XCUIElementTypeStaticText[@name=\"Password\"]");
    private static final By passwordInput = By.xpath("*//XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField");
    private static final By forgotPasswordLink = By.xpath("//XCUIElementTypeStaticText[@name=\"Forgot Password?\"]");
    private static final By loginButton = By.xpath("//XCUIElementTypeButton[@name=\"Log in\"]");
    private static final By backLink = By.xpath("//XCUIElementTypeButton[@name=\"Back\"]");
    private static final By dashboardTab = By.xpath("//XCUIElementTypeButton[@name=\"Dashboard\"]");
    private static final By errorPopup = By.xpath("//XCUIElementTypeButton[@name=\"Cancel\"]");
    private static final By chooseRoleHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Choose your role\"]");
    private static final By myTimeSheetRole = By.xpath("//XCUIElementTypeImage[@name=\"Group 82\"]");
    private static final By approverRole = By.xpath("//XCUIElementTypeImage[@name=\"Group 83\"]");
    private static final By timeKeeperRole = By.xpath("//XCUIElementTypeImage[@name=\"Group 83\"]");
    private static final By adminRole = By.xpath("//XCUIElementTypeImage[@name=\"Group 83\"]");
    private static final By approvalTab = By.xpath("//XCUIElementTypeButton[@name=\"Approval\"]");
    private static final By alreadyInvitedText = By.xpath("//XCUIElementTypeStaticText[@name=\"Already invited?\"]");
    private static final By startHereText = By.xpath("//XCUIElementTypeButton[@name=\"Start here\"]");

    
    
    public void verifyLoginScreenUi() {
		logger.info("Verifying Login Screen UI");
		ReusableFunctions.waitForElementVisible(loginHeader);
		driver.findElement(loginHeader).isDisplayed();
		driver.findElement(backLink).isDisplayed();
		driver.findElement(loginInfoText).isDisplayed();
		driver.findElement(emailLabel).isDisplayed();
		driver.findElement(passwordLabel).isDisplayed();
		driver.findElement(forgotPasswordLink).isDisplayed();
		driver.findElement(alreadyInvitedText).isDisplayed();
		driver.findElement(startHereText).isDisplayed();
    }
    
    
    public void loginToApp(String Email, String Password) {
		logger.info("Entering Login Credentials");
		driver.findElement(emailInput).sendKeys(Email);
		driver.findElement(passwordInput).sendKeys(Password);
		ReusableFunctions.waitForElementClickable(loginButton);
		driver.findElement(loginButton).click();
		Assert.assertFalse(ReusableFunctions.waitForElementVisible(errorPopup), "Failed to login. Check login credentials.");
		logger.info("Login Successful");
    }
    
    
    public void chooseMemberRole() {
    	ReusableFunctions.waitForElementVisible(chooseRoleHeader);
    	logger.info("Selecting role as Member with My Timesheet");
		driver.findElement(myTimeSheetRole).click();
		ReusableFunctions.waitForElementVisible(dashboardTab);
    }
    
    public void chooseApproverRole() {
    	ReusableFunctions.waitForElementVisible(chooseRoleHeader);
    	logger.info("Selecting role as Approver");
		driver.findElement(approverRole).click();
		ReusableFunctions.waitForElementVisible(approvalTab);
    }
    
    public void chooseTimekeeperRole() {
    	ReusableFunctions.waitForElementVisible(chooseRoleHeader);
    	logger.info("Selecting role as Timekeeper");
		driver.findElement(timeKeeperRole).click();
		ReusableFunctions.waitForElementVisible(dashboardTab);
    }
    
    public void chooseAdminRole() {
    	ReusableFunctions.waitForElementVisible(chooseRoleHeader);
    	logger.info("Selecting role as Admin");
		driver.findElement(adminRole).click();
		ReusableFunctions.waitForElementVisible(dashboardTab);
    }

    
}