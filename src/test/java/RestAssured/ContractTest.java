package RestAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;


import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class ContractTest {
    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .build();

    @Test //data match json schema
    public void getMethod(){
        given()
                .spec(requestSpec)
                .basePath("/api/unknown")

                .when()
                .get()

                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath("userSchema/jsonSchema.json"));

    }
}
