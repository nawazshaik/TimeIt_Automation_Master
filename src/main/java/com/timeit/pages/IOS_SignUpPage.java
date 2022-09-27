package com.timeit.pages;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.graphbuilder.math.func.RandFunction;
import com.timeit.base.TestBase;
import com.timeit.testdata.DataReader;
import com.timeit.util.ReusableFunctions;

public class IOS_SignUpPage extends TestBase {
	
	private static Logger logger = Logger.getLogger(IOS_SignUpPage.class.getName());

    // Login Page locators
	private static final By welcomeToTimeitText = By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to TimeIt\"]");
	private static final By firstNameInput = By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField");
	private static final By lastNameInput = By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeTextField");
	private static final By OrgNameInput = By.xpath("//XCUIElementTypeOther[3]/XCUIElementTypeTextField");
	private static final By EmailInput = By.xpath("//XCUIElementTypeOther[4]/XCUIElementTypeTextField");
	private static final By passwordInput = By.xpath("//XCUIElementTypeOther[5]/XCUIElementTypeSecureTextField");
	private static final By confirmPasswordInput = By.xpath("//XCUIElementTypeOther[6]/XCUIElementTypeSecureTextField");
	private static final By createMyAccountButton = By.xpath("//XCUIElementTypeButton[@name=\"Create My Account\"]");
	private static final By verifyEmailTitle = By.xpath("//XCUIElementTypeStaticText[@name=\"Verify your email\"]");
	private static final By verificationCodeInput = By.xpath("//XCUIElementTypeOther/XCUIElementTypeTextField[1]");
	private static final By verifyButton = By.xpath("//XCUIElementTypeButton[@name=\"Verify\"]");
	private static final By setUpWorkplaceText = By.xpath("//XCUIElementTypeStaticText[@name=\"Set up your workspace\"]");
	private static final By getStartedButton = By.xpath("//XCUIElementTypeButton[@name=\"Get Started\"]");
	private static final By editOrgHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Edit Organization\"]");
	private static final By addressInput = By.xpath("//XCUIElementTypeOther[5]/XCUIElementTypeTextView");
	private static final By phoneNumberInput = By.xpath("//XCUIElementTypeOther[6]/XCUIElementTypeTextField");
	private static final By primaryIndustryInput = By.xpath("//XCUIElementTypeOther[7]//XCUIElementTypeTextField");
	private static final By DoneButton = By.xpath("//XCUIElementTypeButton[@name=\"Done\"]");
	private static final By orgSizeInput = By.xpath("//XCUIElementTypeOther[8]/XCUIElementTypeTextField");
	private static final By smallTeamOption = By.xpath("//XCUIElementTypeStaticText[@name=\"Small team\"]");
	private static final By weekltHoursInput = By.xpath("//XCUIElementTypeOther[11]/XCUIElementTypeTextField");
	private static final By editOrgSavebutton = By.xpath("//XCUIElementTypeButton[@name=\"Save\"]");
    private static final By invitedMembersHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Invited Members\"]");
    private static final By inviteEmailInput = By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    private static final By sendButton = By.xpath("//XCUIElementTypeButton[@name=\"Send\"]");
    private static final By verifyEmailHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Verify your email\"]");
    private static final By startHereText = By.xpath("//XCUIElementTypeButton[@name=\"Start here\"]");
    private static final By dashboardTab = By.xpath("//XCUIElementTypeButton[@name=\"Dashboard\"]");
    
	static String NewInvite = "testinvite"+getRandomAlphaNumericString()+"@yopmail.com";
	
	public void createAccount() {
		logger.info("Creating my account in Sign up Screen");
		String OrgName = "Automation Tech_"+getRandomAlphaNumericString();
		String SignUpEmail = "autoemail"+getRandomAlphaNumericString()+"@yopmail.com";
		ReusableFunctions.waitForElementVisible(welcomeToTimeitText);
		driver.findElement(firstNameInput).sendKeys("Test");
		driver.findElement(lastNameInput).sendKeys("Tester");
		driver.findElement(OrgNameInput).sendKeys(OrgName);
		driver.findElement(EmailInput).sendKeys(SignUpEmail);
		driver.findElementByAccessibilityId("Password *").click();
		driver.findElement(passwordInput).sendKeys("Tester@123");
		driver.findElement(confirmPasswordInput).sendKeys("Tester@123");
		driver.findElementByAccessibilityId("Confirm password *").click();
		driver.findElement(createMyAccountButton).click();
		ReusableFunctions.waitForElementVisible(verifyEmailTitle);
		ReusableFunctions.iOS_clearSafariHistory();
		String code = ReusableFunctions.iOS_getCodeFromBrowser(SignUpEmail);
		ReusableFunctions.iOS_closeCurrentBrowserTab();
		ReusableFunctions.navigateBackToTimeIt();
		driver.findElement(verificationCodeInput).sendKeys(code);
		ReusableFunctions.waitForElementClickable(verifyButton);
		driver.findElement(verifyButton).click();
		ReusableFunctions.waitForElementClickable(getStartedButton);
		driver.findElement(getStartedButton).click();
		ReusableFunctions.waitForElementVisible(editOrgHeader);
		editOrganization();
	}
	
	public void navigateToDashboard() {
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Your TimeIt account is ready!\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Get Started\"]")).click();
		ReusableFunctions.waitForElementVisible(dashboardTab);
	}


	public void addNameToInvitedMember() {
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ NewInvite +"']")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Edit Account\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField")).sendKeys("Natasha Sate");
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeButton[@name=\"Save\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Save\"]")).click();
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeButton[@name=\"Manage Members\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Manage Members\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Manage Members\"]"));
	}

	public void selectApprover() {
		logger.info("Selecting Approver");
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"+ Add approver\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"+ Add approver\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Add Members\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"testinvite1@yopmail.com\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Add\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"+ Add approver\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Finish\"]")).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Your TimeIt account is ready!\"]")), "Failed to Select Approver");
		logger.info("Approver Selection Successfull");
	}

	
	public void addApprover() {
		logger.info("Adding Approver to the orgnization");
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Manage Members\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"testinvite1@yopmail.com\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Role *\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeTextField[2]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Role\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Approver\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Done\"]")).click();
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeButton[@name=\"Save\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Save\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Manage Members\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Manage Members\"]"));
	}
	
	
	public void addTimekeeper() {
		logger.info("Adding Timekeeper to the orgnization");
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Manage Members\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"testinvite2@yopmail.com\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Role *\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeTextField[2]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Role\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Timekeeper\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Done\"]")).click();
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeButton[@name=\"Save\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Save\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"Edit\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Manage Members\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Manage Members\"]"));
	}
	
	
	
	public void createTask() {
		logger.info("Creating Task");
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"+ Add a new task\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"+ Add a new task\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Add Task\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField")).sendKeys("Automation Task");
		driver.findElement(By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeTextField")).click();
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeStaticText[@name=\"Billable\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Billable\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Add Task\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Create\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"+ Add a new task\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Next\"]")).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"+ Add approver\"]")), "Failed to create Task");
		logger.info("Task creation successfull");
	}

	public void createProject() {
		logger.info("Creating Project");
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Next\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"New Project\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")).sendKeys("Automation Project");
		driver.findElement(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField[3]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Billable\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Billable\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"New Project\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField[4]")).click();
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeStaticText[@name=\"In Progress\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"In Progress\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"New Project\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Next\"]")).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"+ Add a new task\"]")), "Failed to create project");
		logger.info("Project creation successfull");
	}

	public void inviteMembers() {
		logger.info("Inviting Members");
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Invite Members\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeTextField")).sendKeys("testinvite1@yopmail.com"+"\n");
		driver.findElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeTextField")).sendKeys("testinvite2@yopmail.com"+"\n");
		driver.findElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeTextField")).sendKeys("testinvite3@yopmail.com"+"\n");
		DataReader.setCellData(NewInvite);
		driver.findElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeTextField")).sendKeys(NewInvite+"\n");
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeButton[@name=\"Invite\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Invite\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Invites sent!\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Done\"]")).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Manage Members\"]")), "Select to Invite Members");
		logger.info("Inviting Members Successfull");
	}

	public void editOrganization() {
		String Address = "7880 Keele Street Concord";
		logger.info("Editing Organization Details");
		ReusableFunctions.waitForElementVisible(editOrgHeader);
		selectAddress(Address);
		driver.findElement(phoneNumberInput).sendKeys("14165783434");
		driver.findElement(primaryIndustryInput).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"x\"]"));
		driver.findElementByAccessibilityId("Doctor's Offices and Clinics").click();
		ReusableFunctions.waitForElementVisible(editOrgHeader);
		driver.findElement(orgSizeInput).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"x\"]"));
		driver.findElement(smallTeamOption).click();
		ReusableFunctions.waitForElementVisible(editOrgHeader);
		ReusableFunctions.SwipeDownToTop();
		driver.findElement(By.xpath("//XCUIElementTypeOther[9]/XCUIElementTypeTextField")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeButton[@name=\"x\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Bi-weekly\"]")).click();
		ReusableFunctions.waitForElementVisible(editOrgHeader);
		ReusableFunctions.waitForElementClickable(weekltHoursInput);
		driver.findElement(weekltHoursInput).sendKeys("40");
		driver.findElement(By.xpath("//XCUIElementTypeOther[15]/XCUIElementTypeTextField")).sendKeys("14165783434");
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Next\"]")).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Invite Members\"]")), "Failed to Edit Organization");
		logger.info("Editing Organization Successfull");
	}
	
	public void selectAddress(String Address) {
		logger.info("Selecting Address");
		driver.findElement(addressInput).sendKeys(Address);
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]"));
		driver.findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]")).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(editOrgHeader),"Failed to Select Address");
		logger.info("Address selection successfull");
	}
	
	public static String getRandomAlphaNumericString() {
		String random = RandomStringUtils.randomAlphanumeric(9).toLowerCase();
		return random;
	}
	
	public void invitedMemberSignUp(String invitedEmail, String Password) {
    	logger.info("Invited user Sign up flow started");
    	ReusableFunctions.waitForElementVisible(startHereText);
    	driver.findElement(startHereText).click();
    	ReusableFunctions.waitForElementVisible(invitedMembersHeader);
    	ReusableFunctions.waitForElementClickable(inviteEmailInput);
    	driver.findElement(inviteEmailInput).sendKeys(invitedEmail+"\n");
    	driver.findElement(sendButton).click();
    	ReusableFunctions.waitForElementVisible(verifyEmailHeader);
    	ReusableFunctions.iOS_clearSafariHistory();
    	String code = ReusableFunctions.iOS_getCodeFromBrowser(invitedEmail);
    	ReusableFunctions.iOS_closeCurrentBrowserTab();
    	ReusableFunctions.navigateBackToTimeIt();
    	driver.findElement(verificationCodeInput).sendKeys(code);
		ReusableFunctions.waitForElementClickable(verifyButton);
		driver.findElement(verifyButton).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome\"]"));
		ReusableFunctions.waitForElementClickable(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField"));
		driver.findElement(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField")).click();
		driver.findElement(By.xpath("//XCUIElementTypeOther[1]/XCUIElementTypeTextField")).sendKeys("Tester");
		driver.findElement(By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeTextField")).click();
		driver.findElement(By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeTextField")).sendKeys("Test");
		driver.findElement(By.xpath("//XCUIElementTypeOther[3]/XCUIElementTypeSecureTextField")).sendKeys(Password);
		driver.findElement(By.xpath("//XCUIElementTypeOther[4]/XCUIElementTypeSecureTextField")).sendKeys(Password);
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Save\"]")).click();
		ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Your TimeIt account is ready!\"]"));
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Log In\"]")).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name=\"Log in \"]")), "Invited member failed to Sign Up");
    }
	
}
