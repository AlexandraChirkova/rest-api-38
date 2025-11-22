package apitests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    public static final String BASE_URL = "https://reqres.in";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("x-api-key", "reqres-free-v1")
                .build();
    }
}
