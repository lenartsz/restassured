import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

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
public class My_B03RestAssuredComplexTests {


    private static String name = "Apple MacBook Pro 16";
    private static String requestBody = "{\n" +
            "   \"name\": \""+name+"\",\n" +
            "   \"data\": {\n" +
            "      \"year\": 2019,\n" +
            "      \"price\": 1849.99,\n" +
            "      \"CPU model\": \"Intel Core i9\",\n" +
            "      \"Hard disk size\": \"1 TB\"\n" +
            "   }\n" +
            "}";

    private static String deviceId;

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

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        System.out.println("\nSTART-------------------"+testInfo.getDisplayName()+"---------------------START\n");
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        System.out.println("\nEND-------------------"+testInfo.getDisplayName()+"---------------------END\n");
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
        Response response = given().body(requestBody)
                .when()
                .post()
                .then().extract().response();

        deviceId = response.path("id");
        Assertions.assertEquals(name, response.path("name"));
    }

    @Order(2)
    @Test
    public void simpleGet() {
        System.out.println(deviceId);
        Response response = given().pathParam("id", deviceId)
                .when()
                .get("/{id}")
                .then().extract().response();
        Assertions.assertEquals(name, response.path("name"));
    }

    @Order(3)
    @Test
    public void simplePut() {

        String newName = "New Apple MacBook Pro 16";
        Response response = given()
                .pathParam("id", deviceId)
                .body(requestBody.replace(name,newName))
                .when()
                .put("/{id}")
                .then().extract().response();
        Assertions.assertEquals(newName, response.path("name"));
    }

    @Order(4)
    @Test
    public void simplePatch() {
        String newName = "Very New Apple MacBook Pro 16";
        Response response = given()
                .pathParam("id", deviceId)
                .body(requestBody.replace(name,newName))
                .when()
                .patch("/{id}")
                .then().extract().response();

        Assertions.assertEquals(newName, response.path("name"));
    }

    @Order(5)
    @Test
    public void simpleDelete() {
        Response response = given()
                .pathParam("id", deviceId)
                .when()
                .delete("/{id}")
                .then().extract().response();


        Assertions.assertEquals("Object with id = "+deviceId+" has been deleted.", response.path("message"));

        responseSpecification.statusCode(404);

        try{
            Response responseGet = given()
                    .pathParam("id", deviceId)
                    .when()
                    .get("/{id}")
                    .then().extract().response();
        }finally {
            responseSpecification.statusCode(200);
        }

    }

}