package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.utilityclasses.PropertiesUtilities;

import base.BaseTest;

@Listeners(com.utilityclasses.TestEventListenersUtility.class)
public class ContactTest extends BaseTest {


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
public void createNewContactTest() {
		
		login();
		
		WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
		clickElement(contacts, "contacts");
		
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		WebElement newButton= findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(newButton, "newButton");
		
		WebElement lastaName= findElement("id", "name_lastcon2");
		enterText(lastaName, "xyz","lastaName");
		
		WebElement accountName= findElement("id", "con4");
		enterText(accountName, "yjvivek","accountName");
		
		WebElement save= findElement("xpath", "//input[@title='Save']");
		clickElement(save, "save");
		
	}
	@Test
public void newViewContactTest() {
		
      login();
		
		WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
		clickElement(contacts, "contacts");
		
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		WebElement createNewView= findElement("xpath", "//a[contains(text(),'Create New View')]");
		clickElement(createNewView, "createNewView");
		
		WebElement viewName= findElement("id", "fname");
		enterText(viewName, "testView","viewName");
		
		WebElement uniqueName=findElement("id", "devname");
		enterText(uniqueName, "testview123","uniqueName");
		
		WebElement saveButton= findElement("xpath", "//input[@title='Save']");
	    clickElement(saveButton, "saveButton");
		
		
		
	}
	@Test
	public void recentlyCreatedTest() {
		
		 login();
			WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
			clickElement(contacts, "contacts");
			
			WebElement closeDialogBox = findElement("id", "tryLexDialogX");
			closeDialogBox.click();
			
			WebElement view=findElement("id", "fcf");
			selectFromDropDown(view, "Recently Viewed Contacts");
			
			WebElement goButton= findElement("xpath", "//input[@title='Go!']");
			clickElement(goButton, "goButton");
			
			
		
	}
	
	@Test
	public void myContactsViewTest() {
		login();
		WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
		clickElement(contacts, "contacts");
		
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		WebElement view=findElement("id", "fcf");
		selectFromDropDown(view, "My Contacts");
		
		WebElement goButton= findElement("xpath", "//input[@title='Go!']");
		clickElement(goButton, "goButton");
	}
	
	@Test
	public void contactNameTest() {
		String expected= "Contact: abcd ~ Salesforce - Developer Edition";
		
       login();
		
		WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
		clickElement(contacts, "contacts");
		
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		WebElement recentContactsName= findElement("xpath", "//a[contains(text(),'abcd')]");
		clickElement(recentContactsName, "recentContactsName");
		
		String actual= driver.getTitle();
		
		assertEquals(actual, expected);
		
		
		
	}
	@Test
	public void checkErrorMessageInCreateView() {
		
		String expected= "Error: You must enter a value";
		
		 login();
			WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
			clickElement(contacts, "contacts");
			
			WebElement closeDialogBox = findElement("id", "tryLexDialogX");
			closeDialogBox.click();
			
			WebElement createNewView= findElement("xpath", "//a[contains(text(),'Create New View')]");
			clickElement(createNewView, "createNewView");
			
			WebElement uniqueName=findElement("id", "devname");
			enterText(uniqueName, "EFGH","createNewView");
			
			WebElement saveButton= findElement("xpath", "//input[@title='Save']");
			clickElement(saveButton, "saveButton");
			
			WebElement error= findElement("xpath", "//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/form[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[2]");
			String actual=error.getText();
			
			assertEquals(actual, expected);
	}
	@Test
	public void checkCancelButton() {
		
		String expected= "Contacts: Home ~ Salesforce - Developer Edition";
		
		login();
		WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
		clickElement(contacts, "contacts");
		
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		WebElement createNewView= findElement("xpath", "//a[contains(text(),'Create New View')]");
		clickElement(createNewView, "createNewView");
		

		WebElement viewName= findElement("id", "fname");
		enterText(viewName, "testView","viewName");
		
		WebElement uniqueName=findElement("id", "devname");
		enterText(uniqueName, "testview123","uniqueName");
		
		WebElement cancel= findElement("xpath", "//input[@title='Cancel']");
		clickElement(cancel, "cancel");
		
		String actual=driver.getTitle();
		
		assertEquals(actual, expected);
		
		
	}
	@Test
	public void checkSaveAndNewButton() {
         login();
		
		WebElement contacts=findElement("xpath", "//a[@title='Contacts Tab']");
		clickElement(contacts, "contacts");
		
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		WebElement newButton= findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(newButton, "newButton");
		
		WebElement lastaName= findElement("id", "name_lastcon2");
		enterText(lastaName, "xyz","lastaName");
		
		WebElement accountName= findElement("id", "con4");
		enterText(accountName, "yjvivek","accountName");
		
		WebElement newAndSave= findElement("xpath", "//input[@title='Save & New']");
		clickElement(newAndSave, "newAndSave");
		
		
	}
}