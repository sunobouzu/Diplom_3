import UserApi.UserApi;
import generators.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import factories.WebDriverFactory;
import factories.BrowserType;

import static org.junit.Assert.assertTrue;

public class RegistrationTests {

    private WebDriver driver;
    private pageObjects.RegisterPage registerPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        registerPage = new pageObjects.RegisterPage(driver);
        registerPage.openRegisterPage();
    }

    @Test
    public void testSuccessfulRegistration() {
        String name = UserGenerator.generateRandomName();
        String email = UserGenerator.generateUniqueEmail();
        String password = UserGenerator.generateRandomPassword(8);

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();


        pageObjects.LoginPage loginPage = new pageObjects.LoginPage(driver);
        loginPage.waitForLoginPageToBeVisible();
        assertTrue("Кнопка 'Войти' не найдена", loginPage.isSignInButtonDisplayed());
    }


    @Test
    public void testRegistrationWithInvalidatePassword() {
        String name = UserGenerator.generateRandomName();
        String email = UserGenerator.generateUniqueEmail();
        String password = UserGenerator.generateInvalidPassword(1);

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();


        assertTrue("Некорректный пароль", registerPage.isErrorMessageCorrect("Некорректный пароль"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
