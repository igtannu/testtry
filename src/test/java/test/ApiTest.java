
package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import utils.APIResources;
import utils.ExtentReportListener;
import utils.TestDataBuild;

@Listeners(ExtentReportListener.class)

public class ApiTest extends BaseTest {
	public static String Bookingid;
	public static String firstName;
	private static String token;
	JsonPath js;

	@Test(priority = 0)
	public void AuthorizationTokenPost() throws IOException {
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Start ");
		TestDataBuild data = new TestDataBuild();
		APIResources resourceAPI = APIResources.valueOf("TokenAPI");
		res = RestAssured.given().spec(requestSpecification()).body(data.authToken());
		resspec = new ResponseSpecBuilder().build();
		response = res.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		String ss = null;
		ss = response.asString();
		js = new JsonPath(ss);
		token = js.get("token").toString();
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " End ");
		ExtentReportListener.logApiDetails(res, response);

	}

	@Test(priority = 1)
	public void GetMethodTest() throws IOException {
		APIResources resourceAPI = APIResources.valueOf("CreatePostAPI");
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Start ");
		res = RestAssured.given().spec(requestSpecification());
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		response = res.when().get(resourceAPI.getResource()).then().spec(resspec).extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " End ");
		ExtentReportListener.logApiDetails(res, response);

	}

	@Test(priority = 2)
	public void PostMethodTest() throws IOException {
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Start ");
		TestDataBuild data = new TestDataBuild();
		APIResources resourceAPI = APIResources.valueOf("CreatePostAPI");
		res = RestAssured.given().spec(requestSpecification()).body(data.creatPost());
		resspec = new ResponseSpecBuilder().build();
		response = res.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		String ss = null;
		ss = response.asString();
		js = new JsonPath(ss);
		Bookingid = js.get("bookingid").toString();
		firstName = js.get("booking.firstname").toString();
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Ends ");
		ExtentReportListener.logApiDetails(res, response);

	}

	@Test(priority = 3)
	public void GetPostByIdTest() throws IOException {
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Start ");
		APIResources resourceAPI = APIResources.valueOf("CreatePostAPI");
		res = RestAssured.given().spec(requestSpecification());
		resspec = new ResponseSpecBuilder().build();
		response = res.when().get(resourceAPI.getResource() + "/" + Bookingid).then().spec(resspec).extract()
				.response();
		String ss = null;
		ss = response.asString();
		js = new JsonPath(ss);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(firstName, js.get("firstname").toString());
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Ends ");
		ExtentReportListener.logApiDetails(res, response);

	}

	@Test(priority = 4)
	public void UpdateMethodTest() throws IOException {
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Starts ");
		TestDataBuild data = new TestDataBuild();
		APIResources resourceAPI = APIResources.valueOf("CreatePostAPI");
		res = RestAssured.given().spec(requestSpecification()).header("Cookie", "token=" + token)
				.body(data.creatPost());
		resspec = new ResponseSpecBuilder().build();
		response = res.when().put(resourceAPI.getResource() + "/" + Bookingid).then().spec(resspec).extract()
				.response();
		Assert.assertEquals(response.getStatusCode(), 200);
		String ss = null;
		ss = response.asString();
		js = new JsonPath(ss);
		String first = js.get("firstname").toString();
		Assert.assertNotEquals(first, firstName);
		ExtentReportListener.logApiDetails(res, response);
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Ends ");

	}

	@Test(priority = 5)
	public void DeleteMethodTest() throws IOException {
		APIResources resourceAPI = APIResources.valueOf("CreatePostAPI");
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Starts ");
		res = RestAssured.given().spec(requestSpecification()).header("Cookie", "token=" + token);
		resspec = new ResponseSpecBuilder().build();
		response = res.when().delete(resourceAPI.getResource() + "/" + Bookingid).then().spec(resspec).extract()
				.response();
		Assert.assertEquals(response.getStatusCode(), 201);
		ExtentReportListener.logApiDetails(res, response);
		logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName() + " Ends ");

	}

}
