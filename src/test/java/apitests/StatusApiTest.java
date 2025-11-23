package apitests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class StatusApiTest extends BaseApiTest {

    @Test
    @Description("Отправляет GET запрос и выводит список пользователей")
    @DisplayName("Получить список пользователей")
    void getListUsersTest(){
        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .body("page", equalTo(2))
                .body("data.id", hasItem(7))
                .statusCode(200);

    }

    @Test
    @Description("Отправляет GET запрос и проверяет что такого пользователя нет")
    @DisplayName("Проверить что пользователь не найден")
    void checkUserNotFoundTest(){
        given()
                .when()
                .get("/api/users/23")
                .then()
                .statusCode(404);

    }

}
