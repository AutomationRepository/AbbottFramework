
package abbottLibreViewPages;

/**
 * @author Neha
 *
 */

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import abbottLibreViewSetup.DriverFactory;
import abbottLibreViewSetup.MyLogger;
import utilities.CommonEngine;
import utilities.PropertiesOperations;

public class ChooseCountryLanguagePage {

	// Page Object
	By modal_PopUpWindow = By.xpath("//parent::div[@id='countryModal']//following::div[@class='modal-dialog']");
	By dropdown_country = By.id("countrySelect");
	By dropdown_language = By.id("languageSelect");
	By button_submit = By.id("modalSubmit");
	By button_closeModalWindow = By.id("modalClose");

	// select the country and language
	public void setCountryAndLanguage(String country, String language) {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		MyLogger.info("implicit wait of 20 seconds completed");
		CommonEngine.selectDropDownValue(DriverFactory.getDriver().findElement(By.id("countrySelect")), country);
		MyLogger.info("country from the dropdown is selected sucessfully");
		CommonEngine.selectDropDownValue(DriverFactory.getDriver().findElement(By.id("languageSelect")), language);
		MyLogger.info("language from the dropdown is selected sucessfully");

	}

	// click on submit on modal
	public void clickOnSubmit() {
		DriverFactory.getDriver().findElement(button_submit).click();
		MyLogger.info("submit for the modal has be completed sucessfully");
	}

	// close the modal that is used for selecting country and language
	public void closeLanguageWindow() {
		DriverFactory.getDriver().findElement(button_closeModalWindow).click();
		MyLogger.info("closed modal by clicking on righ hand top corner");
	}

	//
	public void setCountryAndLanguage() throws Exception {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		MyLogger.info("implicit wait of 20 seconds completed");
		CommonEngine.selectDropDownValue(DriverFactory.getDriver().findElement(By.id("countrySelect")),
		PropertiesOperations.getPropertyValueByKey("differentCountry"));
		MyLogger.info("country from the dropdown is selected sucessfully");
		CommonEngine.selectDropDownValue(DriverFactory.getDriver().findElement(By.id("languageSelect")),
		PropertiesOperations.getPropertyValueByKey("differentLanguage"));
		MyLogger.info("language from the dropdown is selected sucessfully");

	}

}
