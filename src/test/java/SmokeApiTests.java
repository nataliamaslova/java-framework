import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static io.restassured.RestAssured.given;

public class SmokeApiTests {
    private static String baseUrl = "https://petstore.swagger.io/v2/";
    private static String body = """
            {
              "id": 0,
              "username": "string",
              "firstName": "string",
              "lastName": "string",
              "email": "string",
              "password": "string",
              "phone": "string",
              "userStatus": 0
            }
            """;

    @Test
    public void simpleTest() {
        // AAA = Arrange -> Act -> Assert

        Response response = given()
                        .header("accept", "application/json")
                        .header("Content-Type", " application/json")
                        .baseUri(baseUrl)
                        .body(body)
                .when()
                        .post("user")
                    .andReturn();

        int actualCode = response.getStatusCode();

        Assertions.assertEquals(200, actualCode);
    }

    @Test
    public void checkUserResponseBody() {
        given()
                .header("accept", "application/json")
                .header("Content-Type", " application/json")
                .baseUri(baseUrl)
                .body(body)
            .when()
                .post("user")
            .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", notNullValue(String.class));
    }
}
