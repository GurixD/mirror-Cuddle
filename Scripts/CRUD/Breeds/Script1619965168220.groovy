import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import util.Util

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/')

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Login'))

WebUI.setText(findTestObject('Object Repository/Page_Please sign in/input_Username_username'), 'florian.feuillade@he-arc.ch')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Please sign in/input_Password_password'), 'V2rEZwedbz9dlLHBWTk7CA==')

WebUI.sendKeys(findTestObject('Object Repository/Page_Please sign in/input_Password_password'), Keys.chord(Keys.ENTER))

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Dashboard'))

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Manage Breeds'))

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Add breeds'))

WebUI.setText(findTestObject('Object Repository/Page_Cuddle/input_Name_name'), 'Maine Coon')

WebUI.click(findTestObject('Object Repository/Page_Cuddle/input'))

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Edit'))

WebUI.click(findTestObject('Object Repository/Page_Cuddle/tr_Name'))

WebUI.setText(findTestObject('Object Repository/Page_Cuddle/input_Name_name'), 'Sphynx')

WebUI.click(findTestObject('Object Repository/Page_Cuddle/inputEdit'))

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Delete'))

WebUI.click(findTestObject('Object Repository/Page_Cuddle/inputYes'))

WebUI.closeBrowser()

