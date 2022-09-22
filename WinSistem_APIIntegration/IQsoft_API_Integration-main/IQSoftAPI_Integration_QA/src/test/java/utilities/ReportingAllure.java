package utilities;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.BaseTest;


public class ReportingAllure implements ITestListener {
    BaseTest baseTest = new BaseTest();
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

//     Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }



    @Override
    public void onStart(ITestContext iTestContext) {
        saveTextLog((iTestContext.getName()) + ": Test execution starts");
        BaseTest.logger.info((iTestContext.getName()) + ": Test execution starts");

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        saveTextLog((iTestContext.getName()) + ": Test execution finished");
        BaseTest.logger.info((iTestContext.getName()) + ": Test execution finished");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        saveTextLog(getTestMethodName(iTestResult) + ": Test passed");
        saveTextLog("RequestBody : " + baseTest.authorizationRequestBody);
        saveTextLog("ResponseBody : " + baseTest.authorizationResponseBody);
        BaseTest.logger.info(getTestMethodName(iTestResult) + ": Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveTextLog(getTestMethodName(iTestResult) + ": Test failed");
        saveTextLog("RequestBody : " + baseTest.authorizationRequestBody);
        saveTextLog("ResponseBody : " + baseTest.authorizationResponseBody);
        BaseTest.logger.error(getTestMethodName(iTestResult) + " Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        saveTextLog(getTestMethodName(iTestResult) + ": Test Skipped");
        saveTextLog("RequestBody : " + baseTest.authorizationRequestBody);
        saveTextLog("ResponseBody : " + baseTest.authorizationResponseBody);
        BaseTest.logger.warn(getTestMethodName(iTestResult) + " Test failed");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        saveTextLog(getTestMethodName(iTestResult) + ": Test failed partially");
        BaseTest.logger.error(getTestMethodName(iTestResult) + ": Test failed partially");
    }
}
