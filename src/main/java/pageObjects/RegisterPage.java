package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    private final By nameInputLocator = By.xpath("//label[contains(@class, 'text') and text()='Имя']/following-sibling::input");
    private final By emailInputLocator = By.xpath("//label[contains(@class, 'text') and text()='Email']/following-sibling::input");
    private final By passwordInputLocator = By.xpath("//label[contains(@class, 'text') and text()='Пароль']/following-sibling::input");
    private final By registerButtonLocator = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLinkLocator = By.xpath("//a[text()='Войти']");
    private final By errorMessageLocator = By.xpath(".//p[contains(text(),'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу регистрации")
    public void openRegisterPage() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Клик на ссылку 'Войти'")
    public void clickRegisterLink() {
        waitForElementToBeClickable(loginLinkLocator);
        driver.findElement(loginLinkLocator).click();
    }

    @Step("Заполнение имени")
    public void enterName(String name) {
        waitForElementToBeClickable(nameInputLocator);
        driver.findElement(nameInputLocator).sendKeys(name);
    }

    @Step("Заполнение email")
    public void enterEmail(String email) {
        waitForElementToBeClickable(emailInputLocator);
        driver.findElement(emailInputLocator).sendKeys(email);
    }

    @Step("Заполнение пароля")
    public void enterPassword(String password) {
        waitForElementToBeClickable(passwordInputLocator);
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        waitForElementToBeClickable(registerButtonLocator);
        driver.findElement(registerButtonLocator).click();
    }

    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        waitForElementToBeVisible(errorMessageLocator);
        return driver.findElement(errorMessageLocator).getText();
    }

    @Step("Проверяем, что текст ошибки для некорректного пароля совпадает")
    public boolean isErrorMessageCorrect(String expectedMessage) {
        try {
            return getErrorMessage().contains(expectedMessage);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
