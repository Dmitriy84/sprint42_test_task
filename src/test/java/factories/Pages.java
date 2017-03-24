package factories;

import org.openqa.selenium.WebDriver;

import com.sprint42.tests.pages.EmailPage;
import com.sprint42.tests.pages.HomePage;
import com.sprint42.tests.pages.LoginPage;

final public class Pages {
	public static HomePage home(WebDriver webdriver) {
		return new HomePage(webdriver);
	}

	public static LoginPage login(WebDriver webdriver) {
		return new LoginPage(webdriver);
	}

	public static EmailPage newEmail(WebDriver webdriver) {
		return new EmailPage(webdriver);
	}
}