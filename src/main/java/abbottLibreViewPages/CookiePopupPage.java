/**
 *
 */
package abbottLibreViewPages;

/**
 * @author Neha
 *
 */

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import abbottLibreViewSetup.DriverFactory;
import abbottLibreViewSetup.MyLogger;

public class CookiePopupPage {

	// Page Object
	By link_acceptCookieWindow = By.id("truste-consent-button");
	By banner_cookiePopUp = By.xpath("//div[@id='consent-banner']");

	// Page Variable
	WebDriverWait wait;

	// validate that the cookie pop-up is present
	public boolean validatePopupAppears() {
		MyLogger.info("Validate if the cookie pop-up is present");
		return DriverFactory.getDriver().findElement(banner_cookiePopUp).isDisplayed();

	}

	// Accept cookies
	public void acceptCookieWindow() {
		if (validatePopupAppears()) {
			wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(link_acceptCookieWindow)).click();
			MyLogger.info("Cookie Pop-up was visible and is closed now");
		} else {
			MyLogger.info("Cookie Pop-up was not visible");
		}
	}

}
