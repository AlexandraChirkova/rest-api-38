package apitests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;

import models.lombok.UserModel;
import models.lombok.UserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserSpec.userRequestSpec;
import static specs.UserSpec.userResponseSpec;

public class CreateUserApiTest extends BaseApiTest{

    UserModel body = new UserModel();

    @Test
    @Description("Отправляет POST запрос и создает юзера")
    @DisplayName("Создать пользователя")
    void createUserTest() {

        body.setName("morpheus");
        body.setJob("leader");

        UserResponseModel response = Allure.step("Создаем пользователя", () -> given(userRequestSpec)
                .body(body)

                .when()
                .post("/users")

                .then()
                .spec(userResponseSpec)
                .extract().as(UserResponseModel.class));

        Allure.step("Проверяем Имя пользователя и должность", () -> {
        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());
        });

    }
}
