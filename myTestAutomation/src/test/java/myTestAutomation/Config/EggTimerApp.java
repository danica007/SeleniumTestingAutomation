package myTestAutomation.Config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EggTimerApp {

	private static final String WEBSITE = "https://e.ggtimer.com/";

	private String inputTime_Id = "EggTimer-start-time-input-text";
	private String startTimerButton_Id = "button[data-for='startpage']";
	private WebDriver webDriver;

	public EggTimerApp() {
		webDriver = ChromeWebDriver.getInstance().getWebDriver();
	}

	public void startTimer(Long time) {
		// Launch the web application
		webDriver.navigate().to(this.getWebsite());

		// Enter 25 seconds timer
		webDriver.findElement(By.id(this.getInputTime_Id())).sendKeys(Long.toString(time));

		// Start timer
		webDriver.findElement(By.cssSelector(this.getStartTimerButton_Id())).click();
	}

	public boolean isPopupMessagePresent() {
		Alert alert = ExpectedConditions.alertIsPresent().apply(webDriver);
		return alert != null;
	}

	public void waitTimeout(Long time, TimeUnit unit) {
		webDriver.manage().timeouts().implicitlyWait(time, unit);
	}

	public String getStringValueOnTheScreen() {

		return webDriver.findElement(By.className("ClassicTimer-time")).findElement(By.tagName("span")).getText();

	}

	public String getStartTimerButton_Id() {
		return startTimerButton_Id;
	}

	public void setStartTimerButton_Id(String startTimerButton_Id) {
		this.startTimerButton_Id = startTimerButton_Id;
	}

	public String getInputTime_Id() {
		return inputTime_Id;
	}

	public void setInputTime_Id(String inputTime_Id) {
		this.inputTime_Id = inputTime_Id;
	}

	public String getWebsite() {
		return WEBSITE;
	}
}
