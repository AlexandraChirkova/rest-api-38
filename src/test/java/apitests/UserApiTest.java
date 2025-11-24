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
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
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
                .post("/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("token", notNullValue());
    }

    @Test
    @Description("Отправляет POST запрос на регистрацию")
    @DisplayName("Неуспешная регистрация  пользователя")
    void registerUserUnsuccessfulTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyUserFailure)
                .when()
                .post("/register")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}
