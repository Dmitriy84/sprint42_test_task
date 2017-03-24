package com.sprint42.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends BasePage {
	public EmailPage(WebDriver webdriver) {
		super(webdriver);
	}

	@FindBy(id = "composeto")
	public WebElement email;

	@FindBy(id = "cmcsubj")
	public WebElement theme;

	@FindBy(xpath = "//div[@class='lc']/div[@onclick=\"_e(event, 'Wa')\"]")
	public WebElement send;

	@FindBy(id = "cmcbody")
	public WebElement body;

	@FindBy(xpath = "//div[@class='vh' and .='Your message has been sent.']")
	public WebElement SuccessMessage;

	@FindBy(xpath = "//div[@class='kc']/div[starts-with(@class,'M j T b hc rs Le')]")
	public WebElement close;

	@FindBy(xpath = "//div[contains(@onclick, '$a')]")
	public WebElement save;

	@FindBy(xpath = "//div[starts-with(@class,'M j T b hc Lm')]")
	public WebElement menu;

	@FindBy(xpath = "//div[@class='Pk ec']")
	public WebElement drafts;

	@FindBy(xpath = "//div[@onclick=\"_e(event, 'Ub','^f')\"]")
	public WebElement sent;
}