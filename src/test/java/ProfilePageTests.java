import UserApi.UserApi;
import factories.BrowserType;
import factories.WebDriverFactory;
import generators.UserGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.ProfilePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfilePageTests extends BaseTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private String email;
    private String password;
    private String name;

    private UserApi userApi;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        registerPage = new pageObjects.RegisterPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        userApi = new UserApi();


        name = UserGenerator.generateRandomName();
        email = UserGenerator.generateUniqueEmail();
        password = UserGenerator.generateRandomPassword(8);


        UserApi.UserData user = new UserApi.UserData(email, password, name);
        ValidatableResponse response = userApi.registerUser(user);
        assertEquals(200, response.extract().statusCode());
        assertTrue("Регистрация не удалась", response.extract().jsonPath().getBoolean("success"));
        accessToken = response.extract().jsonPath().getString("accessToken");

        loginPage.openLoginPage();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assert.assertTrue("Ошибка: переход на главную страницу не произошел", mainPage.isTitleDisplayed());
    }

    @Test
    public void testGoToProfilePage() {
        mainPage.openMainPage();
        mainPage.clickProfileLink();
        assertTrue("Ошибка: Личный кабинет не открыт", profilePage.isProfilePageDisplayed());
    }

    @Test
    public void testGoToConstructorFromProfile() {
        mainPage.openMainPage();
        mainPage.clickProfileLink();
        profilePage.clickConstructorButton();
        assertTrue("Ошибка: Конструктор не открыт", mainPage.isTitleDisplayed());
    }

    @Test
    public void testGoToConstructorFromLogo() {
        mainPage.openMainPage();
        mainPage.clickProfileLink();
        profilePage.clickBurgerLogo();
        assertTrue("Ошибка: Конструктор не открыт", mainPage.isTitleDisplayed());
    }

    @Test
    public void testOutFromProfile() {
        mainPage.openMainPage();
        mainPage.clickProfileLink();
        profilePage.logoutUser();
        assertTrue("Не вышли на страницу авторизации", loginPage.isSignInButtonDisplayed());
    }
}
