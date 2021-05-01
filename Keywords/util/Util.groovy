package util
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

public class Util {
	public static void jsClick(final TestObject testObject) {
		WebElement element = WebUiCommonHelper.findWebElement(testObject, 30);
		WebDriver driver = DriverFactory.getWebDriver();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}
}