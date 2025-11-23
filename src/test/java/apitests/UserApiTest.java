package apitests;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserApiTest extends BaseApiTest{

    String body = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
    String bodyUser = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";
    String bodyUserFailure = "{\"email\": \"sydney@fife\"}";

    @Test
    @Description("Отправляет POST запрос и создает юзера")
    @DisplayName("Создать пользователя")
    void createUserTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .statusCode(201);
    }

    @Test
    @Description("Отправляет POST запрос и зарегистрировать пользователя")
    @DisplayName("Регистрация  пользователя")
    void registerUserSuccessTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyUser)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .body("id", equalTo(4))
                .body("token", notNullValue())
                .statusCode(200);
    }

    @Test
    @Description("Отправляет POST запрос на регистрацию пользователя")
    @DisplayName("Неуспешная регистрация  пользователя")
    void registerUserUnsuccessfulTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyUserFailure)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .body("error", equalTo("Missing password"))
                .statusCode(400);
    }
}
