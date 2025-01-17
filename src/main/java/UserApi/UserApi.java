package UserApi;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi extends RestApi {
    private static final String LOGIN_URL = "/api/auth/login";
    private static final String REGISTER_URL = "/api/auth/register";
    private static final String USER_DATA_URL = "/api/auth/user";
    private static final String DELETE_URL = "/api/auth/user";


    public static class UserData {
        private String email;
        private String password;
        private String name;

        public UserData(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
    }

    @Step("Регистрация пользователя")
    public ValidatableResponse registerUser(UserData user) {
        return given()
                .spec(requestSpecification())
                .body(user)
                .when()
                .post(REGISTER_URL)
                .then();
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser(String email, String password) {
        return given()
                .spec(requestSpecification())
                .body(new UserData(email, password, null))
                .when()
                .post(LOGIN_URL)
                .then();
    }

    @Step("Удаление пользователя по токену")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(requestSpecification())
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_URL)
                .then()
                .log().all();
    }

}
