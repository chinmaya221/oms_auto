package com.oms.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Page;

public class LoginPage extends Page
{
	
	@FindBy(id="txtloginName")
	public static WebElement userName;
	
	@FindBy(id="txtpassword")
	public static WebElement passWord;
	
	@FindBy(id="btnlogin")
	public static WebElement loginBtn;
	
	@FindBy(id="cmbPractice")
	public static WebElement PracticeSelect;
	
	@FindBy(id="cmbOffice")
	public static WebElement OfficeSelect;
	
	public LoginPage() throws Exception
	{
		super();
		PageFactory.initElements(webdriver, this);
	}
	
	
	public LoginPage enterUserName(String username)
	{
		userName.sendKeys(username);
		return this;
	}
	
	public LoginPage enterPassWord(String password)
	{
		passWord.sendKeys(password);
		return this;
	}
	
	public LoginPage loginBtnClick()
	{
		loginBtn.click();
		return this;
	}
	
	
	public LoginPage PracticeSelect()
	{
		Select practice = new Select(PracticeSelect);
		practice.selectByVisibleText("Mayflower Oral and Maxillofacial Surgery (45)");
		return this;
	}
	
	
	public LoginPage officeSelect()
	{
		Select office = new Select(OfficeSelect);
		office.selectByVisibleText("Raynham");
		return this;
	}
	
	public void logon(String VALIDUSERNAME, String VALIDPASSWORD)
	{
		   enterUserName(VALIDUSERNAME)
	                        .enterPassWord(VALIDPASSWORD)
	                          .loginBtnClick()
	                            .PracticeSelect()
	                              .officeSelect();
	}
	
	public void logout()
	{
		//webdriver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		webdriver.findElement(By.xpath("//*[@class='dropdown dropdown-user dropdown-dark open']/a/span")).click();
		WebElement logOutBtn = webdriver.findElement(By.xpath("//*[@class='dropdown-menu dropdown-menu-default']/li[5]/a"));
		((JavascriptExecutor)webdriver).executeScript("arguments[0].click()",logOutBtn);
	}
	
	

}
