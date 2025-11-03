import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import static io.restassured.RestAssured.*;
import static io.restassured.config.SSLConfig.sslConfig;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import java.util.concurrent.TimeUnit;

//--------------------------------------------------------------------
//Origin of the Structure: BDD (Behavior-Driven Development):
// .given() --> Specify request details (headers, parameters, body, etc.)
// .when() --> Specify the HTTP method and endpoint
// .then() --> Validate the response
// .extract() --> Optionally extract response data
//--------------------------------------------------
// 0. https://documenter.getpostman.com/view/4016432/the-dog-api/RW81vZ4Z#11045fd3-0890-4f23-b5ea-f268a3f5eced
// 1. log().all()
// 2. Change URL to v2 instead of v1
// 3. New test cases GET with queryParams given().queryParam("mime_types","jpg").queryParam("format","json") NO path
// 4. New test cases GET with pathParams given().pathParam("id", "AcXu5OATT") + path /{id} or just {id}
// 5. Use global variables
// 6. BeforeAll RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation()) + baseURI
    /* 7. BeforeAll
     RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.thedogapi.com/v1/images")
                .setRelaxedHTTPSValidation()
                .addHeader("Content-Type","application/json")
                .log(LogDetail.ALL).build();

        RestAssured.requestSpecification = requestSpec;
    /* 8.
        ResponseSpecification globalResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(5000L), TimeUnit.MILLISECONDS)
                .log(LogDetail.ALL) // Log all response details
                .build();

        // Set it globally
        RestAssured.responseSpecification = globalResponseSpec;
    */
//9. Change the pathParam test and use nonexisting id
//10. Override global responseSpecification check what happens and handle it with try/finally
//11. Use simple inline restassured validation status code and .body("id", equalTo("asd"))
//12. use Response for validation: JSON response.body().jsonPath().getString("breeds[0].weight.imperial")) and response.getTime();
//--------------------------------------------------------------------

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class B01RestAssuredSimpleGETTests {
    @Test
    @Order(1)
    public void simpleGet() {
        given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .when()
                .get("https://api.thedogapi.com/v1/images/" +
                        "search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1")
                .then().statusCode(200);
    }
}
