package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private final By emailInputLocator = By.xpath("//label[contains(@class, 'text') and text()='Email']/following-sibling::input");
    private final By passwordInputLocator = By.xpath("//label[contains(@class, 'text') and text()='Пароль']/following-sibling::input");
    private final By loginButtonLocator = By.xpath("//button[text()='Войти']");
    private final By forgotPasswordLinkLocator = By.xpath("//a[text()='Восстановить пароль']");
    private final By titleLocator = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу логина")
    public void openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
    }

    @Step("Вводим email")
    public void enterEmail(String email) {
        waitForElementToBeClickable(emailInputLocator);
        driver.findElement(emailInputLocator).sendKeys(email);
    }

    @Step("Вводим пароль")
    public void enterPassword(String password) {
        waitForElementToBeClickable(passwordInputLocator);
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        waitForElementToBeClickable(loginButtonLocator);
        driver.findElement(loginButtonLocator).click();
    }

    @Step("Клик на ссылку 'Забыли пароль?'")
    public void clickForgotPasswordLink() {
        waitForElementToBeClickable(forgotPasswordLinkLocator);
        driver.findElement(forgotPasswordLinkLocator).click();
    }

    @Step("Ожидание, пока страница логина не станет видимой")
    public void waitForLoginPageToBeVisible() {
        waitForElementToBeVisible(titleLocator);
    }



    @Step("Проверяем, что кнопка 'Войти' отображается")
    public boolean isSignInButtonDisplayed() {
        waitForElementToBeClickable(loginButtonLocator);
        return isElementDisplayed(loginButtonLocator);
    }
}
