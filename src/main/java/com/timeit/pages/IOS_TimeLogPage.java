package com.timeit.pages;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.timeit.base.TestBase;
import com.timeit.util.ReusableFunctions;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;

public class IOS_TimeLogPage extends TestBase {
	
	private static Logger logger = Logger.getLogger(IOS_TimeLogPage.class.getName());

    // TimeLog Page locators
	private static final By weeklyRibbon = By.xpath("*//XCUIElementTypeCollectionView");
	private static final By addTimeButton = By.xpath("//XCUIElementTypeButton[@name=\"add\"]");
	private static final By whatAreYouWorkingPageHeader = By.xpath("//XCUIElementTypeSearchField[@name=\"What are you working on?\"]");
	private static final By billableLabel = By.xpath("//XCUIElementTypeStaticText[@name=\"BILLABLE\"]");
	private static final By newEntryHeader = By.xpath("//XCUIElementTypeNavigationBar[@name=\"New Entry\"]");
	private static final By shiftIcon = By.xpath("//XCUIElementTypeImage[@name=\"DayShift\"]");
	private static final By checkmarkIcon = By.xpath("//XCUIElementTypeButton[@name=\"checkmark\"]");
	private static final By doneShiftButton = By.xpath("//XCUIElementTypeButton[@name=\"Done\"]");
	private static final By CloseShiftButton = By.xpath("//XCUIElementTypeButton[@name=\"x\"]");
	private static final By clockIcon = By.xpath("//XCUIElementTypeImage[@name=\"grayClock\"]");
	private static final By hourPickerWheel = By.xpath("*//XCUIElementTypePickerWheel[1]");
	private static final By minutesPickerWheel = By.xpath("*//XCUIElementTypePickerWheel[3]");
	private static final By donePickerWheel = By.xpath("//XCUIElementTypeStaticText[@name=\"Done\"]");
	private static final By addDescriptionInput = By.xpath("*//XCUIElementTypeCell[5]");
	private static final By createButton = By.xpath("//XCUIElementTypeButton[@name=\"Create\"]");
	private static final By notSubmittedLabel = By.xpath("//XCUIElementTypeStaticText[@name=\"Not Submitted\"]");
	private static final By editEntryHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Edit Entry\"]");
	private static final By deleteEntryButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Delete Entry\"]");
	private static final By deleteEntryPopupHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Delete entry\"]");
	private static final By areYouSurePopupMsg = By.xpath("//XCUIElementTypeStaticText[@name=\"Are you sure you want to delete this entry?\"]");
	private static final By deletePopupButton = By.xpath("//XCUIElementTypeButton[@name=\"Delete\"]");
	private static final By saveEditEntryButton = By.xpath("//XCUIElementTypeButton[@name=\"Save\"]");
	private static final By moreIconHeader = By.xpath("//XCUIElementTypeButton[@name=\"more horizontal\"]");
	private static final By reviewAndSubmitButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Review and submit\"]");
	private static final By timeSheetSummaryHeader = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Timesheet summary\"]");
	private static final By submitTimeSheetButton = By.xpath("//XCUIElementTypeButton[@name=\"Submit\"]");
	private static final By submitTimeSheetPopupHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Submit timesheet\"]");
	private static final By submitButtonPopup = By.xpath("(//XCUIElementTypeButton[@name=\"Submit\"])[2]");
	private static final By submitSuccessfulText = By.xpath("//XCUIElementTypeStaticText[@name=\"Submit successful!\"]");
	private static final By doneSubmitPopupButton = By.xpath("//XCUIElementTypeButton[@name=\"Done\"]");
	private static final By submittedLabel = By.xpath("//XCUIElementTypeStaticText[@name=\"Submitted\"]");
	private static final By calenderLink = By.xpath("//XCUIElementTypeNavigationBar[@name=\"CrewTimeTracker.HomeTabBarView\"]//XCUIElementTypeButton[1]");
	private static final By closeCalenderbutton = By.xpath("//XCUIElementTypeButton[@name=\"x\"]");
	private static final By assignMembers = By.xpath("//XCUIElementTypeStaticText[@name=\"Assign member & time\"]");
	private static final By selectMembersHeader =  By.xpath("//XCUIElementTypeNavigationBar[@name=\"Select Members\"]");
	private static final By selectMembersDoneButton = By.xpath("//XCUIElementTypeButton[@name=\"Done\"]");
	private static final By assignMemberTimeHeader = By.xpath("//XCUIElementTypeNavigationBar[@name=\"Assign Member & Time\"]");
	private static final By timeInput = By.xpath("*//XCUIElementTypeOther/XCUIElementTypeTextField");
	private static final By assignMemberDoneButton = By.xpath("//XCUIElementTypeButton[@name=\"Done\"]");
	private static final By deleteAllEntriesButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Delete all entries\"]");
	private static final By deleteAllEntriesPopupHeader = By.xpath("//XCUIElementTypeStaticText[@name=\"Delete all entries\"]");
	private static final By deleteAllEntriesPopupDeleteButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Delete\"]");

	
	
	
    public void addTimeEntry(String Day, String TaskName, String Shift, String Time) {
    	logger.info("Adding time entry to TimeLog Screen");
    	ReusableFunctions.waitForElementVisible(weeklyRibbon);
    	selectDay(Day);
    	ReusableFunctions.waitForElementClickable(addTimeButton);
		driver.findElement(addTimeButton).click();
		ReusableFunctions.waitForElementVisible(whatAreYouWorkingPageHeader);
		driver.findElement(billableLabel).isDisplayed();
		selectTask(TaskName);
		ReusableFunctions.waitForElementVisible(newEntryHeader);
		ReusableFunctions.waitForElementVisible(createButton);
		driver.findElement(shiftIcon).click();
		selectShift(Shift);
		driver.findElement(clockIcon).click();
		selectTime(Time);
		ReusableFunctions.waitForElementClickable(addDescriptionInput);
		driver.findElement(addDescriptionInput).sendKeys("Spent time on "+TaskName);
		driver.findElement(createButton).click();
		ReusableFunctions.waitForElementVisible(addTimeButton);
		ReusableFunctions.waitForElementClickable(addTimeButton);
		driver.findElementByAccessibilityId(Shift).isDisplayed();
		driver.findElementByAccessibilityId(Time).isDisplayed();
		Assert.assertTrue(driver.findElementByAccessibilityId(TaskName).isDisplayed(), "Failed to log time entry");
		logger.info("Timesheet added successfully");
    }
    
    

	public void editTimeEntry() {
    	logger.info("Edit the time entry after logging time");
    	addTimeEntry("Tuesday", "Emergency", "Night", "07:30");
    	String CurrentShiftTime = getShiftTime("07:30");
    	String CurrentShiftType = getShiftType("Night");
    	selectTask(getTask("Emergency"));
		ReusableFunctions.waitForElementVisible(editEntryHeader);
		String NewShiftTime = "07:00";
		String NewShiftType = "Morning";
		driver.findElement(shiftIcon).click();
		ReusableFunctions.waitForElementVisible(CloseShiftButton);
		driver.findElementByAccessibilityId(NewShiftType).click();
		driver.findElement(clockIcon).click();
		selectTime(NewShiftTime);
		ReusableFunctions.waitForElementClickable(addDescriptionInput);
		driver.findElement(addDescriptionInput).sendKeys("Updated description goes here");
		driver.findElement(saveEditEntryButton).click();
		ReusableFunctions.waitForElementVisible(notSubmittedLabel);
		ReusableFunctions.waitForElementClickable(addTimeButton);
		String UpdatedShiftTime = getShiftTime(NewShiftTime);
		String UpdatedShiftType = getShiftType(NewShiftType);
		logger.info("Actual Shift Time is '"+CurrentShiftTime+"' and Updated Shift Time is '"+UpdatedShiftTime+"'");
		logger.info("Actual Shift Type is '"+CurrentShiftType+"' and Updated Shift Type is '"+UpdatedShiftType+"'");
		Assert.assertTrue(ReusableFunctions.verifyTextMatch(NewShiftTime, UpdatedShiftTime), "Failed to edit time");
		Assert.assertTrue(ReusableFunctions.verifyTextMatch(NewShiftType, UpdatedShiftType), "Failed to edit shift");
		logger.info("Timesheet edited successfully");
    }
    
	public void deleteTimeEntry() {
		logger.info("Deleting time entry after logging time");
		String taskName = "Consultation";
		addTimeEntry("Wednesday", taskName, "Night", "06:45");
		ReusableFunctions.waitForElementVisible(driver.findElementByAccessibilityId(taskName));
		driver.findElementByAccessibilityId(taskName).click();
		ReusableFunctions.waitForElementVisible(editEntryHeader);
		driver.findElement(deleteEntryButton).click();
		driver.findElement(deleteEntryPopupHeader).isDisplayed();
		driver.findElement(areYouSurePopupMsg).isDisplayed();
		driver.findElement(deletePopupButton).click();
		ReusableFunctions.waitForElementVisible(addTimeButton);
		Assert.assertFalse(verifyTaskVisible(taskName), "Failed to delete time entry");
		logger.info("Timesheet deleted successfully");
	}
	
	
	
	public void submitTimeSheet() {
		logger.info("Submitting time sheet for whole week");
		ReusableFunctions.waitForElementVisible(moreIconHeader);
		driver.findElement(moreIconHeader).click();
		driver.findElement(reviewAndSubmitButton).click();
		ReusableFunctions.waitForElementVisible(timeSheetSummaryHeader);
		ReusableFunctions.waitForElementClickable(submitTimeSheetButton);
		driver.findElement(submitTimeSheetButton).click();
		ReusableFunctions.waitForElementVisible(submitTimeSheetPopupHeader);
		driver.findElement(submitButtonPopup).click();
		ReusableFunctions.waitForElementVisible(submitSuccessfulText);
		driver.findElement(doneSubmitPopupButton).click();
		Assert.assertTrue(ReusableFunctions.waitForElementVisible(submittedLabel), "Failed to submit time sheet");
		logger.info("Submitted timesheet successfully");
	}
	
	
	public void timekeeperAddsTimeSheetToTeamMembers(String User1, String User2) {
		logger.info("Adding time sheet for team members: "+User1+", "+User2);
		timekeeperAddTimeEntryToWholeWeek(User1, User2);
	}
	
	
	public void timekeeperAddTimeEntry(String Day, String TaskName, String Shift, String Time, String User1, String User2) {
    	logger.info("Adding time entry to TimeLog Screen");
    	ReusableFunctions.waitForElementVisible(weeklyRibbon);
    	selectDay(Day);
    	ReusableFunctions.waitForElementClickable(addTimeButton);
		driver.findElement(addTimeButton).click();
		ReusableFunctions.waitForElementVisible(whatAreYouWorkingPageHeader);
		driver.findElement(billableLabel).isDisplayed();
		selectTask(TaskName);
		ReusableFunctions.waitForElementVisible(newEntryHeader);
		ReusableFunctions.waitForElementVisible(createButton);
		driver.findElement(shiftIcon).click();
		selectShift(Shift);
		ReusableFunctions.waitForElementClickable(assignMembers);
		driver.findElement(assignMembers).click();
		ReusableFunctions.waitForElementClickable(selectMembersHeader);
		driver.findElementByAccessibilityId(User1).click();
		driver.findElementByAccessibilityId(User2).click();
		driver.findElement(selectMembersDoneButton).click();
		ReusableFunctions.waitForElementVisible(assignMemberTimeHeader);
		driver.findElement(timeInput).click();
		selectTime(Time);
		driver.findElement(assignMemberDoneButton).click();
		ReusableFunctions.waitForElementVisible(newEntryHeader);
		driver.findElement(addDescriptionInput).sendKeys("Adding time on "+TaskName);
		driver.findElement(createButton).click();
		ReusableFunctions.waitForElementVisible(weeklyRibbon);
		ReusableFunctions.waitForElementClickable(addTimeButton);
		driver.findElementByAccessibilityId(Shift).isDisplayed();
		Assert.assertTrue(driver.findElementByAccessibilityId(TaskName).isDisplayed(), "Failed to log time entry");
    }
	
    
    public static void selectFutureDate() {
    	driver.findElement(calenderLink).click();
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_YEAR, 7);
    	Date futureDateTime = calendar.getTime();
    	String futureDate = futureDateTime.toString().substring(8, 10);
    	logger.info("Selecting future date "+futureDate);
    	driver.findElement(By.xpath("//XCUIElementTypeButton[contains(@name, '"+futureDate+"')]")).click();
    	driver.findElement(closeCalenderbutton).click();
    	Assert.assertTrue(driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name, '"+futureDate+"')]")).isDisplayed(), "Failed to select Future Date");
	}



	public void addTimeEntryToWholeWeek() {
		logger.info("Adding time entry to whole week");
		addTimeEntry("Monday", "Consultation", "Night", "08:00");
		addTimeEntry("Tuesday", "Emergency", "Morning", "05:30");
		addTimeEntry("Wednesday", "Surgery", "Night", "06:30");
		addTimeEntry("Thursday", "Ward Rounds", "Morning", "07:30");
		addTimeEntry("Friday", "Consultation", "Morning", "08:30");
	}

	
	
	public void timekeeperAddTimeEntryToWholeWeek(String User1, String User2) {
		logger.info("Timekeeper adding time entry for team members for while weeek");
		//timekeeperAddTimeEntry("Monday", "Consultation", "Night", "08:00", User1, User2);
		timekeeperAddTimeEntry("Tuesday", "Surgery", "Night", "05:30", User1, User2);
		timekeeperAddTimeEntry("Wednesday", "Emergency", "Night", "06:30", User1, User2);
		timekeeperAddTimeEntry("Thursday", "Consultation", "Morning", "07:30", User1, User2);
		//timekeeperAddTimeEntry("Friday", "Consultation", "Morning", "08:30", User1, User2);
	}
	
	


	public void selectShift(String shiftName) {
		logger.info("Selecting '" + shiftName + "' in the shift selection screen");
		ReusableFunctions.waitForElementVisible(checkmarkIcon);
		switch (shiftName) {
		case "Morning":
			ReusableFunctions.waitForElementClickable(CloseShiftButton);
			driver.findElement(CloseShiftButton).click();
			break;
		case "Night":
			driver.findElementByAccessibilityId(shiftName).click();
			break;
		}
		Assert.assertTrue(ReusableFunctions.verifyTextMatch(getShiftType(shiftName), shiftName),
				"Failed to select '" + shiftName + "' as shift type");
	}


	public void selectTime(String time) {
		logger.info("Selecting '"+time+"' in the date picker");
		CharSequence hours = time.subSequence(0, 2);
		CharSequence minutes = time.subSequence(3, 5);
		ReusableFunctions.waitForElementVisible(donePickerWheel);
		driver.findElement(hourPickerWheel).sendKeys(hours);
		driver.findElement(minutesPickerWheel).sendKeys(minutes);
		driver.findElement(donePickerWheel).click();
		Assert.assertTrue(ReusableFunctions.verifyTextMatch(getShiftTime(time), time), "Failed to select '"+time+"' as shift time");
	}

	public void selectTask(String taskName) {
    	logger.info("Selecting task '"+taskName+"' from the list of tasks");
    	driver.findElementByAccessibilityId(taskName).click();
		Assert.assertTrue(ReusableFunctions.verifyTextMatch(getShiftType(taskName), taskName), "Failed to select '"+taskName+"' task");	
	}

	
	public void selectDay(String Day) {
    	switch (Day){
        case "Monday":
            logger.info("Selecting '"+Day+"' in the date ribbon");
            String day1 = "Monday";
            CharSequence trimDay1 = day1.subSequence(0, 3);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ trimDay1 +"']")).click();
            break;
        case "Tuesday":
        	logger.info("Selecting '"+Day+"' in the date ribbon");
            String day2 = "Tuesday";
            CharSequence trimDay2 = day2.subSequence(0, 3);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ trimDay2 +"']")).click();
            break;
        case "Wednesday":
        	logger.info("Selecting '"+Day+"' in the date ribbon");
            String day3 = "Wednesday";
            CharSequence trimDay3 = day3.subSequence(0, 3);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ trimDay3 +"']")).click();
            break;
        case "Thursday":
        	logger.info("Selecting '"+Day+"' in the date ribbon");
            String day4 = "Thursday";
            CharSequence trimDay4 = day4.subSequence(0, 3);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ trimDay4 +"']")).click();
            break;
        case "Friday":
        	logger.info("Selecting '"+Day+"' in the date ribbon");
            String day5 = "Friday";
            CharSequence trimDay5 = day5.subSequence(0, 3);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ trimDay5 +"']")).click();
            break;
        case "Saturday":
        	logger.info("Selecting '"+Day+"' in the date ribbon");
            String day6 = "Saturday";
            CharSequence trimDay6 = day6.subSequence(0, 3);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ trimDay6 +"']")).click();
            break;
        case "Sunday":
        	logger.info("Selecting '"+Day+"' in the date ribbon");
            String day7 = "Sunday";
            CharSequence trimDay7 = day7.subSequence(0, 3);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ trimDay7 +"']")).click();
            break;
        }
		
	}

	
	public void deleteAllEntries() {
		if(ReusableFunctions.waitForElementVisible(moreIconHeader)==true) {
		driver.findElement(moreIconHeader).click();
		ReusableFunctions.waitForElementVisible(deleteAllEntriesButton);
		driver.findElement(deleteAllEntriesButton).click();
		ReusableFunctions.waitForElementVisible(deleteAllEntriesPopupHeader);
		driver.findElement(deleteAllEntriesPopupDeleteButton).click();
		ReusableFunctions.waitForElementVisible(notSubmittedLabel);
		logger.info("Delete All Entries is Successful");}
		else {
			logger.info("No Timesheet Entries to Delete");
		}
	}

	
    public static String getShiftTime(String shiftTime) {
    	String CurrentShiftTime = driver.findElementByAccessibilityId(shiftTime).getText();
		return CurrentShiftTime;
    }
    
    public static String getShiftType(String shiftType) {
		String CurrentShiftType = driver.findElementByAccessibilityId(shiftType).getText();
    	return CurrentShiftType;
    }
    
    public static String getTask(String taskName) {
    	String CurrentTask = driver.findElementByAccessibilityId(taskName).getText();
		return CurrentTask;
    }
    
    public static boolean verifyTaskVisible(String taskName) {
    	boolean flag = ReusableFunctions.waitForElementVisible(By.xpath("//XCUIElementTypeStaticText[@name='"+ taskName +"']"));
		return flag;
    }
}
