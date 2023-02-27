package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.utilityclasses.PropertiesUtilities;

import base.BaseTest;

@Listeners(com.utilityclasses.TestEventListenersUtility.class)
public class CreateOppourtunityTest extends BaseTest {

	public void login() {

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("createAccountDataProperties");
		String username = ob.getPropertyData("login.valid.usename");
		WebElement userNameElement = findElement("id", "username");
		enterText(userNameElement, username, "username");

		String password = ob.getPropertyData("login.valid.password");
		WebElement passwordElement = findElement("id", "password");
		enterText(passwordElement, password, "password");

		WebElement loginElement = findElement("id", "Login");
		loginElement.click();
		logger.info("loginpage is launched");
		ExtentReport.logTestInfo("loginpage is launched");

	}
	@Test
	public void oppourtunityDropDownTest() {

		String[] expected = { "All Opportunities", "Closing Next Month", "Closing This Month", "My Opportunities",
				"New Last Week", "New This Week", "Opportunity Pipeline", "Private", "Recently Viewed Opportunities",
				"Won" };
		List<String> listExpected = Arrays.asList(expected);

		login();

		WebElement oppourtunities = findElement("xpath", "//a[contains(text(),'Opportunities')]");
		clickElement(oppourtunities, "oppourtunities");

		WebElement oppourtunityDropDown = findElement("xpath", "//select[@id='fcf']");
		waitToLoadWebPage(oppourtunityDropDown);
		clickElement(oppourtunityDropDown, "oppourtunityDropDown");

		Select select = new Select(oppourtunityDropDown);
		List<WebElement> options = select.getOptions();
		List<String> actualList = new ArrayList<String>();
		for (WebElement option : options) {
			actualList.add(option.getText());
		}
		assertEquals(actualList, listExpected);

	}
	@Test
	public void createNewOppourtunityTest() {
		login();
		WebElement oppourtunities= findElement("xpath", "//a[contains(text(),'Opportunities')]");
		clickElement(oppourtunities, "oppourtunities");
		
		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();
		
		
		WebElement newButton= findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(newButton, "newButton");
		
		WebElement oppourtunityName= findElement("xpath", "//input[@id='opp3']");
		enterText(oppourtunityName, "abc","oppourtunityName");
		
		WebElement accountName= findElement("xpath", "//tbody/tr[4]/td[2]/span[1]/a[1]/img[1]");
		enterText(accountName, "yjvivek","accountName");
		WebElement accountSearch=findElement("xpath", "//a[@id='opp4_lkwgt']");
		clickElement(accountSearch, "accountSearch");
		driver.switchTo().frame("resultsFrame");
		
		
		
		WebElement closeDate= findElement("xpath", "//input[@id='opp9']");
		clickElement(closeDate, "closeDate");
		WebElement today= findElement("xpath", "//a[contains(text(),'Today')]");
		clickElement(today, "today");
		
		WebElement stage=findElement("xpath", "//select[@id='opp11']");
		clickElement(stage, "stage");
		selectFromDropDown(stage, "Qualification");
		
		WebElement probablity = findElement("xpath", "//input[@id='opp12']");
		enterText(probablity, "10","probablity");
		
		WebElement leadsource= findElement("xpath", "//select[@id='opp6']");
		clickElement(leadsource, "leadsource");
		selectFromDropDown(leadsource, "Web");
		
		WebElement save= findElement("xpath", "//input[@title='Save']");
		clickElement(save, "save");
		
		
	}
	@Test
public void OpportunityPipelineReport() {
String expected ="Opportunity Pipeline ~ Salesforce - Developer Edition";
		
		login();
		
		WebElement oppourtunities= findElement("xpath", "//a[contains(text(),'Opportunities')]");
		clickElement(oppourtunities, "oppourtunities");
		
		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();
		
		WebElement oppourtunitypipeline= findElement("xpath", "//a[contains(text(),'Opportunity Pipeline')]");
		clickElement(oppourtunitypipeline, "oppourtunitypipeline");
		
		String actual= driver.getTitle();
		assertEquals(actual, expected);
}
	@Test 
	public void StuckOpportunitiesReportTest() {
		String expected="Stuck Opportunities ~ Salesforce - Developer Edition";
		
		login();
		
		WebElement oppourtunities= findElement("xpath", "//a[contains(text(),'Opportunities')]");
		clickElement(oppourtunities, "oppourtunities");
		
		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();
		
		WebElement stuckOpportunities=findElement("xpath", "//a[contains(text(),'Stuck Opportunities')]");
		clickElement(stuckOpportunities, "stuckOpportunities");
		
		String actual= driver.getTitle();
		assertEquals(actual, expected);
	}
	@Test
	public void QuarterlySummaryReportTest() {
		String expected="Opportunity Report ~ Salesforce - Developer Edition";
		
		login();
		
		WebElement oppourtunities= findElement("xpath", "//a[contains(text(),'Opportunities')]");
		clickElement(oppourtunities, "oppourtunities");
		
		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();
		
		WebElement interval=findElement("xpath", "//select[@id='quarter_q']");
		clickElement(interval, "interval");
		selectFromDropDown(interval, "Next FQ");
		
		WebElement include=findElement("xpath", "//select[@id='open']");
		clickElement(include, "include");
		selectFromDropDown(include, "Open Opportunities");
		
		WebElement runReport=findElement("xpath", "//tbody/tr[3]/td[1]/input[1]");
		clickElement(runReport, "runReport");
		
		String actual= driver.getTitle();
		assertEquals(actual, expected);
		
	}
}
