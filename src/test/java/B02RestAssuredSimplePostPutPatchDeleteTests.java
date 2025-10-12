import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class B02RestAssuredSimplePostPutPatchDeleteTests {

    //0. https://jsontostring.com/
    //1. Create a new device with POST: given.body(requestBody)
    //2. Use simple GET with path param to check values
    //3. Use simple PUT to change name
    //4. Use simple PATCH to change name
    //5. Use simple Delete to change name

    @BeforeAll
    public static void setup() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.restful-api.dev/objects")
                .setRelaxedHTTPSValidation()
                .addHeader("Accept","application/json")
                .addHeader("Content-Type","application/json").log(LogDetail.ALL).build();

        RestAssured.requestSpecification = requestSpec;

        ResponseSpecification globalResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL) // Log all response details
                .build();

        // Set it globally
        RestAssured.responseSpecification = globalResponseSpec;

    }
    @Test
    public void simplePost() {

        String requestBody = "";

        given()
                .body(requestBody)
                .when()
                .post()
                .then();
    }
}