package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Constants;
import utils.Logs;

public class BaseTest {

	public static RequestSpecification req;
	public static Logger logger;
	public static RequestSpecification res;
	public static ResponseSpecification resspec;
	public static Response response;
	ExtentReports extent=new ExtentReports();
	ExtentSparkReporter spark=new ExtentSparkReporter(Constants.EXTENT_REPORT_DIRECTORY);
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-yy");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	public BaseTest() {
		logger = Logs.logSetup();
	}

	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(Constants.GLOBAL_PROP);
		prop.load(fis);
		return prop.getProperty(key);
	}

	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}
//	@BeforeTest
//	public void beforeTest() {
//		extent.attachReporter(spark);
//	}
//	@AfterTest
//	public void afterTest() {
//		extent.flush();
//	}
	
}
