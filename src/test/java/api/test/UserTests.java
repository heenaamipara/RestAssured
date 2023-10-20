package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
 
	
		Faker faker;
		User userPayload;
		
		@BeforeClass
		public void setupData() {
			faker = new Faker();
			userPayload = new User();
			
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setUsername("Hena");
			userPayload.setFirstName("Heena");
			userPayload.setLastName("Panchani");
			userPayload.setEmail("heena@gmail.com");
			userPayload.setPassword("1234567");
			userPayload.setPhone("5438493589");
		}
		
		@Test(priority=1)
		public void testpostUser() {
			Response response=UserEndpints.createUser(userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
		}
		
		@Test(priority=2)
		public void testGetUserByName() {
			Response response=UserEndpints.readUser(this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
		}
		
		@Test(priority=3)
		public void testUpdateUserByName() {
			
			userPayload.setFirstName("XYZ");
			userPayload.setLastName("ABC");
			Response response=UserEndpints.UpdateUser(this.userPayload.getUsername(), userPayload);
			
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			Response response_update=UserEndpints.readUser(this.userPayload.getUsername());
			
			response_update.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(), 200);
		}
		
		@Test(priority=4)
		public void testDeleteUser() {
			Response response=UserEndpints.deleteUser(this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
		}
		
}

