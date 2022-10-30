/**
 *
 */
package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import abbottLibreViewSetup.DriverFactory;

/**
 * @author Neha
 *
 */
public class CommonEngine {

	static String parentHandlerValue = "";

	// validate presence of element
	public static boolean isElementPresent(By locator) {
		try {
			DriverFactory.getDriver().findElement(locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	// select dropdown value using javascript executor
	public static void selectDropDownValue(WebElement element, String val) {
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
				"var select = arguments[0]; " + "for(var i = 0; i < select.options.length; i++)"
						+ "{ if(select.options[i].text == arguments[1])" + "{ select.options[i].selected = true; } }",
				element, val);
	}

	// close all the windows at ones
	public static void closeAllOpenWindows() {
		DriverFactory.getDriver().quit();
	}

	// get parent window handler
	public static String getParentWindowHandler() {
		return parentHandlerValue;
	}

	// set the value of the parent handler object
	public static void setParentWindowHandler() {
		parentHandlerValue = DriverFactory.getDriver().getWindowHandle();
	}

	public static void storeTestVariable(String key, String value) {
		PropertiesOperations.setPropertyValueByKey(key, value);
	}
}
