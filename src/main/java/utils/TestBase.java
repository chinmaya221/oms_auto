package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;






import com.oms.pages.LoginPage;
//import com.infiniview.WebPages.Loginpage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.WebDriverUtils;

public class TestBase {

	//protected static final String AUTOMATION_OBJECT_PREFIX = "auto";
	protected static final String OMS_VALID_USERNAME = "ali.monsalvo";
	protected static final String OMS_VALID_PASSWORD = "Mr3499!!!!";
	// Path for the screenshots
	protected static final String SCREENSHOT_LOCATION = "/OMS_Automation/test-output/Screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";

	protected WebDriverUtils driverUtils;
	protected static WebDriver webdriver;
	protected boolean oneTimeSetUp = true;
	protected boolean oneTimeTearDown = true;
	protected static boolean logSettingUpdated = false;
	// protected final Logger logger = Logger.getLogger(TestBase.class);
	public String InfiniviewUrl = null;
	public static ExtentReports report;
	public static ExtentTest logger;

	
	@Parameters({ "browser" })
	@BeforeClass
	protected void startWebdriver(String browser) throws Exception {
		webdriver = WebDriverUtils.startWebDriver(browser);
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("browser open-- startWebdriver ");
	}

	@BeforeTest
	public void ExtentReportStart() {
		System.out.println("start------ ExtentReportStart");
		report = new ExtentReports(System.getProperty("user.dir")
				+ "/test-output/Extent report/OMS_Auto_ExtentReport.html",
				true);
		report.addSystemInfo("Host Name", "CentOsVM - 192.168.190.129")
				.addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Chinmaya QA");
		report.loadConfig(new File(System.getProperty("user.dir")
				+ "\\extent-config.xml"));
		System.out.println("started------ ExtentReportStart");
		

	}

	/*
	 * @BeforeMethod public void startExtentreport(ITestContext result) { String
	 * method=result.getName(); logger = report.startTest(method); }
	 */

	@BeforeMethod
	public void nameBefore(Method method) {
		logger = report.startTest(method.getName());
	}

	@AfterMethod
	public void TestResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL,
					"Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL,
					"Test Case Failed is " + result.getThrowable());
			String screenShotPath = takeScreenShot(webdriver, result.getName());
			logger.log(LogStatus.FAIL, result.getThrowable());
			logger.log(
					LogStatus.FAIL,
					"Snapshot below: "
							+ logger.addScreenCapture(screenShotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP,
					"Test Case Skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS,
					"Test Case Success  is " + result.getName());
		}
		report.endTest(logger);
	}

	@AfterTest
	public void endReport(){
		report.flush();
		report.close();
	}

    public void loginToOMS() throws Exception {
		LoginPage login = new LoginPage();
		login.logon(OMS_VALID_USERNAME, OMS_VALID_PASSWORD);
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void logoutFromOMS() throws Exception {
		LoginPage login = new LoginPage();
		login.logout();
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/*public static String getRandomAutomationObjectName() {
		return getRandomAutomationObjectName(AUTOMATION_OBJECT_PREFIX);
	}*/

	public static String getRandomAutomationObjectName(String customPrefix) {
		return customPrefix + RandomStringUtils.randomAlphanumeric(8);
	}

	protected void stopWebdriver() {
		if (webdriver != null)
			webdriver.close();
		webdriver.quit();

	}

	// capture screenshot for failed test cases
	public static String takeScreenShot(WebDriver webDriver,
			String screenshotname) {
		// get the driver
		webDriver = TestBase.getDriver();
		File scrFile = ((TakesScreenshot) webDriver)
				.getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with test
		// method name
		String dest = SCREENSHOT_LOCATION + screenshotname + SCREENSHOT_FORMAT;
		try {
			FileUtils.copyFile(scrFile, new File(dest));
			System.out.println("***Placed screen shot in "
					+ SCREENSHOT_LOCATION + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}

	private static WebDriver getDriver() {

		return webdriver;
	}

	@AfterSuite(alwaysRun = true)
	public void oneTimeTearDown() throws Exception {
		stopWebdriver();
	}

}