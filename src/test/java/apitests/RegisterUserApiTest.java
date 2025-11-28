package apitests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;

import models.lombok.RegisterResponseModel;
import models.lombok.RegisterUserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RegisterSpec.*;

public class RegisterUserApiTest extends BaseApiTest{

    RegisterUserModel bodyUser = new RegisterUserModel();

    @Test
    @Description("Отправляет POST запрос и регистрирует пользователя")
    @DisplayName("Регистрация  пользователя")
    void registerUserSuccessTest() {
        bodyUser.setEmail("eve.holt@reqres.in");
        bodyUser.setPassword("pistol");

        RegisterResponseModel registerResponseModel =  Allure.step("Выполняем регистрацию пользователя", () ->
        given(registerRequestSpec)
                .body(bodyUser)

                .when()
                .post("/register")

                .then()
                .spec(registerResponseSpec)
                .extract().as(RegisterResponseModel.class));

        Allure.step("Проверяем ID и token ответа", () -> {
        assertEquals(4, registerResponseModel.getId());
        assertEquals("QpwL5tke4Pnpja7X4", registerResponseModel.getToken());
        });

    }

    @Test
    @Description("Отправляет POST запрос на регистрацию")
    @DisplayName("Неуспешная регистрация  пользователя")
    void registerUserUnsuccessfulTest() {
        bodyUser.setEmail("sydney@fife");

        RegisterResponseModel registerResponseModel =  Allure.step("Выполняем неуспешную регистрацию пользователя", () ->
        given(registerRequestSpec)
                .body(bodyUser)

                .when()
                .post("/register")

                .then()
                .spec(registerFailedResponseSpec)
                .extract().as(RegisterResponseModel.class));

        Allure.step("Проверяем содержимое ответа при неуспешной регистрации", () -> {
        assertEquals("Missing password", registerResponseModel.getError());
        });
    }
}
