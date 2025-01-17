import UserApi.UserApi;
import factories.BrowserType;
import factories.WebDriverFactory;
import generators.UserGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import pageObjects.ForgotPasswordPage;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.RegisterPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTests extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    private ForgotPasswordPage forgotPasswordPage;

    private String email;
    private String password;
    private String name;
    private UserApi userApi;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userApi = new UserApi();

        email = UserGenerator.generateUniqueEmail();
        password = UserGenerator.generateRandomPassword(8);
        name = UserGenerator.generateRandomName();

        UserApi.UserData user = new UserApi.UserData(email, password, name);
        ValidatableResponse response = userApi.registerUser(user);
        assertEquals(200, response.extract().statusCode());
        assertTrue("Регистрация не удалась", response.extract().jsonPath().getBoolean("success"));
        accessToken = response.extract().jsonPath().getString("accessToken");

    }

    @Test
    public void testLoginFromMainPage() {
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Ошибка: переход на главную страницу не произошел", mainPage.isTitleDisplayed());
    }

    @Test
    public void testLoginFromProfileLink() {
        mainPage.openMainPage();
        mainPage.clickProfileLink();
        loginPage.waitForLoginPageToBeVisible();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Ошибка: переход на главную страницу не произошел", mainPage.isTitleDisplayed());
    }

    @Test
    public void testLoginFromRegisterPage() {
        registerPage.openRegisterPage();
        registerPage.clickRegisterLink();
        loginPage.waitForLoginPageToBeVisible();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Ошибка: переход на главную страницу не произошел", mainPage.isTitleDisplayed());
    }

    @Test
    public void testLoginFromForgotPasswordPage() {
        loginPage.openLoginPage();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickSignInLink();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Ошибка: переход на главную страницу не произошел", mainPage.isTitleDisplayed());
    }
}
