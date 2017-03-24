package com.sprint42.tests;

import factories.Pages;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sprint42.tests.helpers.Storage;
import com.sprint42.tests.pages.EmailPage;
import com.sprint42.tests.pages.HomePage;
import com.sprint42.tests.pages.LoginPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sprint42 {
	public static AndroidDriver<WebElement> driver;

	@BeforeClass
	public static void initializeDriver() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,
				MobilePlatform.ANDROID);
		cap.setCapability("platformVersion", "6.0");
		cap.setCapability("deviceName", "ANDROID");
		cap.setCapability(
				"app",
				System.getProperty("user.dir")
						+ "\\apk\\com.android.chrome_53.0.2785.124-278512411_minAPI21(x86).apk");
		cap.setCapability("appActivity", "com.android.chrome.Main");
		cap.setCapability("newCommandTimeout", "1800");
		cap.setCapability("clearSystemFiles", "true");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://localhost:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void closeDriver() {
		if (driver == null)
			return;
		driver.close();
		driver.quit();
	}

	@Test
	public void test1_login() throws InterruptedException {
		final String account = "sprint42test";
		driver.get("https://mail.google.com");
		LoginPage login = Pages.login(driver);

		// Enter invalid username and password
		login(RandomStringUtils.randomAlphanumeric(20));
		login.waitForError("Sorry, Google doesn't recognize that email.");

		// Enter valid username and password to login
		login(account);
		login.password.sendKeys("1qa@WS3ed");
		login.signIn.click();

		HomePage home = Pages.home(driver);

		// Verify your username and default inbox selected.
		String df_account = home.defaultAccount().getText();
		Assert.assertEquals("Unexpected default account", account
				+ "@gmail.com", df_account);

		// clear emails
		EmailPage email = Pages.newEmail(driver);
		email.menu.click();
		email.sent.click();
		home.marks.forEach(WebElement::click);
		home.delete.click();

		// Generate 5 different emails
		for (int i = 0; i < 5; i++) {
			home.newEmail().click();
			String subj = RandomStringUtils.randomAlphanumeric(20);
			createEmail(account + "@gmail.com", subj,
					RandomStringUtils.randomAlphanumeric(40));
			Storage.add(subj);
		}
	}

	@Test
	public void test2_manipulate_emails() {
		HomePage home = Pages.home(driver);

		selected = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			Object expected = Storage.getRandom();
			WebElement filter = home.emailsSubjects()
					.filter(el -> el.getText().equals(expected)).findFirst()
					.get();
			filter.click();
			selected.add(filter);
		}
		WebElement[] elements = { home.other, home.archive, home.delete,
				home.move };

		// visible
		for (WebElement el : elements) {
			el.isDisplayed();
		}

		// deselect
		selected.forEach(WebElement::click);

		// invisible
		for (WebElement el : elements) {
			try {
				el.isDisplayed();
				throw new Exception(el + " should be invisible!");
			} catch (Exception e) {
			}
		}
	}

	@Test
	public void test3_delete_emails() {
		selected.forEach(WebElement::click);
		HomePage home = Pages.home(driver);
		home.delete.click();
		Assert.assertEquals("Unexpected number of letters:", 3,
				home.marks.size());
	}

	private static List<WebElement> selected;

	private void createEmail(String to, String theme, String body) {
		EmailPage email = Pages.newEmail(driver);
		email.email.sendKeys(to);
		email.theme.sendKeys(theme);
		email.body.sendKeys(body);
		email.send.click();
	}

	private void login(String email) {
		LoginPage login = Pages.login(driver);
		login.email.clear();
		login.email.sendKeys(email);
		login.next().click();
	}
}