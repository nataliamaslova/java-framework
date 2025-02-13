import controllers.UserController;
import io.restassured.response.Response;
import models.AddUserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static constants.CommonConstants.BASE_URI;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static io.restassured.RestAssured.given;
import static testdata.TestData.DEFAULT_USER;
import static testdata.TestData.INVALID_USER;

public class SmokeApiTests {
    UserController userController = new UserController();

//    private static String body = """
//            {
//              "id": 0,
//              "username": "string",
//              "firstName": "string",
//              "lastName": "string",
//              "email": "string",
//              "password": "string",
//              "phone": "string",
//              "userStatus": 0
//            }
//            """;

    @Test
    public void simpleTest() {
        // AAA = Arrange -> Act -> Assert

        Response response = given()
                        .header("accept", "application/json")
                        .header("Content-Type", " application/json")
                        .baseUri(BASE_URI)
                        .body(DEFAULT_USER)
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
                .baseUri(BASE_URI)
                .body(DEFAULT_USER)
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
        Response response = userController.createDefaultUser();
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }

    @Test
    void createUserControllerTest2() {
        Response response = userController.createDefaultUser(INVALID_USER);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }
}
