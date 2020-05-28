package core.generic;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.face.pageFactory.TouchActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidKeyMetastate;



public class WrapperFunctions {
	private StepBase objStepBase = new StepBase();
	protected static WebDriver driver = null;
	protected static AppiumDriver appiumDriver = null;
	private WebDriverWait appiumDriverWait;
	private static StepBase2 objStepBase2 = new StepBase2();

	/**
	 * @Method: waitForElementToBeClickable
	 * @Description: This is wrapper method wait for element to be click able
	 * @param locator
	 *            - By identification of element
	 * @param waitInSeconds
	 *            - wait time
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 13 Dec 2017 @Modified Date:
	 */
	public void waitForElementToBeClickable(By locator, int waitInSeconds) {
		try {
			Wait<WebDriver> wait = new WebDriverWait(objStepBase.getDriver(), waitInSeconds)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @Method: waitForElementPresence
	 * @Description: This is wrapper method wait for element presence
	 * @param locator
	 *            - By identification of element
	 * @param waitInSeconds
	 *            - wait time
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 13 Dec 2017 @Modified Date:
	 */
	public void waitForElementPresence(By locator, int waitInSeconds) {
		try {
			Wait<WebDriver> wait = new WebDriverWait(objStepBase.getDriver(), waitInSeconds)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @Method: checkElement_Existance
	 * @Description: This is wrapper method to check the existence of any web
	 *               element on the page
	 * @param locator
	 *            - By identification of element
	 * @param waitInSeconds
	 *            - wait time
	 * @return - true if element present
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 13 Dec 2017 @Modified Date:
	 */
	public boolean checkElementExistence(By locator, int... sTimeInSecond) {
		try {
			WebDriverWait wait = null;
			if (sTimeInSecond.length > 0) {
				wait = new WebDriverWait(objStepBase.getDriver(), sTimeInSecond[0]);
			} else {
				wait = new WebDriverWait(objStepBase.getDriver(), 10);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			WebElement ele = objStepBase.getDriver().findElement(locator);
			return ele.isDisplayed();
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: click
	 * @Description: This is wrapper method to click on web element
	 * @param locator
	 *            - By identification of element
	 * @return - true if click successful
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 15 Dec 2017 @Modified Date:
	 */
	public boolean click(By locator) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try {
			JavascriptExecutor executor = (JavascriptExecutor) objStepBase.getDriver();
			executor.executeScript("arguments[0].click();", webElement);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: doubleClick
	 * @Description: This is wrapper method used for doubleClick on element
	 * @param locator
	 *            - By identification of element
	 * @return - true if double click successful
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 15 Dec 2017 @Modified Date:
	 */
	public boolean doubleClick(By locator) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try {
			Actions actionBuilder = new Actions(objStepBase.getDriver());
			actionBuilder.doubleClick(webElement).build().perform();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: setText
	 * @Description: This is wrapper method to set text for input element
	 * @param locator
	 *            - By identification of element
	 * @param fieldValue
	 *            - field value as string
	 * @return - true if text entered successfully
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 15 Dec 2017 @Modified Date:
	 */
	public boolean setText(By locator, String fieldValue) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try {
			// replace the text
			JavascriptExecutor executor = (JavascriptExecutor) objStepBase.getDriver();
			executor.executeScript("arguments[0].click();", webElement);
			webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			webElement.sendKeys(Keys.DELETE);
			webElement.clear();
			webElement.sendKeys(fieldValue);
			// webElement.sendKeys(Keys.TAB);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: getText
	 * @Description: This is wrapper method to get text form input elements
	 * @param locator
	 *            - By identification of element
	 * @param textBy
	 *            - get text by value attribute (set textBy as value)/ by visible
	 *            text (set textBy as text)
	 * @return - text as string
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 18 Dec 2017 @Modified Date:
	 */
	public String getText(By locator, String textBy) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try {
			String strText = "";
			if (textBy.equals("value"))
				strText = webElement.getAttribute("value");
			else if (textBy.equalsIgnoreCase("text"))
				strText = webElement.getText();
			return strText;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method: selectCheckBox
	 * @Description: This is wrapper method select/deselect checkbox
	 * @param locator
	 *            - By identification of element
	 * @param status
	 *            - select/deselect
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 18 Dec 2017 @Modified Date:
	 */
	public boolean selectCheckBox(By locator, boolean status) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try {
			if (webElement.getAttribute("type").equals("checkbox")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				return true;
			} else
				return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: isCheckBoxSelected
	 * @Description: This is wrapper checkbox is selected or not
	 * @param locator
	 *            - By identification of element
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 18 Dec 2017 @Modified Date:
	 */
	public boolean isCheckBoxSelected(By locator, boolean status) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		boolean state = false;
		try {
			if (webElement.getAttribute("type").equals("checkbox"))
				state = webElement.isSelected();

			return state;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: selectRadio
	 * @Description: This is wrapper method select/deselect radio button
	 * @param locator
	 *            - By identification of element
	 * @param status
	 *            - select/deselect
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 19 Dec 2017 @Modified Date:
	 */
	public boolean selectRadioButton(By locator, boolean status) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try {
			if (webElement.getAttribute("type").equals("radio")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				return true;
			} else
				return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: mouseHover
	 * @Description: This is wrapper method used for Mouse Hovering to the element
	 * @param locator
	 *            - By identification of element
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 19 Dec 2017 @Modified Date:
	 */
	public boolean mouseHover(By locator) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try {
			Actions actionBuilder = new Actions(objStepBase.getDriver());
			actionBuilder.moveToElement(webElement).build().perform();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: switchToWindowUsingTitle
	 * @Description: This is wrapper method used switch to window using the given
	 *               title
	 * @param locator
	 *            - Window title
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 19 Dec 2017 @Modified Date:
	 */
	public boolean switchToWindowUsingTitle(String windowTitle) {
		try {
			String mainWindowHandle = objStepBase.getDriver().getWindowHandle();
			Set<String> openWindows = objStepBase.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					String window = objStepBase.getDriver().switchTo().window(windows).getTitle();
					if (windowTitle.equals(window))
						return true;
					else
						objStepBase.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: selectDropdown
	 * @Description: This is wrapper method select drop down element
	 * @param locator
	 *            - By identification of element
	 * @param option
	 *            - drop down element (user may specify text/value/index)
	 * @param selectType
	 *            - select dorp down element by Text/Value/Index
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 19 Dec 2017 @Modified Date:
	 */
	public boolean selectDropDownOption(By locator, String option, String... selectType) {
		try {
			waitForElementPresence(locator, 10);
			WebElement webElement = objStepBase.getDriver().findElement(locator);
			Select sltDropDown = new Select(webElement);

			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equals("Value"))
					sltDropDown.selectByValue(option);
				else if (selectType[0].equals("Text"))
					sltDropDown.selectByVisibleText(option);
				else if (selectType[0].equals("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));

				return true;
			} else {
				// Web elements from dropdown list
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
					} else
						iIndex++;
					if (blnOptionAvailable)
						break;
				}
				if (blnOptionAvailable)
					return true;
				else
					return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: getSelectedValueFormDropDown
	 * @Description: This is wrapper method select drop down element
	 * @param locator
	 *            - By identification of element
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 20 Dec 2017 @Modified Date:
	 */
	public String getSelectedValueFormDropDown(By locator) {
		try {
			waitForElementPresence(locator, 10);
			Select selectDorpDown = new Select(objStepBase.getDriver().findElement(locator));
			String selectedDorpDownValue = selectDorpDown.getFirstSelectedOption().getText();
			return selectedDorpDownValue;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}

	}

	/**
	 * @Method: selectRadioButtonForSpecificColumn
	 * @Description: This is wrapper method to select radio button from table with
	 *               respect to particular column content
	 * @param locator
	 *            - By identification of element (table with all rows)
	 * @param columnContent
	 *            - String column content
	 * @columnNumberForRadio - integer column number for radio button
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 20 Dec 2017 @Modified Date:
	 */
	public boolean selectRadioButtonForSpecificColumn(By locator, String columnContent, int columnNumberForRadio) {
		try {
			waitForElementPresence(locator, 10);
			List<WebElement> weResultTable = objStepBase.getDriver().findElements(locator);
			for (WebElement weRow : weResultTable) {
				List<WebElement> weColumns = weRow.findElements(By.xpath(".//td"));
				for (WebElement weColumn : weColumns) {
					if (weColumn.getText().trim().equals(columnContent)) {
						WebElement webElement = weRow
								.findElement(By.xpath(".//td['" + columnNumberForRadio + "']/input[@type='radio']"));
						JavascriptExecutor executor = (JavascriptExecutor) objStepBase.getDriver();
						executor.executeScript("arguments[0].click();", webElement);
					}
				}
			}
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: selectCheckBoxForSpecificColumn
	 * @Description: This is wrapper method to select check box from table with
	 *               respect to particular column content
	 * @param locator
	 *            - By identification of element (table with all rows)
	 * @param columnContent
	 *            - String column content
	 * @columnNumberForRadio - integer column number for radio button
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 20 Dec 2017 @Modified Date:
	 */
	public boolean selectCheckBoxForSpecificColumn(By locator, String columnContent, int columnNumberForRadio) {
		try {
			waitForElementPresence(locator, 10);
			List<WebElement> weResultTable = objStepBase.getDriver().findElements(locator);
			for (WebElement weRow : weResultTable) {
				List<WebElement> weColumns = weRow.findElements(By.xpath(".//td"));
				for (WebElement weColumn : weColumns) {
					if (weColumn.getText().trim().equals(columnContent))
						weRow.findElement(
								By.xpath(".//td['" + columnNumberForRadio + "']/span/input[@type='checkbox']")).click();
				}
			}
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: verifyTableContent
	 * @Description:
	 * @param locator
	 *            - By identification of element (table with all rows)
	 * @param columnHeader
	 *            - String column header
	 * @param columnContent
	 *            - String column content
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 22 Dec 2017 @Modified Date:
	 */
	public boolean verifyTableContent(By locator, String columnHeader, String columnContent) {
		Hashtable<String, String> dataColumnHeader = new Hashtable<String, String>();
		int intColumnNumber = 1;
		boolean blnverify = false;
		try {
			waitForElementPresence(locator, 10);
			WebElement weResultTable = objStepBase.getDriver().findElement(locator);

			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			for (WebElement weColumnHeader : weColumnsHeaders) {
				String strHeader = weColumnHeader.getText().trim();
				if (!strHeader.equals(""))
					dataColumnHeader.put(strHeader, String.valueOf(intColumnNumber));
				intColumnNumber++;
			}

			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				System.out.println(dataColumnHeader.get(columnHeader));
				WebElement weExceptedClm = weRow
						.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeader) + "]"));
				if (weExceptedClm.getText().trim().contains(columnContent))
					blnverify = true;
			}

			return blnverify;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: verifyTableContentAndCheckSelected
	 * @Description:
	 * @param locator
	 *            - By identification of element (table with all rows)
	 * @param columnHeader
	 *            - String column header
	 * @param columnContent
	 *            - String column content
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 22 Dec 2017 @Modified Date:
	 */
	public boolean verifyTableContentAndCheckSelected(By locator, String columnHeader, String columnContent,
			int checkboxColumnNumber) {
		Hashtable<String, String> dataColumnHeader = new Hashtable<String, String>();
		int intColumnNumber = 1;
		boolean blnverify = false;
		try {
			waitForElementPresence(locator, 10);
			WebElement weResultTable = objStepBase.getDriver().findElement(locator);

			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			for (WebElement weColumnHeader : weColumnsHeaders) {
				String strHeader = weColumnHeader.getText().trim();
				if (!strHeader.equals(""))
					dataColumnHeader.put(strHeader, String.valueOf(intColumnNumber));
				intColumnNumber++;
			}

			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weExceptedClm = weRow
						.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeader) + "]"));
				if (weExceptedClm.getText().trim().contains(columnContent)) {
					WebElement weCheckBox = weRow
							.findElement(By.xpath(".//td[" + checkboxColumnNumber + "]/span/input[@type='checkbox']"));
					boolean blnIsSelected = weCheckBox.isSelected();
					if (blnIsSelected) {
						blnverify = true;
					}
				}
			}
			return blnverify;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: view table details
	 * @Description:
	 * @param locator
	 *            - By identification of table element
	 * @Author: Digvijay Dusane(Aress) @Creation Date : 25 Dec 2017 @Modified Date:
	 */
	public boolean viewTableContent(By locator) {
		try {
			PrintStream myconsole = new PrintStream(new File("D://java.txt"));
			WebElement weResultTable = objStepBase.getDriver().findElement(locator);

			int intHeaderNumber = 1;
			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			System.out.println("************************* Table Header details *************");
			myconsole.print("************************* Table Header details *************");
			for (WebElement weColumnHeader : weColumnsHeaders) {
				System.out.println("Table Header * " + intHeaderNumber + " *--->" + weColumnHeader.getText());
				intHeaderNumber++;
			}

			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			int intRowNum = 1;
			System.out.println("************************* Table content details *************");
			for (WebElement weRow : weRows) {
				System.out.println("**********Row Number  " + intRowNum + " *************");
				int intClmNum = 1;
				List<WebElement> weClmns = weRow.findElements(By.xpath(".//td"));
				for (WebElement weClmn : weClmns) {
					System.out.println("Column Number---->" + intClmNum);
					System.out.println("Column Text-------->" + weClmn.getText());
					intClmNum++;
				}
				intRowNum++;
			}
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	public boolean navigateURL(String url) {
		System.out.println("*************  In navigate function********");
		objStepBase.getDriver().get(url);
		return false;
	}

	public void scrollDown() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) objStepBase.getDriver();
		jse.executeScript("window.scrollBy(0,400)", "");
	}

	/**
	 * @Method : sendKeyBoardKeys
	 * @Description : This is wrapper method is used to send keyboard keys
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            key - key name
	 * @return : - true if text entered successfully
	 * @author : Digvijay Dusane (Aress)
	 * @param waitInSeconds
	 */
	public boolean sendKeyBoardKeys(By locator, String key, int waitInSeconds) {
		try {
			waitForElementPresence(locator, waitInSeconds);
			WebElement webElement = objStepBase.getDriver().findElement(locator);
			if (key.equalsIgnoreCase("enter"))
				webElement.sendKeys(Keys.ENTER);
			if (key.equalsIgnoreCase("shift"))
				webElement.sendKeys(Keys.SHIFT);
			if (key.equalsIgnoreCase("arrow_up"))
				webElement.sendKeys(Keys.ARROW_UP);
			if (key.equalsIgnoreCase("tab"))
				webElement.sendKeys(Keys.TAB);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	public void selectTime() throws InterruptedException {
		objStepBase.getDriver().findElement(By.xpath("//input[contains(@fieldname, 'Time')]")).click();
		Thread.sleep(2000);
		WebElement hours = objStepBase.getDriver()
				.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/dl/dd[2]/div/span"));
		for (int i = 1; i <= 5; i++) {
			hours.sendKeys(Keys.ARROW_UP);
		}
		WebElement mins = objStepBase.getDriver()
				.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/dl/dd[3]/div/span"));
		for (int i = 1; i <= 20; i++) {
			mins.sendKeys(Keys.ARROW_UP);
		}
	}

	public void uploadPhoto(By locator, String filePath) {
		filePath = System.getProperty("user.dir") + "/src/main/resources/testData/testImage.jpg";
		WebElement uploadElement = objStepBase.getDriver().findElement(locator);
		System.out.println("****Started uploading");
		uploadElement.sendKeys(filePath);
		System.out.println("****Done uploading");
	}

	public void acceptDefult() {
		// WebElement uploadElement = objStepBase.getDriver().findElement(locator);
		objStepBase.getDriver().switchTo().alert().accept();

	}

	public void sleep() throws InterruptedException {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeExcel() throws InterruptedException {
		try {
			FileOutputStream file = new FileOutputStream("‪C:\\Users\\user\\Documents\\consoleoutput.xls");
			// File.write(file);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// <------------------------------------------------------------------------------------------------------------>
	// <------------------------------------------------------------------------------------------------------------>
	/*
	 * Appium Wrapper functions for Mobile
	 */

	// private static Properties objConfig = new Properties();
	// static {
	// try {
	// FileInputStream in = new FileInputStream(
	// System.getProperty("user.dir") + "/src/main/java/core/config.properties");
	// objConfig.load(in);
	// in.close();
	// } catch (Exception e) {
	// System.out.println("Step Failed - Unable to load config.properties file");
	// }
	// }

	/**
	 * @Description : This is wrapper method to check the mobile element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 * @author: Digvijay Dusane
	 */

	public boolean checkMobileElementDisplayed(By locator) {

		try {
			return appiumDriver.findElement(locator).isDisplayed();
		} catch (Exception exception) {
			System.out.println("Step Failed - Mobile element not found");
			return false;
		}

	}

	/**
	 * @author Martin
	 * @category Mobile methods
	 * @description Accept pop up method for android and ios.
	 * @createdDate 11-4-2018
	 */

	public void acceptMobileAlert() {

		try {
			StepBase2.appiumDriver.switchTo().alert().accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Step Failed - Unable to accept alert");

		}
	}

	/**
	 * @author Martin
	 * @category Mobile methods
	 * @description Dismiss alert pop up method for android and ios.
	 * @createdDate 11-4-2018
	 */

	public void dismissMobileAlert() {

		try {
			StepBase2.appiumDriver.switchTo().alert().dismiss();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Step Failed - Unable to dismiss alert");
		}
	}

	/**
	 * @author: Martin
	 * @category: Mobile function for swipe operation
	 * @param startX
	 *            Initial position of X coordinate
	 * @param startY
	 *            Initial position of y coordinate
	 * @param endX
	 *            Final position of X coordinate
	 * @param endY
	 *            Final position of y coordinate
	 * @param Duration
	 *            Time in milliseconds
	 * @creationDate: 11-4-2018
	 */

	public boolean swipeHorizontal(int startX, int startY, int endX, int endY, int Duration) {

		try {
			StepBase2.appiumDriver.swipe(startX, startY, endX, endY, Duration);
			return true;
		} catch (Exception e) {
			System.out.println("Step Failed - Unable to swipe horizontally");
			return false;
		}

	}

	/**
	 * @author: Martin
	 * @category Mobile function to swipe vertically
	 * @param fromX
	 *            - Initial position of X coordinate
	 * @param fromY
	 *            - Initial position of Y coordinate
	 * @param Duration
	 *            - Time in milliseconds
	 * @param toX
	 *            - Final position of X coordinate
	 * @param toY
	 *            - Final position of Y coordinate
	 * @Created date: 16-4-2018
	 */

	public boolean swipeVerticle(int fromX, int fromY, int Duration, int toX, int toY) {

		try {
			TouchAction action = new TouchAction((AndroidDriver) StepBase2.appiumDriver);
			action.press(fromX, fromY).waitAction(Duration).moveTo(toX, toY).release().perform();
			return true;
		} catch (Exception exception) {
			System.out.println("Step Failed- Failed To Swipe Vertically");
			return false;
		}
	}

	/**
	 * @author Martin
	 * @Description: Function is used to navigate back on ios and android devices
	 * @creationDate: 11-4-2018
	 */
	public void navigateBack() {

		try {
			StepBase2.appiumDriver.navigate().back();
		} catch (Exception e) {
			System.out.println("Step Failed - Unable to navigate back");
		}
	}

	public void selectEnter() {
		try {
			StepBase2.appiumDriver.getKeyboard().sendKeys(Keys.DOWN);
//			((Object) StepBase2.appiumDriver).pressKeyCode(AndroidKeyCode.SPACE, AndroidKeyMetastate.META_SHIFT_ON);
			Map<String, Object> EnterKeyEvent  = new HashMap<>();
			EnterKeyEvent.put("key", "Done"); 
//			StepBase2.appiumDriver.getKeyboard().pressKey("Done");
//			EnterKeyEvent.putIfAbsent("key", "6");
		} catch (Exception e) {
			System.out.println("Step Failed - Unable to click enter");
		}
	}
	
//	public void pressKeyAndGenerateIMEActionTest() {
//        Activity activity = new Activity(driver.getCurrentPackage(), ".text.KeyEventText");
//        driver.startActivity(activity);
//        driver.pressKey(new KeyEvent()
//                .withKey(AndroidKey.ENTER)
//                .withFlag(KeyEventFlag.SOFT_KEYBOARD)
//                .withFlag(KeyEventFlag.KEEP_TOUCH_MODE)
//                .withFlag(KeyEventFlag.EDITOR_ACTION));
//        final String state = driver.findElement(PRESS_RESULT_VIEW).getText();
//        // This event won't update the view
//        assertTrue(state.isEmpty());
//    }
		
	/**
	 * @author Martin
	 * @description Click on the given element
	 * @param locator
	 *            of the element to be clicked
	 * @return True if element is clicked else false
	 * @CreatedDate 11-4-2018
	 */

	// public boolean mobileClick(By locator) {
	// waitForElementPresence(locator, 10);
	// WebElement webElement = objStepBase2.android().findElement(locator);
	// try {
	// JavascriptExecutor executor = (JavascriptExecutor) objStepBase2.android();
	// executor.executeScript("arguments[0].click();", webElement);
	// return true;
	// } catch (Exception exception) {
	// System.out.println("Step Failed - Unable to perform click operation");
	// return false;
	// }
	//
	// }
	public boolean mobileClick(By locator) {

		MobileElement mobileElement = (MobileElement) objStepBase2.getDriver().findElement(locator);
		try {
			mobileElement.click();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Martin
	 * @param locator
	 *            of the element in which text has to be set
	 * @param fieldValue
	 *            actual value to be set in the field
	 * @return true if text is set in the desired field else false
	 * @CreatedDate 11-4-2018
	 */

	public boolean mobileSetText(By locator, String fieldValue) {
		// waitForMobileElementPresence(locator);
		MobileElement mobileElement = (MobileElement) objStepBase2.getDriver().findElement(locator);
		try {
			mobileElement.clear();
			mobileElement.sendKeys(fieldValue);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Digvijay Dusane
	 * @param locator
	 * @param waitInSeconds
	 *            - Time in seconds
	 */
	// public void waitForMobileElementPresence(By locator, int waitInSeconds) {
	// try {
	// Wait<WebDriver> wait = new WebDriverWait(objStepBase2.android(),
	// waitInSeconds)
	// .ignoring(StaleElementReferenceException.class);
	// wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	// } catch (Exception exception) {
	// System.out.println("Step Failed - Unable to find element");
	// }
	// }

	/**
	 * @Method : mobileGetText
	 * @Description : This is wrapper method to get text form input elements
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            textBy - get text by value attribute (set textBy as value)/ by
	 *            visible text (set textBy as text)
	 * @return : - text as string
	 * @author : Digvijay Dusane
	 * 
	 */

	public String mobileGetText(By locator, String textBy) {

		try {
			waitForElementPresence(locator, 10);
			WebElement webElement = appiumDriver.findElement(locator);
			String strText = "";
			if (textBy.equalsIgnoreCase("value"))
				strText = webElement.getAttribute("value");
			else if (textBy.equalsIgnoreCase("text"))
				strText = webElement.getText();
			return strText;
		} catch (Exception exception) {
			System.out.println("Step Failed - Unable to get mobile text");
			return null;
		}
	}

	/**
	 * 
	 * @Method : SndKeyEvent
	 * @Description : This is wrapper method to sendKeyEvent
	 * @param :
	 *            Keycode - Keycode for Event
	 * @author : Digvijay Dusane
	 */

	public boolean sendKeyEvent(int keyCode) {
		try {
			Runtime.getRuntime().exec("adb shell input keyevent " + keyCode);
			return true;
		} catch (Exception exception) {
			System.out.println("Step Failed - Unable to send keys");
			return false;
		}
	}

	public void clickOnMobileBackButton() {
		try {
			// sendKeyEvent(4);
			objStepBase2.appiumDriver.navigate().back();
		} catch (Exception e) {
			System.out.println("Step Failed - Unable to click on back button");
		}

	}

	public boolean clickOnMobileScreen() {

		try {
			TouchAction action = new TouchAction(appiumDriver);
			action.tap(300, 700).perform().release();
			return true;
		} catch (Exception e) {
			System.out.println("Step Failed - Unable to click on mobile screen");
			return false;
		}
	}

	public void bluetoothOn() {
		try {
			Runtime.getRuntime().exec("adb shell su -c service call bluetooth_manager 6 Enable");
			// Runtime.getRuntime().exec("adb shell am start -a
			// android.bluetooth.adapter.action.REQUEST_ENABLE");
		} catch (Exception exception) {
			System.out.println("Step failed - Unable to turn bluetooth on");
		}
	}

	public void bluetoothOff() {
		try {
			Runtime.getRuntime().exec("adb shell su -c service call bluetooth_manager 8 Disable");
		} catch (Exception exception) {
			System.out.println("Step failed - Unable to turn bluetooth off");
		}
	}

	public void bluetoothOnOFF(String sOnOff) throws Exception {

		// if (sOnOff.equalsIgnoreCase("On"))
		// Runtime.getRuntime().exec("adb shell su -c service call bluetooth_manager 6
		// Enable");
		try {
			Runtime.getRuntime().exec("adb shell am start -a android.bluetooth.adapter.action.REQUEST_ENABLE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Step Failed - Unable to on/off Bluetooth");

		}
		/*
		 * else Runtime.getRuntime().
		 * exec("adb shell su -c service call bluetooth_manager 8 Disable");
		 */
	}

	/**
	 * @Description : This is wrapper method wait for mobile element presence
	 *              located
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForMobileElementPresence(By locator) throws NotFoundException {
		appiumDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * @Description : This is wrapper method wait for mobile element to be clickable
	 * @param :
	 *            locator - By identification of element
	 */

	public void waitForMobileElementToBeClickable(By locator) throws NotFoundException {

		appiumDriverWait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public boolean mobileDropDownOption(By locator, String option, String... selectType) {
		try {
			waitForElementPresence(locator, 10);
			WebElement webElement = objStepBase2.getDriver().findElement(locator);
			Select sltDropDown = new Select(webElement);

			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equals("Value"))
					sltDropDown.selectByValue(option);
				else if (selectType[0].equals("Text"))
					sltDropDown.selectByVisibleText(option);
				else if (selectType[0].equals("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));

				return true;
			} else {
				// Web elements from dropdown list
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
					} else
						iIndex++;
					if (blnOptionAvailable)
						break;
				}
				if (blnOptionAvailable)
					return true;
				else
					return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	public void waitForMobile(final int timeInMilliseconds) {
		try {
			Thread.sleep(timeInMilliseconds);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void waitForElement(int timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("print ur message here");
		}
	}
	
//	public static void longPressElement() {
//		TouchAction action = new TouchAction(objStepBase2.getDriver());
//		MobileElement ele = (MobileElement) driver.findElement(By.xpath("//*[contains(@text,'I can’t sleep at night. Is there anything I can do?')]"));
//		action.longPress(ele).release().perform();
//	}
	
	public boolean longPressElement(By locator) {

		TouchAction action = new TouchAction(objStepBase2.getDriver());
		MobileElement mobileElement = (MobileElement) objStepBase2.getDriver().findElement(locator);
		try {
			action.longPress((WebElement) locator).release().perform();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	
}
