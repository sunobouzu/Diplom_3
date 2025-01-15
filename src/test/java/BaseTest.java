import factories.BrowserType;
import factories.WebDriverFactory;
import generators.UserGenerator;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pageObjects.*;

public abstract class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected ProfilePage profilePage;

    protected String email;
    protected String password;
    protected String name;

    @Step("Инициализация теста")
    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        profilePage = new ProfilePage(driver);

        generateTestUser();
        registerUser();
    }

    void generateTestUser() {
        email = UserGenerator.generateUniqueEmail();
        password = UserGenerator.generateRandomPassword(8);
        name = UserGenerator.generateRandomName();
    }

    void registerUser() {
        mainPage.openMainPage();
        registerPage.openRegisterPage();
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
