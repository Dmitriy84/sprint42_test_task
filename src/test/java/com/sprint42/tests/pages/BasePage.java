package com.sprint42.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BasePage {
	public BasePage(WebDriver webdriver) {
		PageFactory.initElements(webdriver, this);
		wait = new WebDriverWait(webdriver, 10);
	}

	protected final WebDriverWait wait;
}