
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.config.SSLConfig.sslConfig;
import static org.junit.Assert.assertEquals;

public class XAPITests {

    //String BaseURL = "https://reqres.in/api";
    //String BaseURL = "https://index.hu";
    @BeforeAll
    public static void setup() {
        RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        baseURI = "https://index.hu";
    }


    @Test
    public void passIng() {


        given()
                // WHEN
                .when()
                .get()
        // THEN
        .then()
        //.statusCode(200);
        .log().all();

        JSONObject data = new JSONObject();
        //RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        // GIVEN
        Response response = given()
                // WHEN
                .when()
                .get();
                // THEN
                //.then()
                //.statusCode(200);
                //.log().all();
        Assertions.assertEquals(response.statusCode(), 200);
        /*
        String expected = "asd";
        String actual = "asd";

        Assertions.assertEquals(expected, actual);

         */

    }
    /*
    @Test
    public void createUser() {

        JSONObject data = new JSONObject();

        data.put("name", "NewUser1");
        data.put("job", "Testing");
        RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        // GIVEN
        given()
                .contentType(ContentType.JSON)
                .body(data.toString())

                // WHEN
                .when()
                .post(BaseURL + "/users")

                // THEN
                .then()
                .statusCode(201)
                .body("name", equalTo("NewUser1"))
                .body("job", equalTo("Testing"))
                .log().all();

    }

    @Test
    public void getUser() {  //Failed Test

        // GIVEN
        given()//.relaxedHTTPSValidation() // due to HTTPS issue
                .contentType(ContentType.JSON)

                // WHEN
                .when()
                .get(BaseURL + "/users/2")

                // THEN
                .then()
                .statusCode(200)
                .body("data.first_name", equalTo("Janet1"))
                .log().all();

    }
*/
}