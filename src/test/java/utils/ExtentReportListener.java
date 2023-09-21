package utils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ExtentReportListener implements ITestListener {
    static ExtentReports extent;
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(Constants.EXTENT_REPORT_DIRECTORY);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
        test.log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        String pathOfExtentReport = Constants.EXTENT_REPORT_DIRECTORY;

		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }

    public static void logApiDetails(RequestSpecification res, Response response) {
    	res.get();
    	QueryableRequestSpecification queryRequest = SpecificationQuerier.query(res);
    	String retrieveURI = queryRequest.getBaseUri();
    	System.out.println(queryRequest.getBaseUri()+" / "+queryRequest.getBasePath()+" / "+queryRequest.getURI()+" / "+queryRequest.getUserDefinedPath());
        test.log(Status.INFO, "Request URL: " + retrieveURI);
        test.log(Status.INFO, "Request URL: " + queryRequest.getBasePath());
        test.log(Status.INFO, "Request Method: " +queryRequest.getMethod());
        test.log(Status.INFO, "Request Headers: " +queryRequest.getHeaders());
        test.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
    }
}
