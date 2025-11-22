package apitests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserApiTest extends BaseApiTest{

    String body = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
    String bodyUser = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";
    String bodyUserFailure = "{\"email\": \"sydney@fife\"}";

    @Test
    void createUserTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test
    void registerUserSuccessTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyUser)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("token", notNullValue());
    }

    @Test
    void registerUserUnsuccessfulTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyUserFailure)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}
