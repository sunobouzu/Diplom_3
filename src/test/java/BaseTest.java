import factories.BrowserType;
import factories.WebDriverFactory;
import generators.UserGenerator;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import UserApi.UserApi;
import io.restassured.response.ValidatableResponse;

public abstract class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;

    protected String name;
    protected String accessToken;



    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (accessToken != null) {
            new UserApi().deleteUser(accessToken);
        }
    }
}
