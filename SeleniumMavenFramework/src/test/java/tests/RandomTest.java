package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.utilityclasses.PropertiesUtilities;

import base.BaseTest;
@Listeners(com.utilityclasses.TestEventListenersUtility.class)
public class RandomTest extends BaseTest{

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
	public void verifyLoggedInUserLink() {
		String expected="Salesforce - Developer Edition";
		String expectedFinal="User: yamuna ABC ~ Salesforce - Developer Edition";
		login();
		
		WebElement home= findElement("xpath", "//a[@title='Home Tab']");
		clickElement(home, "home");
		
		WebElement closeDialogBox = findElement("id", "tryLexDialogX");
		closeDialogBox.click();
		
		
		String actual= driver.getTitle();
		assertEquals(actual, expected);
		
		WebElement usernameLink= findElement("xpath", "//a[contains(text(),'yamuna ABC')]");
		clickElement(usernameLink, "usernameLink");
		
		String actualFinal=driver.getTitle();
		
		assertEquals(actualFinal, expectedFinal);
	
	}
	
	@Test
public  void verifyEditedLastNameUpdate() {
	login();
	String expectedNameLinkpage="User: yamuna xyz ~ Salesforce - Developer Edition";
	String expectedContactElement= " Email";
	String expectedChangedName="yamuna abc  ";
	
	WebElement homeTab= findElement("xpath", "//a[@title='Home Tab']");
	clickElement(homeTab, "homeTab");
	ThreadSleep(1000);
	
	WebElement closeDialogBox = findElement("id", "tryLexDialogX");
	closeDialogBox.click();
	
	
	WebElement firstnamelastNameLink= findElement("xpath", "//h1[@class='currentStatusUserName']//child::a");
	clickElement(firstnamelastNameLink, "firstnamelastNameLink");
	ThreadSleep(1000);
	
	String actualNameLinkPage=driver.getTitle();
	assertEquals(actualNameLinkPage, expectedNameLinkpage);
	

	WebElement edit = findElement("xpath",
			"//tbody/tr[1]/td[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/div[1]/div[1]/a[1]/img[1]");
	ThreadSleep(2000);
	clickElement(edit, "edit");
	driver.switchTo().frame("contactInfoContentId");
	
//	WebElement email=findElement("xpath", "//*[@id=\"TabPanel:0:Body:2\"]/div[1]/div[1]/div/div[1]/label");
//	String actualContactElement=email.getText();
//	assertEquals(actualContactElement, expectedContactElement);
	
	WebElement aboutTab = findElement("id", "aboutTab");
	clickElement(aboutTab, "aboutTab");
	
	WebElement lastname = findElement("id", "lastName");
	enterText(lastname, "abc","lastname");
	findElement("xpath", "//input[@value='Save All']").click();
	String actualChangedName=findElement("id", "tailBreadcrumbNode").getText();
	assertEquals(actualChangedName, expectedChangedName);
	
	String actualChangedUserName= findElement("id", "userNav").getText();
	assertEquals(actualChangedUserName, expectedChangedName);
	
	
}
	@Test
	public  void verfifyTabCustomization() {
		
		
		
		login();
		
		WebElement tabplus=findElement("xpath", "//li[@id='AllTab_Tab']");
		clickElement(tabplus, "tabplus");
		String actuallAllTabPageTitle=driver.getTitle();
		


		
		WebElement customizeMyTabs=findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(customizeMyTabs, "customizeMyTabs");
		
		WebElement assets= findElement("xpath", "//option[contains(text(),'Assets')]");
		clickElement(assets, "assets");
		
		WebElement remove= findElement("xpath", "//a[@id='duel_select_0_left']");
		clickElement(remove, "remove");
		WebElement saveButton= findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(saveButton, "saveButton");
		
		WebElement usermenuDropDown= findElement("xpath", "//div[@id='userNav']");
		clickElement(usermenuDropDown, "usermenuDropDown");
		
		WebElement logout=findElement("xpath", "//a[contains(text(),'Logout')]");
		clickElement(logout, "logout");
		
		 
		
		
		
		}
	
}
