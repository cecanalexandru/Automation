package stepdefinition;

import static utilities.CommonMethods.driver;
import static utilities.CommonMethods.clickElement;
import static utilities.CommonMethods.getElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import utilities.CommonMethods;

public class HomePageStepDefintion {
	String url;

	@Before
	public void setUp() {
		url = "https://www.ncl.com/";
		CommonMethods.driverSetUp("chrome");
	}

	@Given("^a Guest$")
	public void userType() throws Throwable {
		System.out.println("Guest User....");
	}

	@Given("^I am on Homepage$")
	public void homePage() throws Throwable {
		// navigate to homepage
		driver.get(url);
	}

	@Given("^I navigated to 'Shore Excursion' page$")
	public void navigateTo() throws Throwable {
		clickElement("cssSelector", "a[title='Explore']");
		clickElement("cssSelector", "a[title='Shore Excursions']");
		clickElement("xpath", "//*[@id=\"search_destinations_chosen\"]/a/span");
	}

	@When("^I search for destination 'Alaska Cruises'$")
	public void searchFor() throws Throwable {
		WebElement elData = getElement("xpath", "//*[@id=\"search_destinations_chosen\"]//input");
		elData.sendKeys("Alaska Cruises", Keys.TAB);
		driver.switchTo().alert();
		Thread.sleep(2000);
		clickElement("cssSelector", "button[class$='submit']");
	}

	@Then("^Shore Excursions page is present$")
	public void validatePage() throws Throwable {
		String expectedTitle = "Shore Excursions";
		String actualTitle = getElement("cssSelector", ".breadcrumb-detail h2").getText();
		Assert.assertEquals(expectedTitle, actualTitle);

	}

	@Then("^Results are filtered by 'Alaska Cruises'$")
	public void validateResult() throws Throwable {
		String filterResult = getElement("cssSelector", ".items-text").getText();
		Assert.assertEquals("Alaska Cruises", filterResult);
	}

	@Then("^Filter By Ports are only belong to 'Alaska, British Columbia'$")
	public void filterBy() {
		clickElement("cssSelector", "a[title='Port']");
		List<WebElement> l = driver.findElements(By.xpath("//*[@id=\"ports\"]/li/label"));
		for (WebElement element : l) {
			String elementText = element.getText();
			if (elementText != null & (elementText.contains("Alaska") || elementText.contains("British Columbia"))) {

				element.click();
			}
		}
		driver.findElement(By.xpath("//*[@id=\"sap-menu-left\"]/div/div[4]/ul[1]/li[2]/div[1]/div/div[4]/ul/li[2]/a"))
				.click();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
