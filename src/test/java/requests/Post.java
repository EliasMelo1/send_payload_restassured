package requests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import models.request.LoginPayload;

import static io.restassured.RestAssured.*;


public class Post {
    public ValidatableResponse login(LoginPayload body){
        baseURI = "https://serverest.dev";
        return given().
                contentType(ContentType.JSON).
                log().all().
                body(body).
                when().
                post("/login").
                then().
                log().all();
    }
}
