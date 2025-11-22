package apitests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class StatusApiTest extends BaseApiTest {

    @Test
    void getListUsersTest(){
        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data.id", hasItem(7));

    }

    @Test
    void checkUserNotFoundTest(){
        given()
                .when()
                .get("/api/users/23")
                .then()
                .statusCode(404);

    }

}
