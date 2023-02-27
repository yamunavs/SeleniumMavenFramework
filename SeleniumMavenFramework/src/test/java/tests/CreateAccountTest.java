package tests;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.utilityclasses.PropertiesUtilities;

import base.BaseTest;

@Listeners(com.utilityclasses.TestEventListenersUtility.class)
public class CreateAccountTest extends BaseTest {
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
	public void AccountCreateTest() {
		login();
		WebElement accountslink = findElement("xpath", "//a[@title='Accounts Tab']");
		clickElement(accountslink, "accountslink");
		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();

		WebElement newButton = findElement("xpath", "//input[@title='New']");
		waitToLoadWebPage(newButton);
		clickElement(newButton, "newButton");

		WebElement accountName = findElement("xpath", "//input[@id='acc2']");
		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("createAccountDataProperties");
		String accName = ob.getPropertyData("createAccount.accName")
				+ new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		enterText(accountName, accName, "accName");
		WebElement type = findElement("xpath", "//select[@id='acc6']");
		selectFromDropDown(type, "Technology Partner");

		WebElement customerPriority = findElement("id", "00NDm000001kTWq");
		selectFromDropDown(customerPriority, "High");

		WebElement saveButton = findElement("xpath", "//input[@title='Save']");
		clickElement(saveButton, "saveButton");
		ThreadSleep(6000);

	}

	@Test
	public void CreateNewViewTest() {
		login();
		WebElement accountslink = findElement("xpath", "//a[@title='Accounts Tab']");
		clickElement(accountslink, "accountslink");
		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();
		WebElement createNewView = findElement("xpath", "//a[contains(text(),'Create New View')]");
		clickElement(createNewView, "createNewView");

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("createAccountDataProperties");
		String viewName = ob.getPropertyData("createNewView.viewName")
				+ new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		String uniquename = ob.getPropertyData("createNewView.uniqueName");

		WebElement ViewName = findElement("xpath", "//input[@id='fname']");
		waitToLoadWebPage(ViewName);
		enterText(ViewName, viewName, "viewName");
		WebElement Uniquename = findElement("xpath", "//input[@id='devname']");
		enterText(Uniquename, uniquename, "createNewView");
		WebElement saveButton = findElement("xpath", "//input[@title='Save']");
		clickElement(saveButton, "saveButton");

		WebElement viewDropdown = findElement("xpath", "//select[@class='title']");
		Select select = new Select(viewDropdown);
		String currentOption = select.getFirstSelectedOption().getText();
		assertEquals(viewName, currentOption);
	}

//
	@Test
	public void editViewTest() {
		CreateNewViewTest();

		WebElement viewDropdown = findElement("xpath", "//select[@class='title']");
		Select select = new Select(viewDropdown);
		String currentOption = select.getFirstSelectedOption().getText();

		waitToLoadWebPage(viewDropdown);
		selectFromDropDown(viewDropdown, currentOption);
		WebElement edit = findElement("xpath", "//a[text()='Edit']");
		clickElement(edit, "editButton");

		String viewName = "user" + new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		WebElement ViewName = findElement("xpath", "//input[@id='fname']");
		enterText(ViewName, viewName, "viewname");

		String uniqueName = "user" + new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		WebElement UniqueName = findElement("id", "devname");
		enterText(UniqueName, uniqueName, "unique");

		WebElement filterField = findElement("xpath", "//select[@id='fcol1']");
		selectFromDropDown(filterField, "Account Name");
		WebElement operator = findElement("xpath", "//select[@id='fop1']");
		selectFromDropDown(operator, "contains");
		WebElement value = findElement("xpath", "//input[@id='fval1']");
		value.sendKeys("a");
		WebElement saveButton = findElement("xpath", "//input[@title='Save']");
		saveButton.click();
		//
		WebElement viewDropDownafterChange = findElement("id", "00BDm000000U7Cg_listSelect");
		waitToLoadWebPage(viewDropDownafterChange);
		Select select1 = new Select(viewDropDownafterChange);
		String changedViewName = select.getFirstSelectedOption().getText();
		assertEquals(viewName, changedViewName);

	}

	@Test
	public void mergeAccountTest() {

		login();
		WebElement accountslink = findElement("xpath", "//a[@title='Accounts Tab']");
		clickElement(accountslink, "accountslink");
		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();
		WebElement mergeAccount = findElement("xpath", "//a[contains(text(),'Merge Accounts')]");
		clickElement(mergeAccount, "mergeAccount");
		WebElement searchAcc = findElement("xpath", "//input[@id='srch']");
		enterText(searchAcc, "user", "searchACc");
		WebElement findAcc = findElement("xpath", "//input[@value='Find Accounts']");
		findAcc.click();
		WebElement firstAccCheckBox = findElement("xpath", "//input[@id='cid0']");
		selectCheckbox(firstAccCheckBox);

		WebElement secondAccCheckBox = findElement("xpath", "//input[@id='cid1']");
		selectCheckbox(secondAccCheckBox);
		WebElement nextbutton = findElement("xpath", "//input[@value=' Next ']");
		nextbutton.click();
		WebElement accNameRadio = findElement("xpath", "//tbody/tr[4]/td[2]//child::input");
		waitToLoadWebPage(accNameRadio);
		clickElement(accNameRadio, "accountRadioButton");

		String expected = findElement("xpath", "//tbody/tr[4]/td[2]//child::label").getText();

		WebElement mergeButton = findElement("xpath", "//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]");
		waitToLoadWebPage(mergeButton);
		mergeButton.click();
		driver.switchTo().alert().accept();

		WebElement recentAccountName = findElement("xpath", "//tbody/tr[2]/th[1]//child::a");
		String actual = recentAccountName.getText();
		assertEquals(actual, expected);

	}
@Test
	public void createAccountReportTest() {
		login();
		WebElement accountslink = findElement("xpath", "//a[@title='Accounts Tab']");
		clickElement(accountslink, "accountslink");

		WebElement dialogBoxClose = findElement("id", "tryLexDialogX");
		dialogBoxClose.click();

		WebElement accountsWithLastactivity = findElement("xpath",
				"//a[contains(text(),'Accounts with last activity > 30 days')]");
		clickElement(accountsWithLastactivity, "accountsWithLastactivity");

		WebElement fromCalenderIcon = findElement("xpath", "//img[@id='ext-gen152']");
		clickElement(fromCalenderIcon, "fromCalenderIcon");

		WebElement today = findElement("xpath", "//table[@id='ext-comp-1111']");
		clickElement(today, "todayButton");

		WebElement tocalenderIcon = findElement("xpath", "//img[@id='ext-gen154']");
		clickElement(tocalenderIcon, "tocalenderIcon");

		WebElement todayButton = findElement("xpath", "//*[@id=\"ext-gen292\"]");
		clickElement(todayButton, "todayButton");

		WebElement saveButton = findElement("xpath", "//button[@id='ext-gen49']");
		clickElement(saveButton, "saveButton");

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("createAccountDataProperties");
		String ReportName = ob.getPropertyData("createAccReport.reportName")
				+ new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		WebElement reportName = findElement("xpath", "//input[@id='saveReportDlg_reportNameField']");
		enterText(reportName, ReportName, "reportname");

		String uniqueName = ob.getPropertyData("createAccReport.uniqueName")
				+ new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		WebElement reportUniqueName = findElement("xpath", "//input[@id='saveReportDlg_DeveloperName']");
		enterText(reportUniqueName, uniqueName, "reportUniqueName");

		WebElement saveAndRun = findElement("xpath", "//*[@id=\"dlgSaveAndRun\"]");
		clickElement(saveAndRun, "saveAndRun");
	}

}
