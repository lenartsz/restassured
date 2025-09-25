
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.config.SSLConfig.sslConfig;
import static org.hamcrest.Matchers.equalTo;

public class APITests {

    //String BaseURL = "https://reqres.in/api";
    String BaseURL = "https://index.hu";
    @BeforeAll
    public static void setup() {
        RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
    }


    @Test
    public void passIng() {

        JSONObject data = new JSONObject();
        //RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        // GIVEN
        given()
                // WHEN
                .when()
                .get(BaseURL)
                // THEN
                .then()
                .statusCode(200)
                .log().all();

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