/**
 *
 */
package abbottLibreViewPages;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import abbottLibreViewSetup.DriverFactory;
import abbottLibreViewSetup.MyLogger;
import utilities.CommonEngine;

/**
 * @author Neha
 *
 */
public class HomePage {

	By link_patHeader = By.id("patHeaderLink");
	By link_proHeader = By.id("proHeaderLink");
	By link_logIn = By.xpath("//div[@id='marketingLinks']/a[text()='Log In']");
	By link_signUp = By.xpath("//div[@id='marketingLinks']/a[text()='Sign Up']");
	By span_languageSelection = By.xpath("//div[@id='languageSelectWrapperHomeHeader']//following::span");

	// Page Variable
	WebDriverWait wait;
	static String val;

	// click on patients link
	public void selectPatient() {
		DriverFactory.getDriver().findElement(link_patHeader).click();
		MyLogger.info("click on patient tab sucessful");
	}

	// click on Professional link
	public void selectProfessional() {
		DriverFactory.getDriver().findElement(link_proHeader).click();
		MyLogger.info("click on professional tab sucessful");
	}

	// click on language selection option
	public void selectLanguage() {
		DriverFactory.getDriver().findElement(span_languageSelection).click();
		MyLogger.info("click on top right corner where the language selection is mentioned");
	}

	// validate that the patient and professional links are present in header
	public void validateBothElementPresent() {
		Assert.assertEquals(true, DriverFactory.getDriver().findElement(link_patHeader).isDisplayed());
		Assert.assertEquals(true, DriverFactory.getDriver().findElement(link_proHeader).isDisplayed());
		MyLogger.info("link for both patient and professional is present");
	}

	// validate that the patient and professional links are present in header
	public void validateBothElementNotPresent() {
		Assert.assertEquals(false, CommonEngine.isElementPresent(link_patHeader));
		Assert.assertEquals(false, CommonEngine.isElementPresent(link_patHeader));
		MyLogger.info("link for both patient and professional is not present");
	}

	// navigate to professional Tab
	public void navigateToProfPage() {
		MyLogger.info("current value of the window stored");
		CommonEngine.setParentWindowHandler();
		selectProfessional();
		MyLogger.info("the professional tag is clicked");
		try {
			MyLogger.info("Static wait started");
			Thread.sleep(20000);
			MyLogger.info("Static wait completed");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set newTab = DriverFactory.getDriver().getWindowHandles();
		Iterator<String> itr = newTab.iterator();
		System.out.println("Size" + newTab.size());
		while (itr.hasNext()) {
			System.out.println(itr.next());
			String val = itr.next();
			if (!val.equals(CommonEngine.getParentWindowHandler())) {
				DriverFactory.getDriver().switchTo().window(val);
				MyLogger.info("Focus moved from parent window to professional tab");
			}
		}
	}

	// close all the currently opened windows
	public void closeAllWindows() {
		CommonEngine.closeAllOpenWindows();
		MyLogger.info("All open tabs/windows are closed");
	}

	// Validate that the Css property is activated on selection
	public void isElementHighlighted(String element) {
		if (element.equals("patient")) {
			wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(link_patHeader));
			Assert.assertEquals("rgba(72, 145, 197, 1)",
					DriverFactory.getDriver().findElement(link_patHeader).getCssValue("color"));
			MyLogger.info("Element is highlighted with rgba(72, 145, 197, 1)");
		}
		if (element.equals("Professional")) {
			wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(link_proHeader));
			Assert.assertEquals(DriverFactory.getDriver().findElement(link_proHeader).getCssValue("color"),
					"rgba(72, 145, 197, 1)");
			MyLogger.info("Element is highlighted with rgba(72, 145, 197, 1)");
		}
	}

	// Validate that the country and language selected are correctly displayed on
	// the screen
	public void isSelectionCorrect(String country, String language) {

		// String val;
		try {
			val = DriverFactory.getDriver().findElement(span_languageSelection).getText();
		} catch (StaleElementReferenceException e) {
			wait.until(ExpectedConditions.refreshed(
					ExpectedConditions.stalenessOf(DriverFactory.getDriver().findElement(span_languageSelection))));
		}

		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher matcher = pattern.matcher(country);
		if (matcher.find()) {
			if (!language.equals("French") && !country.equals("France")) {
				System.out.println(val + "and" + matcher.group(1) + ": " + language);
				Assert.assertEquals(matcher.group(1) + ": " + language, val);
				MyLogger.info("current combinaton is" + val);
			} else {
				System.out.println(val + "and" + matcher.group(1) + ": Français");
				Assert.assertEquals(matcher.group(1) + ": Français", val);
				MyLogger.info("current combinaton is" + val);
			}
		} else {
			Assert.fail("The Value specified doesn't match with the country selected");
			MyLogger.info("combination present on the screen :" + val + "is incorrect");
		}
	}

}
