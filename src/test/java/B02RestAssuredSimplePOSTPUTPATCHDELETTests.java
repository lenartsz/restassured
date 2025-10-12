import com.github.cliftonlabs.json_simple.JsonArray;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class B02RestAssuredSimplePOSTPUTPATCHDELETTests {

    //0. https://jsontostring.com/

    @BeforeAll
    public static void setup() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.restful-api.dev/objects")
                .setRelaxedHTTPSValidation()
                .addHeader("Accept","application/json")
                .addHeader("Content-Type","application/json").build();

        RestAssured.requestSpecification = requestSpec;

        ResponseSpecification globalResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL) // Log all response details
                .build();

        // Set it globally
        RestAssured.responseSpecification = globalResponseSpec;

    }

    @Test
    public void simpleGet() {
        given()
                .when()
                .get()
                .then();
    }

    @Test
    public void simplePost() {

        String requestBody =
        "{\"name\":\"AppleMacBookPro16\",\"" +
                        "data\":{\"" +
                        "year\":2019,\"" +
                        "price\":1849.99,\"" +
                        "CPUmodel\":\"IntelCorei9\",\"" +
                        "Harddisksize\":\"1TB\"}}";

        given()
                .body(requestBody)             // Attach the JSON body
                .when()
                .post()                // Endpoint for the POST request
                .then()
                .statusCode(200);         // Validate the status code
    }

    @Test
    public void simplePut() {
        given()
                .when()
                .get()
                .then();
    }

    @Test
    public void simplePatch() {
        given()
                .when()
                .get()
                .then();
    }

    @Test
    public void simpleDelete() {
        given()
                .when()
                .get()
                .then();
    }
}