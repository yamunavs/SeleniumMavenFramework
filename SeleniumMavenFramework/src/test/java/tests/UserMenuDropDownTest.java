package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.utilityclasses.PropertiesUtilities;

import base.BaseTest;

@Listeners(com.utilityclasses.TestEventListenersUtility.class)
public class UserMenuDropDownTest extends BaseTest {

	public void login() {

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("usermenuDataProperties");
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
	public void userMenuTest() {
		String expected = "[My Profile, My Settings, Developer Console, Switch to Lightning Experience, Logout]";
		login();

		WebElement usermenu = findElement("id", "userNavLabel");
		usermenu.click();
		logger.info("user menu is clicked");
		ExtentReport.logTestInfo("user menu is clicked");

		List<WebElement> dropdown = driver.findElements(By.className("menuButtonMenuLink"));
		ArrayList<String> list = new ArrayList<>();

		for (WebElement Element : dropdown) {
			if (!Element.getText().isBlank() || !Element.getText().isEmpty()) {
				list.add(Element.getText());

			}
		}
		String actual = list.toString();
		assertEquals(actual, expected);

	}

	@Test
	public void mySettingsTest() {
		String expected = "Start Time: ";
		login();
		logger.info("loginpage is launched");
		ExtentReport.logTestInfo("loginpage is launched");

		WebElement usermenu = findElement("id", "userNavLabel");
		clickElement(usermenu, "userMenu");

		WebElement mySettingsLink = findElement("xpath", "//a[contains(text(),'My Settings')]");
		clickElement(mySettingsLink, "mysettings");

		WebElement personal = findElement("xpath", "//tbody/tr[1]/td[1]/div[1]/div[4]/div[2]/a[1]");
		clickElement(personal, "personal link");

		WebElement loginHistory = findElement("xpath", "//a[@id='LoginHistory_font']");
		clickElement(loginHistory, "loginHistory");

		WebElement downloadLoginHistory = findElement("xpath",
				"//a[contains(text(),'Download login history for last six months, includ')]");
		clickElement(downloadLoginHistory, "downloadLogInHistory");

		WebElement displayLayoutLink = findElement("xpath", "//tbody/tr[1]/td[1]/div[1]/div[4]/div[3]/a[1]");
		clickElement(displayLayoutLink, "displayLayOutLink");

		WebElement customizeTab = findElement("xpath", "//a[@id='CustomizeTabs_font']");
		clickElement(customizeTab, "customizeTab");

		WebElement customAppDropDown = findElement("id", "p4");
		selectFromDropDown(customAppDropDown, "Salesforce Chatter");

		WebElement availableTabs = findElement("id", "duel_select_0");
		selectFromDropDown(availableTabs, "Reports");

		WebElement add = findElement("xpath", "//a[@id='duel_select_0_right']");
		clickElement(add, "addButton");

		WebElement save = findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(save, "saveButton");

		WebElement emailLink = findElement("xpath", "//tbody/tr[1]/td[1]/div[1]/div[4]/div[4]/a[1]");
		clickElement(emailLink, "email");

		WebElement myEmailSettings = findElement("xpath", "//a[@id='EmailSettings_font']");
		clickElement(myEmailSettings, "myEmailSettings");

		WebElement emailAdress = findElement("id", "sender_email");
		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("usermenuDataProperties");
		String Email = ob.getPropertyData("emailaddress");
		enterText(emailAdress, Email, "email");

		WebElement automaticBCC = findElement("id", "auto_bcc1");
		clickElement(automaticBCC, "automaticBCC");

		WebElement saveButton = findElement("xpath", "//tbody/tr[1]/td[2]/input[1]");
		clickElement(saveButton, "saveButton");

		WebElement calenderRemainders = findElement("xpath", "//*[@id=\"CalendarAndReminders\"]/a");
		clickElement(calenderRemainders, "calenderRemainders");

		WebElement activityRemainders = findElement("xpath", "//a[@id='Reminders_font']");
		clickElement(activityRemainders, "activityRemainders");

		String baseWindow = driver.getWindowHandle();
		WebElement openTestRemainder = findElement("xpath", "//input[@id='testbtn']");
		clickElement(openTestRemainder, "openTestRemainder");

		Set<String> allwindows = driver.getWindowHandles();
		String ActualElement = null;
		for (String window : allwindows) {
			if (!window.equalsIgnoreCase(baseWindow)) {
				driver.switchTo().window(window);
				WebElement startTime = findElement("xpath", "//*[@id=\"summary0\"]/table/tbody/tr[1]/td[1]/span");
				ActualElement = startTime.getText();
				break;
			}

		}
		assertEquals(ActualElement, expected);

	}

	@Test
	public void developerConsoleTest() {
		login();
		
		WebElement usermenu = findElement("id", "userNavLabel");
		clickElement(usermenu, "userMenu");
		
		WebElement developerConsoleLink=findElement("xpath", "//a[contains(text(),'Developer Console')]");
		String baseWinsow=driver.getWindowHandle();
		clickElement(developerConsoleLink, "developerConsoleLink");
		Set<String> allwindow= driver.getWindowHandles();
		for (String window : allwindow) {
			if (!window.equalsIgnoreCase(baseWinsow)) {
				driver.switchTo().window(window);
				driver.close();
				break;
			}
			driver.switchTo().window(baseWinsow);
		}
		

	}
	@Test
	public void logOutTest() {
		String expected="Login | Salesforce";
		login();
		
		WebElement usermenu = findElement("id", "userNavLabel");
		clickElement(usermenu, "userMenu");
		WebElement logout=findElement("xpath", "//a[contains(text(),'Logout')]");
		clickElement(logout, "logout");
		ThreadSleep(1000);
		String actual=driver.getTitle();
		assertEquals(actual, expected);
	}

}
