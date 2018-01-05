package utils;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WebDriverUtils;

public class Page{

	protected static WebDriver webdriver;
	protected WebDriverUtils driverUtils;

	public Page() throws Exception {
		webdriver = WebDriverUtils.getDriver();
		driverUtils = new WebDriverUtils();
	}
	
	
	
}
