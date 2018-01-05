package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener
{
	
	
    private static WebDriver getDriver() {
		
		return TestBase.webdriver;
	}

	@Override
	public void onFinish(ITestContext Result) {
		 System.out.println("The name of the TestSuite finished is :"+Result.getName());
		
	}

	@Override
	public void onStart(ITestContext Result) {
		
		System.out.println("The name of the TestSuite started is :"+Result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		
		System.out.println("The name of the onTestFailed But Within Success Percentage is :"+Result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		 System.out.println("***** Error "+result.getName()+" test has failed *****");
    	 //String methodName=result.getName().toString().trim();
    	 //TestBase.takeScreenShot(methodName);
	}
	@Override
	public void onTestSkipped(ITestResult Result) {
		
		System.out.println("The name of the Test skipped is :"+Result.getName());

	}

	@Override
	public void onTestStart(ITestResult Result) {
		
		System.out.println(Result.getName()+" test case started");
		
	}

	@Override
	public void onTestSuccess(ITestResult Result) {
		
		System.out.println(Result.getName()+" test case passed");

	}

}
