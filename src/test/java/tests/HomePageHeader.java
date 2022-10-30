package tests;

import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.SkipException;
/**
 *
 *  @author Neha
 *
*/

import org.testng.annotations.Test;
import abbottLibreViewPages.ChooseCountryLanguagePage;
import abbottLibreViewPages.CookiePopupPage;
import abbottLibreViewPages.HomePage;
import abbottLibreViewSetup.MyLogger;
import abbottLibreViewSetup.SetUp;
import utilities.CommonEngine;
import utilities.JsonDataReader;

public class HomePageHeader extends SetUp {

	HomePage homePage;
	CookiePopupPage cookiePage;
	ChooseCountryLanguagePage chooseCAL;

	public HomePageHeader() {
		cookiePage = new CookiePopupPage();
		homePage = new HomePage();
		chooseCAL = new ChooseCountryLanguagePage();
	}

	// Validate patient is loaded by default and link(patient) is selected
	@Test(dataProviderClass = JsonDataReader.class, dataProvider = "dataProvider1", description = "validate patient is loaded by default and link(patient) is selected")
	public void validateDefaultLoading(HashMap<String, String> hashMap) {
		if (hashMap.get("country").equals("France (FR)")) {
			throw new SkipException("Test skipped as data is:" + hashMap.get("country"));
		} else {
			MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
			chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
			chooseCAL.clickOnSubmit();
			cookiePage.acceptCookieWindow();
			homePage.isElementHighlighted("patient");
			CommonEngine.storeTestVariable("country", hashMap.get("country"));
			MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());
		}
	}

	// Validate that on selection of Franch/French link for patient and professional
	// is not visible
	@Test(dataProviderClass = JsonDataReader.class, dataProvider = "dataProvider1", description = "validate that on selection of Franch/French link for patient and/professional is not visible")
	public void validateLinkNotPresentForFrance(HashMap<String, String> hashMap) {
		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		if (!hashMap.get("country").equals("France (FR)")) {
			throw new SkipException("Test skipped as data is:" + hashMap.get("country"));
		} else {
			chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
			chooseCAL.clickOnSubmit();
			cookiePage.acceptCookieWindow();
			homePage.validateBothElementNotPresent();
			CommonEngine.storeTestVariable("country", hashMap.get("country"));
			CommonEngine.storeTestVariable("country", hashMap.get("country"));
		}
		MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());
	}

	// Validate that on clicking professional new window opens and
	// link(professional) is selected
	@Test(dataProviderClass = JsonDataReader.class, dataProvider = "dataProvider1", description = "Validate that on clicking professional new window opens and link(professional) is selected")
	public void validateProfessionalLink(HashMap<String, String> hashMap) {
		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		if (hashMap.get("country").equals("France (FR)")) {
			throw new SkipException("Test skipped as data is:" + hashMap.get("country"));
		} else {
			chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
			chooseCAL.clickOnSubmit();
			cookiePage.acceptCookieWindow();
			homePage.navigateToProfPage();
			homePage.isElementHighlighted("Professional");
			homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
			homePage.closeAllWindows();
		}
		MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());
	}

	// Validate correct data is displayed in header on selection
	@Test(dataProviderClass = JsonDataReader.class, dataProvider = "dataProvider1", description = "Validate correct data is displayed in header on selection")
	public void validateSelection(HashMap<String, String> hashMap) {
		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
		chooseCAL.clickOnSubmit();
		cookiePage.acceptCookieWindow();
		homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
		MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());
	}

	// Validate nothing changes on the home page on opening, and closing modal
	// without changes
	@Test(dataProviderClass = JsonDataReader.class, dataProvider = "dataProvider1", description = "Validate correct data is displayed in header on selection")
	public void validateSelectionOnClosure(HashMap<String, String> hashMap) {

		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
		chooseCAL.clickOnSubmit();
		cookiePage.acceptCookieWindow();
		homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
		homePage.selectLanguage();
		chooseCAL.closeLanguageWindow();
		homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
		MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());

	}

	// Validate nothing changes on the home page on opening modal, inputting values
	// in dropdown, and closing without submit
	@Test(dataProviderClass = JsonDataReader.class, dataProvider = "dataProvider1", description = "Validate nothing changes on the home page on opening modal, inputting values in dropdown, and closing without submit")
	public void validateSelectionWithoutSubmit(HashMap<String, String> hashMap) throws Exception {

		if (hashMap.get("country").equals("France (FR)")) {
			MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
			chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
			chooseCAL.clickOnSubmit();
			cookiePage.acceptCookieWindow();
			homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
			homePage.selectLanguage();
			chooseCAL.setCountryAndLanguage();
			chooseCAL.closeLanguageWindow();
			homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
			MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());
		} else {
			MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
			chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
			chooseCAL.clickOnSubmit();
			cookiePage.acceptCookieWindow();
			homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
			homePage.selectLanguage();
			chooseCAL.setCountryAndLanguage();
			chooseCAL.closeLanguageWindow();
			homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
			MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());
		}

	}

	// Validate that on selecting the same values from header as during the page
	// load, nothing changes on the home page
	@Test(dataProviderClass = JsonDataReader.class, dataProvider = "dataProvider1", description = "Validate that on selecting the same values from header as during the page load, nothing changes on the home page")
	public void validateSameValueSelection(HashMap<String, String> hashMap) {
		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());

		chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
		chooseCAL.clickOnSubmit();
		cookiePage.acceptCookieWindow();
		homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));
		homePage.selectLanguage();
		chooseCAL.setCountryAndLanguage(hashMap.get("country"), hashMap.get("language"));
		chooseCAL.clickOnSubmit();
		homePage.isSelectionCorrect(hashMap.get("country"), hashMap.get("language"));

		MyLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());

	}
}
