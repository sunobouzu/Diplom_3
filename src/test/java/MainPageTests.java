import factories.BrowserType;
import factories.WebDriverFactory;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.RegisterPage;

public class MainPageTests extends BaseTest {

    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);


        email = UserGenerator.generateUniqueEmail();
        password = UserGenerator.generateRandomPassword(8);
        name = UserGenerator.generateRandomName();


        mainPage.openMainPage();
        registerPage.openRegisterPage();
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();


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
