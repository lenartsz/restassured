import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
//----------------------------------------------------------------
//0. Check https://restful-api.dev/
//1. Create a new device with POST: given.body(requestBody)
//2. Use simple GET with path param to check values
//3. Use simple PUT to change name
//4. Use simple PATCH to change name
//5. Use simple Delete to change name
//6. Use json schema validation
//----------------------------------------------------------------


public class My_B02RestAssuredSimplePostPutPatchDeleteTests {

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

        String requestBody = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";

        given()
                .body(requestBody)
                .when()
                .post()
                .then();
    }

    @Test
    public void simpleGet(){
        given().pathParam("id","ff8081819782e69e019a4a5ad3003cc6")
                .when()
                .get("/{id}")
                .then();
    }

    @Test
    public void simplePut() {

        String requestBody = "{\n" +
                "   \"name\": \"My Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";

        given().pathParam("id","ff8081819782e69e019a4a5ad3003cc6")
                .body(requestBody)
                .when()
                .put("/{id}")
                .then();
    }

    @Test
    public void simplePatch() {

        String requestBody = "{\"name\": \"Mymy Apple MacBook Pro 16\"}";

        given().pathParam("id","ff8081819782e69e019a4a6c98433d2a")
                .body(requestBody)
                .when()
                .put("/{id}")
                .then();
    }


    @Test
    public void simpleDelete() {
        given().pathParam("id","ff8081819782e69e019a4a5ad3003cc6")
                .when()
                .delete("/{id}")
                .then();
    }

    @Test
    public void simpleGetWithSchema() {
        File myObj = new File("src/test/java/schema.json");
        given()
                .when()
                .get()
                .then()
                .assertThat()
                .body(matchesJsonSchema(myObj));
    }


}