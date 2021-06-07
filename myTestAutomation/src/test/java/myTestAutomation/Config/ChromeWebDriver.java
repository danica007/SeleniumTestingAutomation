package myTestAutomation.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public final class ChromeWebDriver {
	private static final String WEBDRIVER_CHROME_PROPERTY = "webdriver.chrome.driver";
	private static final String WEBDRIVER_CHROME = "C:\\ChromeDriver\\chromedriver.exe";

	private static WebDriver webDriver;

	private static ChromeWebDriver INSTANCE;

	private ChromeWebDriver() {
		System.setProperty(WEBDRIVER_CHROME_PROPERTY, WEBDRIVER_CHROME);

		webDriver = new ChromeDriver();
	}

	public synchronized static ChromeWebDriver getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ChromeWebDriver();
		}

		return INSTANCE;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

}
