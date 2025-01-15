import factories.BrowserType;
import factories.WebDriverFactory;
import generators.UserGenerator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.ProfilePage;

import static org.junit.Assert.assertTrue;

public class ProfilePageTests {

    private WebDriver driver;
    private pageObjects.RegisterPage registerPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        registerPage = new pageObjects.RegisterPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);


        name = UserGenerator.generateRandomName();
        email = UserGenerator.generateUniqueEmail();
        password = UserGenerator.generateRandomPassword(8);


        registerPage.openRegisterPage();
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
