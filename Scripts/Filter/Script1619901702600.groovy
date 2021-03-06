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

WebUI.maximizeWindow()

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Adopt'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Cuddle/select_-- select a species --             C_4f5b6a'), 
    '2', true)

WebUI.click(findTestObject('Object Repository/Page_Cuddle/button_search'))

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Login'))

WebUI.setText(findTestObject('Object Repository/Page_Please sign in/input_Username_username'), 'antoine.lestrade@he-arc.ch')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Please sign in/input_Password_password'), 'V2rEZwedbz9dlLHBWTk7CA==')

WebUI.click(findTestObject('Object Repository/Page_Please sign in/button_Sign in'))

Util.jsClick(findTestObject('Object Repository/Page_Cuddle/a_Dashboard'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Cuddle/select_-- select a species --              _d5172c'), 
    '1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Cuddle/select_-- select a breed --                _129fe6'), 
    '2', true)

WebUI.click(findTestObject('Object Repository/Page_Cuddle/input_Has a treatment_hasTreatment'))

WebUI.click(findTestObject('Object Repository/Page_Cuddle/button_search'))

WebUI.closeBrowser()

