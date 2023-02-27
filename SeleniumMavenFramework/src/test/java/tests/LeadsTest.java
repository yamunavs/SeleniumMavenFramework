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
public class LeadsTest extends BaseTest {

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
	public void leadsTabTest() {
		String expected = "Leads: Home ~ Salesforce - Developer Edition";

		login();

		WebElement lead = findElement("xpath", "//a[contains(text(),'Leads')]");
		clickElement(lead, "lead");

		String actual = driver.getTitle();
		assertEquals(actual, expected);
	}

	@Test
	public void leadsSelectViewTest() {
		String[] expected = { "All Open Leads", "My Unread Leads", "Recently Viewed Leads", "Today's Leads",
				"View - Custom 1", "View - Custom 2" };
		List<String> listExpected = Arrays.asList(expected);

		login();

		WebElement lead = findElement("xpath", "//a[contains(text(),'Leads')]");
		clickElement(lead, "lead");
		WebElement view = findElement("xpath", "//select[@id='fcf']");
		clickElement(view, "view");
		Select select = new Select(view);
		List<WebElement> alloption = select.getOptions();

		List<String> actualList = new ArrayList<String>();
		for (WebElement option : alloption) {
			actualList.add(option.getText());
		}

		assertEquals(actualList, listExpected);

	}

	@Test
	public void defaultViewTest() {
		String expected = "Leads ~ Salesforce - Developer Edition";
		login();
		WebElement lead = findElement("xpath", "//a[contains(text(),'Leads')]");
		clickElement(lead, "lead");
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();

		WebElement view = findElement("xpath", "//select[@id='fcf']");
		selectFromDropDown(view, "My Unread Leads");

		WebElement userMenu = findElement("id", "userNavLabel");
		clickElement(userMenu, "userMenu");

		WebElement logout = findElement("xpath", "//a[contains(text(),'Logout')]");
		clickElement(logout, "logout");

		login();
		lead = findElement("xpath", "//a[contains(text(),'Leads')]");
		clickElement(lead, "lead");

		closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();

		view = findElement("xpath", "//select[@id='fcf']");
		Select select = new Select(view);
		select.getFirstSelectedOption();

		WebElement goButton = findElement("xpath", "//input[@title='Go!']");
		clickElement(goButton, "goButton");

		String actual = driver.getTitle();
		assertEquals(actual, expected);
	}
	@Test
	public void todaysLeadTest() {
		String expected = "Leads ~ Salesforce - Developer Edition";

		login();

		WebElement lead = findElement("xpath", "//a[contains(text(),'Leads')]");
		clickElement(lead, "lead");

		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();

		WebElement view = findElement("xpath", "//select[@id='fcf']");
		selectFromDropDown(view, "Today's Leads");

		WebElement goButton = findElement("xpath", "//input[@title='Go!']");
		clickElement(goButton, "goButton");

		String actual = driver.getTitle();
		assertEquals(actual, expected);
	}
	@Test
public void newButtonLeadTest() {
		
		String expectedPageTitle="Lead Edit: New Lead ~ Salesforce - Developer Edition";
		
		login();

		WebElement lead = findElement("xpath", "//a[contains(text(),'Leads')]");
		clickElement(lead, "lead");

		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		WebElement newButton= findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(newButton, "newButton");
		
		String actualPageTitle=driver.getTitle(); 
		assertEquals(actualPageTitle, expectedPageTitle);
		
		WebElement lastname= findElement("id", "name_lastlea2");
		enterText(lastname, "ABCD","lastname");
		
		WebElement companyName= findElement("id", "lea3");
		enterText(companyName, "ABCD","companyName");
		
		WebElement savebutton= findElement("xpath", "//input[@title='Save']");
		clickElement(savebutton, "savebutton");
		
		
	}
	
	
}
