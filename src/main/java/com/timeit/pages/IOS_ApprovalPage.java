package com.timeit.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.timeit.base.TestBase;
import com.timeit.util.ReusableFunctions;

public class IOS_ApprovalPage extends TestBase {
	
	private static Logger logger = Logger.getLogger(IOS_ApprovalPage.class.getName());

    // Approval Page locators
    private static final By approvalTab = By.xpath("//XCUIElementTypeButton[@name=\"Approval\"]");
    private static final By submittedSection = By.xpath("//XCUIElementTypeStaticText[@name=\"Submitted\"]"); 
    private static final By projectName = By.xpath("//XCUIElementTypeStaticText[@name=\"Health Care Project\"]");
    private static final By approveButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Approve\"]");
    private static final By rejectButton = By.xpath("//XCUIElementTypeButton[@name=\"Reject\"]");
    private static final By approveEntriesHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Approve entries\"]");
    private static final By rejectEntriesHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Reject entries\"]");
    private static final By feedbackDropdown = By.xpath("*//XCUIElementTypeTextField");
    private static final By wellDoneOption = By.xpath("//XCUIElementTypeStaticText[@name=\"Well done!\"]");
    private static final By incorrectShiftOption = By.xpath("//XCUIElementTypeStaticText[@name=\"Incorrect shift\"]");
    private static final By addDetailsInputText = By.xpath("*//XCUIElementTypeTextView");
    private static final By confirmButton = By.xpath("//XCUIElementTypeButton[@name=\"Confirm\"]");
    private static final By approvedSection = By.xpath("//XCUIElementTypeStaticText[@name=\"Approved\"]");
    private static final By rejectedSection = By.xpath("//XCUIElementTypeStaticText[@name=\"Rejected\"]");
    private static final By closeButton = By.xpath("//XCUIElementTypeButton[@name=\"x\"]");
    
    public void approveSubmittedTimesheet() {
    	String Name = "Maria Coy";
    	ReusableFunctions.waitForElementVisible(submittedSection);
    	driver.findElement(submittedSection).click();
    	driver.findElementByAccessibilityId(Name).click();
    	ReusableFunctions.waitForElementVisible(projectName);
    	driver.findElement(projectName).click();
    	ReusableFunctions.waitForElementVisible(approveButton);
    	driver.findElement(approveButton).click();
    	ReusableFunctions.waitForElementVisible(approveEntriesHeader); 
    	driver.findElement(feedbackDropdown).click();
    	ReusableFunctions.waitForElementVisible(closeButton);
    	driver.findElement(wellDoneOption).click();
    	ReusableFunctions.waitForElementClickable(addDetailsInputText);
    	driver.findElement(addDetailsInputText).sendKeys("Continue the great work");
    	driver.findElement(confirmButton).click();	
    	ReusableFunctions.waitForElementVisible(approvedSection);
    	ReusableFunctions.waitForElementClickable(approvedSection);
    	driver.findElement(approvedSection).click();
    	Boolean flag = driver.findElementByAccessibilityId(Name).isDisplayed();
    	Assert.assertTrue(flag, "Failed to approve timesheet");
    }
    
    
    public void rejectSubmittedTimesheet() {
    	String Name = "Wayne Shubert";
    	ReusableFunctions.waitForElementVisible(submittedSection);
    	driver.findElement(submittedSection).click();
    	driver.findElementByAccessibilityId(Name).click();
    	ReusableFunctions.waitForElementVisible(projectName);
    	driver.findElement(projectName).click();
    	ReusableFunctions.waitForElementVisible(rejectButton);
    	driver.findElement(rejectButton).click();
    	ReusableFunctions.waitForElementVisible(rejectEntriesHeader); 
    	driver.findElement(feedbackDropdown).click();
    	ReusableFunctions.waitForElementVisible(closeButton);
    	driver.findElement(incorrectShiftOption).click();
    	ReusableFunctions.waitForElementClickable(addDetailsInputText);
    	driver.findElement(addDetailsInputText).sendKeys("Re-submit the timesheet with correct shift");
    	driver.findElement(confirmButton).click();
    	ReusableFunctions.waitForElementVisible(rejectedSection);
    	ReusableFunctions.waitForElementClickable(rejectedSection);
    	driver.findElement(rejectedSection).click();
    	Boolean flag = driver.findElementByAccessibilityId(Name).isDisplayed();
    	Assert.assertTrue(flag, "Failed to reject timesheet");
    }
    
}
