import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import static io.restassured.config.SSLConfig.sslConfig;
import io.restassured.builder.RequestSpecBuilder;
public class B01RestAssuredSimpleGETTests {

    //Origin of the Structure: BDD (Behavior-Driven Development):
    // .given() --> Specify request details (headers, parameters, body, etc.)
    // .when() --> Specify the HTTP method and endpoint
    // .then() --> Validate the response
    // .extract() --> Optionally extract response data
    //--------------------------------------------------
    // 0. https://restful-api.dev/
    // 1. log().all()
    // 2. Add "Accept" header with "application/text"
    // 3  New test cases
    // 4. Add with pathParams given().pathParam("id", "1") + path /{id}
    // 5. GET with queryParams given().queryParam("id","3").queryParam("id","4") NO path
    // 6. Use global variables
    // 7. BeforeAll RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()) + baseURI
    /* 8. BeforeAll
     RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.restful-api.dev/objects")
                .setRelaxedHTTPSValidation()
                .addHeader("Accept","application/json").log(LogDetail.ALL).build();

        RestAssured.requestSpecification = requestSpec;
    /* 8.
        ResponseSpecification globalResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL) // Log all response details
                .build();

        // Set it globally
        RestAssured.responseSpecification = globalResponseSpec;
    */


    @Test
    public void simpleGet() {
        given().relaxedHTTPSValidation()
                .header("Accept", "application/json")
                .when()
                .get("https://api.restful-api.dev/objects")
                .then().statusCode(200);
    }
}