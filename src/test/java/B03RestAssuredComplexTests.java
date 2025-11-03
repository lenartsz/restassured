import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.*;


import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import io.restassured.matcher.RestAssuredMatchers.*;

//-------------------------------------------------------
//0. Check https://restful-api.dev/
//1. Create a new device with POST: given.body(requestBody)
//   Use Response to access to response details: then().extract().response();
//2. use global static variables to store id: deviceId = response.path("id") and
//   name
//3. Assert name
//4. Run get with param, using the generated id, use the same validation
//5. Take care of the Order of the tests
//6. Add separator before and after each test
//   @BeforeEach
//   public void beforeEach(TestInfo testInfo)
//   System.out.println("\nSTART-------------------"+testInfo.getDisplayName()+"---------------------START\n");
//7. Change name via PUT call, reuse requestBody + validation
//8. Change only the name with PATCH only the name in JSON + validation
//9. Delete and validate message + validation GET in the same test
//-------------------------------------------------------

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class B03RestAssuredComplexTests {

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

    /*
    @Order(1)
    @Test
    public void simplePost() {


    }
    */

    @Order(1)
    @Test
    public void simplePost() {



    }

}
