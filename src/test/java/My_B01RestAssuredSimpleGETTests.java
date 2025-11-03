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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;

import java.io.File;
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
public class My_B01RestAssuredSimpleGETTests {
    String contenttype = "application/json";

    @BeforeAll
    public static void setup() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.thedogapi.com/v1/images")
                .setRelaxedHTTPSValidation()
                .addHeader("Content-Type","application/json")
                .log(LogDetail.ALL).build();

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
        given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .when().log().all()
                .get("https://api.thedogapi.com/v1/images/" +
                        "search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1")
                .then().statusCode(200).log().all();
    }

    @Test
    public void simpleGetQueryParam() {
        given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .queryParam("mime_types", "png")
                .when().log().all()
                .get("https://api.thedogapi.com/v1/images/" +
                        "search?size=med&format=json&has_breeds=true&order=RANDOM&page=0&limit=1")
                .then().statusCode(200).log().all().assertThat().body(containsString("png"));
    }

    @Order(1)
    @Test
    public void simpleGetPathParam() {

        responseSpecification.statusCode(400);
        try{
            given()
                    .pathParam("id","asd")
                    .when()
                    .get("{id}")
                    .then().statusCode(400);
        }finally {
            responseSpecification.statusCode(200);
        }



    }
    @Order(2)
    @Test
    public void simpleGetasd() {
        given().pathParam("id", "3dA-D-AIm")
                .when()
                .get("{id}")
                .then()
                .assertThat().body("id", equalTo("3dA-D-AIm"))
                .statusCode(200);
    }
    @Order(3)
    @Test
    public void simpleGetasdasd() {
        Response response = given().pathParam("id","3dA-D-AIm")
                .when()
                .get("{id}")
                .then().extract().response();

        Assertions.assertEquals(200, response.getStatusCode(),"asd");
        Assertions.assertEquals("45 - 55", response.body().jsonPath().getString("breeds[0].weight.imperial"));
        Assertions.assertTrue(response.getTime()<1000,"!!!!!!!!!!!"+response.getTime());

    }

}