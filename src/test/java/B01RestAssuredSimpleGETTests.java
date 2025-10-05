import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.config.SSLConfig.sslConfig;

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
    // 3  New test case
    // 4. Add with pathParams given().pathParam("id", "1") + path
    // 5. GET with queryParams given().queryParam("id","3").queryParam("id","4") NO path

    // 6. BeforeAll RestAssured.config = RestAssured.config() + baseURI

    @Test
    public void simpleGet() {
        given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .when()
                .get("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200).log().all();
    }
}