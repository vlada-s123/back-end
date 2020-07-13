package RestAssured;

import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.*;


public class TestAPI{
        RequestSpecification requestSpec=new RequestSpecBuilder()
        .setBaseUri("https://reqres.in")
        .setContentType(ContentType.JSON)
        .build();

@Test//GET метод
public void checkGetResponseStatusCode(){

        given()//GET request
        .spec(requestSpec)
        .basePath("/api/users?page=2")
        .log().body()

        .when()
        .get()

        .then()
        .log().body()
        .statusCode(200);
        }

@Test//POST метод
public void checkPostCreateRequest(){
        User user=User.builder()
        .name("morpheus")
        .job("leader")
        .build();

        given()
        .spec(requestSpec)
        .basePath("/api/users")
        .log().body()

        .when()
        .body(user)
        .post()

        .then()
        .log().body()
        .statusCode(201)
        .body("name",equalTo("morpheus"));


        }

@Test//PUT Update
public void checkPutUpdateMethod(){
        given()
        .spec(requestSpec)
        .basePath("/api/users/2")
        .log().body()

        .when()
        .body("{\"name\" : \"morpheus\", \"job\" : \"zion resident\"}")
        .put()

        .then()
        .log().body()
        .statusCode(200)
        .time(lessThan(5000l))
        .assertThat().body("job",equalTo("zion resident"));
        }
@Test//DELETE
public void checkDeleteMethod(){
        given()
        .spec(requestSpec)
        .basePath("/api/users/2")

        .when()
        .delete()

        .then().log().status()
        .statusCode(204);
        }

@Test//get list
public void checkGetListMethod(){
        given()
        .spec(requestSpec)
        .basePath("/api/unknown")

        .when()
        .get()

        .then().log().status()
        .log().body()
        .statusCode(200);
        }
        }

