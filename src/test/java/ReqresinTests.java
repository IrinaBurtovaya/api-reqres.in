import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresinTests {

    @Test
    @AllureId("11322")
    @Tag("totalUsersCount")
    @DisplayName("Проверка общего количества пользователей")
    void checkTotalCountOfUsers() {
        step("Проверка общего количества пользователей");
    }

    @Test
    @AllureId("11323")
    @Tag("specificEmail")
    @DisplayName("Проверка email конкретного пользователя")
    void checkSpecificEmail() {
        step("Проверка email конкретного пользователя");
    }

    @Test
    @AllureId("11324")
    @Tag("checkEmailGroovy")
    @DisplayName("Проверки email и имени, используя Groovy")
    void checkEmailUsingGroovy() {
        step("Проверки email и имени, используя Groovy");
    }

    @Test
    @AllureId("11325")
    @Tag("createUser")
    @DisplayName("Проверка создания пользователя")
    void createUser() {
        step("Проверка создания пользователя");
    }

    @Test
    @AllureId("11326")
    @Tag("deleteUser")
    @DisplayName("Проверка успешного удаления пользователя")
    void deleteUserStatusCode204() {
        step("Проверка успешного удаления пользователя");
    }

    @Test
    @AllureId("11327")
    @Tag("unsuccessfulLogin")
    @DisplayName("Проверка неуспешной авторизации")
    void checkUnsuccessfulLogin() {
        step("Проверка неуспешной авторизации");
    }
}
