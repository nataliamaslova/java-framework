import controllers.UserController;
import io.restassured.response.Response;
import models.AddUserResponse;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static io.restassured.RestAssured.given;

public class SmokeApiTests {
    UserController userController = new UserController();

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

    @Test
    void createUserControllerTest() {
        User user = new User(0,
                "username",
                "firstName",
                "lastName",
                "email",
                "password",
                "phone",
                0);
        User userBuilder = User.builder()
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phone("password")
                .userStatus(0)
                .build();

        Response response = userController.createUser(user);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }
}
