package com.oms.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.oms.pages.LoginPage;

import utils.ExcelUtils;
import utils.TestBase;
import utils.WebDriverUtils;

public class LoginPageTest extends TestBase
{
	
	LoginPage login;
	
	
	private static Logger log= Logger.getLogger(LoginPageTest.class.getName());
	
	@Test(priority=0)
	public void TestloginToApplicationWithValidCredentials_TC_01() throws Exception
	{
		log.info("login to application test started");
		login= new LoginPage();
		webdriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);	
		login.enterUserName("ali.monsalvo")
		       .enterPassWord("Mr3499!!!!")
		       .loginBtnClick()
		       .PracticeSelect()
		       .officeSelect();
		
		WebDriverWait wait= new WebDriverWait(webdriver,60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='/dashboard/Home']")));
		
		Assert.assertEquals(webdriver.getTitle(),"OMS | Dashboard","user failed to login");
		
		//logoutFromOMS();
	
	}
	
	//@Test(priority=1)
	public void TestloginToApplicationWithInValidCredentials_TC_02() throws Exception
	{
	    webdriver.findElement(By.xpath("//*[@class='container']/div[2]/ul/li[5]/a/img")).click();	
		login= new LoginPage();
		
		login.enterUserName("ali.monsalv@")
	            .enterPassWord("Mr3499!!!#")
	              .loginBtnClick();
		
		WebDriverWait wait= new WebDriverWait(webdriver,60);
		wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id='span-error']"))));
		
		Assert.assertEquals("Username or Password is incorrect.",webdriver.findElement(By.xpath("//*[@id='span-error']")).getText());
	}

}
