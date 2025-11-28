package apitests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import models.lombok.UsersListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserSpec.*;

public class StatusApiTest extends BaseApiTest {

    @Test
    @Description("Отправляет GET запрос и выводит список пользователей")
    @DisplayName("Получить список пользователей")
    void getListUsersTest(){
        UsersListResponse response = Allure.step("Выводим список пользователей", () -> given()
                .queryParam("page", 2)

                .when()
                .get("/users")

                .then()
                .spec(userListResponseSpec)
                .extract().as(UsersListResponse.class));

        Allure.step("Проверяем ответ", () -> {
            assertEquals(2, response.getPage(), "Номер страницы неверный");
        });

    }

    @Test
    @Description("Отправляет GET запрос и проверяет что такого пользователя нет")
    @DisplayName("Проверить что пользователь не найден")
    void checkUserNotFoundTest(){
        Allure.step("Запрашиваем не существующего пользователя", () ->  given(userRequestSpec)

                .when()
                .get("/users/23")

                .then()
                .spec(userNotFoundSpec));
    }

}
