import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresinTests {

    @Test
    @DisplayName("Проверка общего количества пользователей")
    void checkTotalCountOfUsers() {
        given()
                .spec(Specs.request)
                .get("/users?page=2")
                .then()
                .spec(Specs.response)
                .body("total", is(12));
    }

    @Test
    @DisplayName("Првоерка email конкретного пользователя")
    void checkSpecificEmail() {
        List<UserData> users = given()
                .spec(Specs.request)
                .get("/users?page=2")
                .then()
                .spec(Specs.response)
                .body("total", is(12))
                .extract().jsonPath().getList("data", UserData.class);
        assertEquals("byron.fields@reqres.in", users.get(3).getEmail());
    }

    @Test
    @DisplayName("Проверки, используя Groovy")
    void checkEmailUsingGroovy() {
        given()
                .spec(Specs.request)
                .get("/users?page=2")
                .then()
                .spec(Specs.response)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("byron.fields@reqres.in"))
                .body("data.findAll{it.first_name}.first_name.flatten()", hasItem("Byron"));
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    void createUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\", \"id\": \"598\", \"createdAt\": \"2022-06-07T11:53:47.580Z\" }";

        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .body("name", is("morpheus"));
    }

    @Test
    @DisplayName("Проверка успешного удаления пользователя")
    void deleteUserStatusCode204() {
        given()
                .spec(Specs.request)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации")
    void checkUnsuccessfulLogin() {
        String body = "{ \"email\": \"peter@klaven\" }";
        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
