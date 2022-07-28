import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresinTests {

    @Test
    @AllureId("11366")
    @Tag("totalCount")
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
    @AllureId("11367")
    @Tag("specificEmail")
    @DisplayName("Проверка email конкретного пользователя")
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
    @AllureId("11368")
    @Tag("groovy")
    @DisplayName("Проверки email и имени, используя Groovy")
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
    @AllureId("11369")
    @DisplayName("Проверка создания пользователя")
    @Tag("createUser")
    void createUser() {
        UserData user = new UserData("morpheus", "leader");
        given()
                .spec(Specs.request)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .body("name", is(user.getName()), "job", is(user.getJob()));
    }

    @Test
    @AllureId("11370")
    @Tag("deleteUser")
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
    @AllureId("11371")
    @Tag("unsuccessfulLogin")
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
