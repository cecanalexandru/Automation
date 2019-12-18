package utilities;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CommonMethods {
	public static WebDriver driver = null;

	public static void driverSetUp(String browserType) {
		String browserName = null;
		String browserPath = null;

		if (browserType != null && browserType.equalsIgnoreCase("chrome")) {
			browserName = "webdriver.chrome.driver";
			browserPath = "./src/test/resources/driver/chromedriver.exe";
			System.setProperty(browserName, browserPath);

			driver = new ChromeDriver();

		} else if (browserType != null && browserType.equalsIgnoreCase("IE")) {
			// TODO ... not complete..
			driver = new InternetExplorerDriver();
		} else if (browserType != null && browserType.equalsIgnoreCase("Firefox")) {
			// TODO ... not complete..
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	// locate element
	public static WebElement getElement(String locatorType, String elementPath) {
		WebElement element = null;
		switch (locatorType) {
		case "id":
			element = driver.findElement(By.id(elementPath));
			break;
		case "name":
			element = driver.findElement(By.name(elementPath));
			break;
		case "cssSelector":
			element = driver.findElement(By.cssSelector(elementPath));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(elementPath));
			break;
		case "tagName":
			element = driver.findElement(By.tagName(elementPath));
			break;
		case "className":
			element = driver.findElement(By.className(elementPath));
			break;
		case "linkText":
			element = driver.findElement(By.linkText(elementPath));
			break;
		case "partialLinkText":
			element = driver.findElement(By.partialLinkText(elementPath));
			break;
		default:
			System.out.println("Invalid Locator Type: Pleaese check");
			break;
		}
		return element;
	}

	//click element
	public static void clickElement(String locatorType, String elementPath) {
		getElement(locatorType, elementPath).click();
	}
}
