package RaneemTestCase;

import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import dev.failsafe.internal.util.Assert;

public class MainTestCaseAutomationLab {

	WebDriver driver = new ChromeDriver();

	String WebSite = "https://codenboxautomationlab.com/practice/";

	Random rand = new Random();

	private JavascriptExecutor js;

	@BeforeTest
	public void MySetup() {

		driver.manage().window().maximize();
		driver.get(WebSite);
		JavascriptExecutor js = (JavascriptExecutor) driver;

	}

	@Test(priority = 1, description = " Radio Button", invocationCount = 1, enabled = false)

	public void Radio_Button_Example() {

		List<WebElement> AllRadioButtons = driver.findElements(By.className("radioButton"));

		int randomIndex = rand.nextInt(AllRadioButtons.size());
		AllRadioButtons.get(randomIndex).click();

		boolean expectedddddddddddResult = true;
		boolean ActualResult = AllRadioButtons.get(randomIndex).isSelected();

	     Assert.assertEquals(ActualResult, expectedddddddddddResult);

	}

	@Test(priority = 2, description = "DynamicDropdown List", enabled = false)

	public void Dynamic_Dropdown_Example() throws InterruptedException {

		String[] countryCodes = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES" };

		int randomIndex = rand.nextInt(countryCodes.length);

		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));
		DynamicListInput.sendKeys(countryCodes[randomIndex]);
		Thread.sleep(1000);

		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", DynamicListInput);

		String updateDataInMyInput = DataInsideMyInput.toUpperCase();

		boolean ActualValue = updateDataInMyInput.contains(countryCodes[randomIndex].toUpperCase());

		boolean ExpectedResult = true;

		Assert.assertEquals(ActualValue, ExpectedResult);

	}

	@Test(priority = 3, description = "Static Dropdown List", enabled = false)

	public void Static_Dropdown_Example() {
		WebElement SelectElement = driver.findElement(By.id("dropdown-class-example"));

		Select sel = new Select(SelectElement);

		// sel.selectByIndex(3);
		// sel.selectByValue("option3");
		sel.selectByVisibleText("Appium");

	}

	@Test(priority = 4, description = "Check box Example", enabled = false)
	public void Checkbox_Example() {
		List<WebElement> Checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int ranodomIndex = rand.nextInt(Checkboxes.size());

		// Checkboxes.get(ranodomIndex).click();

		for (int i = 0; i < Checkboxes.size(); i++) {
			Checkboxes.get(i).click();

			Checkboxes.get(i).click();
			boolean ActaualResult = Checkboxes.get(i).isSelected();
			boolean expectedResult = true;

			Assert.assertEquals(ActaualResult, expectedResult);
		}

	}

	@Test(priority = 5, description = "this is to move from window to another one", enabled = false)
	public void Switch_Window_Example() throws InterruptedException {
		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));

		OpenWindowButton.click();
		Thread.sleep(2000);
		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(windowsHandels.size());
		driver.switchTo().window(windowsHandels.get(1));

		// in the second window
		WebElement ContactButton = driver.findElement(By.id("menu-item-9680"));
		ContactButton.click();
		System.out.println(driver.getTitle() + " hello from the second window");

		driver.close();
		// switch to the first window
		driver.switchTo().window(windowsHandels.get(0));

	}

	@Test(priority = 6, description = "check moving to another tab", enabled = false)
	public void Switch_Tab_Example() throws InterruptedException {
		WebElement OpenTabButton = driver.findElement(By.id("opentab"));

		OpenTabButton.click();

		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));

		Thread.sleep(2000);
		System.out.println(driver.getTitle());

		driver.switchTo().window(windowsHandels.get(0));

	}

	@Test(priority = 7, description = "Alert and Confirm", enabled = false)
	public void Switch_To_Alert_Example() throws InterruptedException {
		WebElement nameBox = driver.findElement(By.id("name"));
		nameBox.sendKeys("Raneem Atef");

		// WebElement AlertBox = driver.findElement(By.id("alertbtn"));
		// AlertBox.click();
		Thread.sleep(1000);
		// driver.switchTo().alert().accept();
		// driver.switchTo().alert().dismiss();

		WebElement ConfirmBox = driver.findElement(By.id("confirmbtn"));
		ConfirmBox.click();
		// driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();

	}

	@Test(priority = 8, description = "play with the data of column", enabled = false)
	public void Web_Table_Example() {
		WebElement TheTable = driver.findElement(By.id("product"));
		List<WebElement> TheDataInsideTheTable = TheTable.findElements(By.tagName("tr"));

		for (int i = 1; i < TheDataInsideTheTable.size(); i++) {

			int totalTdInTheRow = TheDataInsideTheTable.get(i).findElements(By.tagName("td")).size();

			System.out.println(
					TheDataInsideTheTable.get(i).findElements(By.tagName("td")).get(totalTdInTheRow - 1).getText());
		}

	}

	@Test(priority = 9, description = "this is to test hide and show buttons", enabled = false)
	public void Element_Displayed_Example() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert myAssertion = new SoftAssert();

		js.executeScript("window.scrollTo(0,1500)");

		WebElement HideButtons = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButtons = driver.findElement(By.id("show-textbox"));

		HideButtons.click();

		WebElement theTEXXXXXTINPUT = driver.findElement(By.id("displayed-text"));

		myAssertion.assertEquals(theTEXXXXXTINPUT.isDisplayed(), false);

		Thread.sleep(4000);

		ShowButtons.click();

		myAssertion.assertEquals(theTEXXXXXTINPUT.isDisplayed(), true);

		myAssertion.assertAll();

	}

}
