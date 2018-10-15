package com.firstframework.qa.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class PageObject {
	protected static WebDriver driver = null;
	private static String APP_URL;
	private static String DRIVER_URL;
	private static BrowserType BROWSER_TYPE;

	private enum BrowserType {
		IE, CHROME, FIREFOX
	}

	public static void startBrowser() {
		if (driver == null) {

			Properties configProperties = new Properties();
			try {
				configProperties.load(PageObject.class.getResourceAsStream("/config.properties"));
				APP_URL = configProperties.getProperty("APP_URL");
				DRIVER_URL = configProperties.getProperty("DRIVER_URL");
				BROWSER_TYPE = BrowserType.valueOf(configProperties.getProperty("BROWSER_TYPE"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch (BROWSER_TYPE) {
			case IE:
				break;
			case CHROME:
				System.setProperty("webdriver.chrome.driver", DRIVER_URL);
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				System.setProperty("webdriver.gecko.driver", DRIVER_URL);
				driver = new FirefoxDriver();
				break;
			}

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(APP_URL);

	}

	public static void closeBrowser() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void waitForPage(String title, String errorMessage) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.titleContains(title));
		} catch (TimeoutException e) {
			Assert.assertTrue(false, errorMessage);
		}
	}

	public static WebElement waitForClick(WebElement e, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(e));
		return e;
	}

	public static WebElement waitForClick(WebElement e) {
		return waitForClick(e, 10);
	}

	public static WebElement waitForVisible(WebElement e, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(e));
		return e;
	}

	public static WebElement waitForVisible(WebElement e) {
		return waitForVisible(e, 10);
	}
}
