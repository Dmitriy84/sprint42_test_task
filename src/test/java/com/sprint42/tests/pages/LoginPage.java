package com.sprint42.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver webdriver) {
		super(webdriver);
	}

	@FindBy(id = "Email")
	public WebElement email;

	@FindBy(id = "Passwd")
	public WebElement password;

	@FindBy(id = "signIn")
	public WebElement signIn;

	@FindBy(xpath = "//a[@href='https://accounts.google.com/SignOutOptions?hl=uk&continue=https://mail.google.com/mail&service=mail']")
	public WebElement Account;

	public WebElement next() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("next")));
	}

	public void waitForError(String text) {
		wait.until(ExpectedConditions.textToBe(By.id("errormsg_0_Email"), text));
	}
}