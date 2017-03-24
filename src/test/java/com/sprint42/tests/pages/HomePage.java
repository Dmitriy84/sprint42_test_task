package com.sprint42.tests.pages;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
	public HomePage(WebDriver webdriver) {
		super(webdriver);
	}

	public WebElement defaultAccount() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//div[@class='V Y undefined']")));
	}

	public WebElement newEmail() throws InterruptedException {
		Thread.sleep(500);
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("div.lc div")));
	};

	public Stream<WebElement> emailsSubjects() {
		return marks.stream().map(
				(el) -> el.findElement(By
						.xpath("../../div[@class=' Kg Il']/span")));
	}

	@FindBy(xpath = "//div[@id='bev_tbt']/descendant::div[@onclick=\"_e(event, 'f',  9 )\"]/following::div")
	public WebElement other;

	@FindBy(xpath = "//div[@id='bev_tbt']/descendant::div[@onclick=\"_e(event, 'f',  1 )\"]")
	public WebElement archive;

	@FindBy(xpath = "//div[@id='bev_tbt']/descendant::div[@onclick=\"_e(event, 'f',  9 )\"]")
	public WebElement delete;

	@FindBy(xpath = "//div[@id='bev_tbt']/descendant::div[@onclick=\"_e(event, 'f',  8 )\"]")
	public WebElement move;

	@FindBy(xpath = "//div[@class='km']/descendant::div[@class='M j Pl']/div[@class='V j cb Ol']")
	public List<WebElement> marks;
}