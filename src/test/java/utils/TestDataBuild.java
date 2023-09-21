package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.javafaker.Faker;

import pojo.Auth;
import pojo.CreatePost;
import pojo.bookingdate;

public class TestDataBuild {
	
	public Auth authToken() {
		Auth auth=new Auth();
		auth.setUsername(Constants.USERNAME);
		auth.setPassword(Constants.PASSWORD);
		return auth;
	}

	public CreatePost creatPost() {
		Faker faker = new Faker();
		CreatePost post=new CreatePost();
		bookingdate bookings=new bookingdate();
		post.setFirstname(faker.name().firstName());
		post.setLastname(faker.name().lastName());
		post.setTotalprice(faker.number().randomDigit());
		post.setDepositpaid(true);
		 Date date = new Date();
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	       String str = formatter.format(date);
		bookings.setCheckin(str);
		bookings.setCheckout(str);
		post.setBookingdates(bookings);
		
		return post;
		
	}
	
}
