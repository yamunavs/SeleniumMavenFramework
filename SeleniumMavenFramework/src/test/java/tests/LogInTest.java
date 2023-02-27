package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilityclasses.ExtentReportsUtility;
import com.utilityclasses.PropertiesUtilities;

import base.BaseTest;
@Listeners(com.utilityclasses.TestEventListenersUtility.class)
public class LogInTest extends BaseTest {

   
	@Test
	public void error_logIn() throws IOException {
		logger.info("inside errorlogin method");
		ExtentReport.logTestInfo("inside errorlogin method");
		String expected = "Please enter your password.";
		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");

		String username = ob.getPropertyData("login.valid.usename");

		WebElement userNameElement = findElement("id", "username");
		enterText(userNameElement, username, "username");

		logger.info("valid username is entered");
		ExtentReport.logTestInfo("valid username is entered");
		logger.info("password field is cleared");
		ExtentReport.logTestInfo("password field is cleared");

		WebElement loginElement = findElement("id", "Login");
		clickElement(loginElement, "login");
		
		WebElement error = findElement("id", "error");
		waitToLoadWebPage(error);
		String actual = getTextFromElement(error);

		assertEquals(actual, expected);
		logger.info("TestCase1 is passed");
		ExtentReport.logTestInfo(" errorlogin method ended");
		

	}

	@Test
	public void logIn_to_Salesforce() {
		String expectedPageTitle = "Home Page ~ Salesforce - Developer Edition";

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");

		String username = ob.getPropertyData("login.valid.usename");
		WebElement userNameElement = findElement("id", "username");
		enterText(userNameElement, username, "username");

		logger.info("valid username is entered in username Field");
		ExtentReport.logTestInfo("valid username is entered in username Field");

		String password = ob.getPropertyData("login.valid.password");
		WebElement passwordElement = findElement("id", "password");
		enterText(passwordElement, password, "password");
		logger.info("valid password is entered in password field");
		ExtentReport.logTestInfo("valid password is entered in password field");

		WebElement loginElement = findElement("id", "Login");
		clickElement(loginElement, "login");
		

		WebElement LoggedInUser = findElement("id", "userNav");
		waitToLoadWebPage(LoggedInUser);

		String actual = driver.getTitle();
		assertEquals(actual, expectedPageTitle);
		logger.info("Testcase2 is passed ");
		ExtentReport.logTestInfo(" logIn_to_Salesforce method ended");
		

	}

	@Test
	public void checkRememberMe() {

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");

		String username = ob.getPropertyData("login.valid.usename");
		String expected = username;
		WebElement userNameElement = findElement("id", "username");
		enterText(userNameElement, username, "username");
		logger.info("Valid username is entered in username field");
		ExtentReport.logTestInfo("Valid username is entered in username field");

		String password = ob.getPropertyData("login.valid.password");
		WebElement passwordElement = findElement("id", "password");
		enterText(passwordElement, password, "password");
		logger.info("valid password is entered in password field");
		ExtentReport.logTestInfo("valid password is entered in password field");

		WebElement remElement = findElement("id", "rememberUn");
		clickElement(remElement, "Remember me checkbox");
		

		WebElement loginElement = findElement("id", "Login");
		clickElement(loginElement, "login");
		
		WebElement LoggedInUser = findElement("id", "userNav");
		clickElement(LoggedInUser, "usermenu");
		

		WebElement logout =findElement("xpath", "//a[@title='Logout']");
		clickElement(logout, "logout");
		

		WebElement remUserName = findElement("id", "username_container");
		waitToLoadWebPage(remUserName);
		

		String actual = remUserName.getText();
		assertEquals(actual, expected);
		logger.info("TestCase3 is passed");
		ExtentReport.logTestInfo(" checkRememberMe method ended");
		

	}

	@Test
	public void forgetPasswordTest() {
		String expectedTitle = "Check Your Email | Salesforce";

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");
		String username = ob.getPropertyData("login.valid.usename");

		WebElement forgetpassword=findElement("id", "forgot_password_link");
		clickElement(forgetpassword, "forgetpassword");
		

		findElement("id", "un").sendKeys(username);
		logger.info("username is entered in username field");
		ExtentReport.logTestInfo("username is entered in username field");
		findElement("id", "continue").click();

		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
		logger.info("TestCase4A is passed");
		ExtentReport.logTestInfo(" forgetPasswordTest method ended");
		

	}

	@Test
	public void LogInErrorWithInvalidInputs() {
		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");

		String username = ob.getPropertyData("login.invalid.usename");
		WebElement userNameElement = findElement("id", "username");
		enterText(userNameElement, username, "username");
		logger.info("invalid username is entered in username field");
		ExtentReport.logTestInfo("invalid username is entered in username field");

		String password = ob.getPropertyData("login.invalid.password");
		WebElement passwordElement = findElement("id", "password");
		enterText(passwordElement, password, "password");
		logger.info("invalid password is entered in password field");
		ExtentReport.logTestInfo("invalid password is entered in password field");

		WebElement loginElement = findElement("id", "Login");
		clickElement(loginElement, "login");
		
		String actual = findElement("id", "error").getText();
		assertEquals(actual, expected);
		logger.info("TestCase4B is passed");
		ExtentReport.logTestInfo(" LogInErrorWithInvalidInputs method ended");

	}

}
