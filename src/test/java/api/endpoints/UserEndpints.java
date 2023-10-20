package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payload.User;

//userEndpoints.java file
//creates for CRUD operation

public class UserEndpints {

	public static Response createUser(User payload) { 
		Response response=given().contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(payload)
				.when()
				.post(Routes.post_url);
		
		return response;
	}
	
	public static Response readUser(String username)
	{
		Response response=given().pathParam("username",username)
				.when()
				.get(Routes.get_url);
		
		return response;
	}
	
	public static Response UpdateUser(String username, User payload) { 
		Response response=given().contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(payload).pathParam("username", username)
				.when()
				.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String username) {
		Response response=given().pathParam("username",username)
				.when()
				.get(Routes.get_url);
		
		return response;
	}
}
