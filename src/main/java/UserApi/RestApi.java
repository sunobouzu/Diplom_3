package UserApi;


import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.qameta.allure.restassured.AllureRestAssured;

public class RestApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";


    protected RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured())
                .log().all();
    }
}
