package myTestAutomation.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import myTestAutomation.Config.ChromeWebDriver;
import myTestAutomation.Config.EggTimerApp;

@Test(groups = { "windows" })
public class TestBasicFunctionChrome {

	// Test Data
	private static final Long SECONDS_25 = 25L;
	private WebDriver driver;
	private EggTimerApp app;
	private String verifyDisplay;
	private Long timer;

	@BeforeTest
	public void setup() {
		// Initialise webDriver
		driver = ChromeWebDriver.getInstance().getWebDriver();

		// Initialise the web application
		app = new EggTimerApp();

		timer = SECONDS_25;
	}

	@Test
	public void test_WebPage_Load() { // Launch the web application
		driver.navigate().to(app.getWebsite());

		Assert.assertTrue(driver.getPageSource().contains("E.ggTimer is a simple countdown timer."),
				"The website is offline.");

	}

	@Test
	public void test_Timer_Countdown() {
		app.startTimer(SECONDS_25);

		while (timer != 0) {

			app.waitTimeout(1L, TimeUnit.SECONDS);
			
			verifyDisplay = app.getStringValueOnTheScreen();

			// verify count down if deducted by 1    
			// decrement time
			if (!verifyDisplay.contains(Long.toString(timer))) {
				Assert.assertTrue(verifyDisplay.contains(Long.toString(timer - 1)),
						"Timer is not counting down correctly. Current timer is " + timer);
				timer--;
			}

		}

	}

	@Test
	public void test_Messages_AfterTimer() {
		app.startTimer(SECONDS_25);

		while (!app.isPopupMessagePresent()) {
		}

		Assert.assertTrue(driver.switchTo().alert().getText().contains("Time Expired"),
				"Pop-up window is not available!");

		driver.switchTo().alert().accept();

		verifyDisplay = app.getStringValueOnTheScreen();

		Assert.assertEquals(verifyDisplay, "Time Expired!",
				"Last Display message on the main screen verified incorrect!");
	}

}
