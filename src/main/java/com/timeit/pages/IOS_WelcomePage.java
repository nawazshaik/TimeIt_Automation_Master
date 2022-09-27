package com.timeit.pages;

import com.timeit.base.TestBase;
import com.timeit.util.ReusableFunctions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

public class IOS_WelcomePage extends TestBase {

    private static Logger logger = Logger.getLogger(IOS_WelcomePage.class.getName());

    // Welcome Page locators
    private static final By welcomeToText = By.xpath("//XCUIElementTypeStaticText[@name=\"WELCOME TO\"]");
    private static final By timeItText = By.xpath("//XCUIElementTypeStaticText[@name=\"TimeIt\"]");
    private static final By loginButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Log in\"]");
    private static final By signupButton = By.xpath("//XCUIElementTypeButton[@name=\"Sign up\"]");
    private static final By loginScreenHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Log in \"]");
    private static final By signupScreenTitle = By.xpath("//XCUIElementTypeStaticText[@name=\"Sign up\"]");
	
    /**
     * This method is used to verify Welcome page UI 
     */
	public void verifyWelcomeScreenUi() {
		logger.info("Verifying Welcome Screen UI");
		ReusableFunctions.waitForElementVisible(welcomeToText);
		driver.findElement(welcomeToText).isDisplayed();
		driver.findElement(timeItText).isDisplayed();
		driver.findElement(loginButton).isDisplayed();
		driver.findElement(signupButton).isDisplayed();
		Assert.assertTrue(ReusableFunctions.waitForElementClickable(loginButton));
		Assert.assertTrue(ReusableFunctions.waitForElementClickable(signupButton));
	}
	
	/**
     * This method is used to navigate login screen 
     */
	public void navigateToLoginScreen() {
		logger.info("Navigating to Login Screen");
		ReusableFunctions.waitForElementClickable(loginButton);
		driver.findElement(loginButton).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(loginScreenHeader), "Failed to navigate to Login Page");
	}
      
	
	public void navigateToSignUpScreen() {
		logger.info("Navigating to Sign up Screen");
		ReusableFunctions.waitForElementClickable(signupButton);
		driver.findElement(signupButton).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(signupScreenTitle), "Failed to navigate to Sign up Page");
	}
}


