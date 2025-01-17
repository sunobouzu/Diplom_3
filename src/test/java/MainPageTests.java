import UserApi.UserApi;
import factories.BrowserType;
import factories.WebDriverFactory;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPageTests extends BaseTest {

    private String email;
    private String password;
    private String name;

    private UserApi userApi;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        userApi = new UserApi();

        email = UserGenerator.generateUniqueEmail();
        password = UserGenerator.generateRandomPassword(8);
        name = UserGenerator.generateRandomName();


        UserApi.UserData user = new UserApi.UserData(email, password, name);
        ValidatableResponse response = userApi.registerUser(user);
        assertEquals(200, response.extract().statusCode());
        assertTrue("Регистрация не удалась", response.extract().jsonPath().getBoolean("success"));
        accessToken = response.extract().jsonPath().getString("accessToken");

        mainPage.openMainPage();
        mainPage.clickLoginButton();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assert.assertTrue("Ошибка: переход на главную страницу не произошел", mainPage.isTitleDisplayed());
    }

    @Test
    @DisplayName("Переход в секцию 'Булки'")
    @Description("Проверяем, что переход в секцию 'Булки' работает корректно")
    public void testNavigateToBunsSection() {
        mainPage.selectSaucesSection();
        mainPage.selectBunsSection();
        Assert.assertTrue("Ошибка: Раздел 'Булки' не выбран", mainPage.isCurrentlySelectedBuns());
    }

    @Test
    @DisplayName("Переход в секцию 'Соусы'")
    @Description("Проверяем, что переход в секцию 'Соусы' работает корректно")
    public void testNavigateToSaucesSection() {
        mainPage.selectSaucesSection();
        Assert.assertTrue("Ошибка: Раздел 'Соусы' не выбран", mainPage.isCurrentlySelectedSauces());
    }

    @Test
    @DisplayName("Переход в секцию 'Начинки'")
    @Description("Проверяем, что переход в секцию 'Начинки' работает корректно")
    public void testNavigateToFillingsSection() {
        mainPage.selectFillingsSection();
        Assert.assertTrue("Ошибка: Раздел 'Начинки' не выбран", mainPage.isCurrentlySelectedFillings());
    }
}
