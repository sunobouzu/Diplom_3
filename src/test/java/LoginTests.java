import generators.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import factories.WebDriverFactory;
import factories.BrowserType;
import pageObjects.MainPage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pageObjects.ForgotPasswordPage;

import static org.junit.Assert.assertTrue;

public class LoginTests {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    private ForgotPasswordPage forgotPasswordPage;

    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        email = UserGenerator.generateUniqueEmail();
        password = UserGenerator.generateRandomPassword(8);
        name = UserGenerator.generateRandomName();

        mainPage.openMainPage();
        registerPage.openRegisterPage();
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

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
        loginPage.clickForgotPasswordLink();

        forgotPasswordPage.clickSignInLink();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Ошибка: переход на главную страницу не произошел", mainPage.isTitleDisplayed());
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
