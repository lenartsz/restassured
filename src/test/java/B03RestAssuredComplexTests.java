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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class B03RestAssuredComplexTests {

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
    //9. Delete + validation GET in the same test


    private static String deviceId;
    private static String deviceName;
    private static String requestBody =
            "{\"name\":\"AppleMacBookPro16\",\"" +
                    "data\":{\"" +
                    "year\":2019,\"" +
                    "price\":1849.99,\"" +
                    "CPUmodel\":\"IntelCorei9\",\"" +
                    "Harddisksize\":\"1TB\"}}";

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

    @Order(1)
    @Test
    public void simplePost() {

        String body = requestBody;

        Response response = given()
                .body(body)             // Attach the JSON body
                .when()
                .post()                // Endpoint for the POST request
                .then().extract().response();


        deviceId = response.path("id");
        deviceName = response.path("name");

        Assertions.assertEquals("AppleMacBookPro16",deviceName);

    }
    @Order(2)
    @Test
    public void simpleGetParam() {

       Response response = given().pathParam("id", deviceId)
                .when()
                .get("{id}")
                .then().extract().response();

        Assertions.assertEquals("AppleMacBookPro16",deviceName);
    }
    @Order(3)
    @Test
    public void simplePut() {

        String body = requestBody.replace("AppleMacBookPro16","AppleMacBookPro20");

        Response response = given().pathParam("id", deviceId).body(body)
                .when()
                .put("{id}")
                .then().extract().response();

        String deviceName = response.path("name");

        Assertions.assertEquals("AppleMacBookPro20",deviceName);
    }
    @Order(4)
    @Test
    public void simplePatch() {

        String body =
                "{\"name\":\"AppleMacBookPro40\"}";
        Response response = given().pathParam("id",deviceId).body(body)
                .when()
                .patch("{id}")
                .then().extract().response();

        String deviceName = response.path("name");

        Assertions.assertEquals("AppleMacBookPro40",deviceName);
    }
    @Order(5)
    @Test
    public void simpleDelete() {
        Response response = given().pathParam("id", deviceId)
                .when()
                .delete("{id}")
                .then().extract().response();
        String deviceName = response.path("name");

        given().pathParam("id", deviceId)
                .when()
                .get("{id}")
                .then().statusCode(404);
    }
}