package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    private final By userNameInputLocator = By.xpath("//label[contains(text(),'Имя')]/following-sibling::input");
    private final By emailInputLocator = By.xpath("//label[contains(text(),'Логин')]/following-sibling::input");
    private final By constructorButtonLocator = By.xpath("//p[contains(text(),'Конструктор')]");
    private final By burgerLogoLocator = By.xpath("//div[contains(@class, 'AppHeader_header__logo')]");
    private final By logoutButtonLocator = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем email пользователя")
    public String getEmail() {
        waitForElementToBeVisible(emailInputLocator);
        return driver.findElement(emailInputLocator).getAttribute("value");
    }

    @Step("Кликаем на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        waitForElementToBeClickable(constructorButtonLocator);
        driver.findElement(constructorButtonLocator).click();
    }

    @Step("Кликаем на логотип 'Старлайт Бургер'")
    public void clickBurgerLogo() {
        waitForElementToBeClickable(burgerLogoLocator);
        driver.findElement(burgerLogoLocator).click();
    }

    @Step("Выход из аккаунта")
    public void logoutUser() {
        waitForElementToBeClickable(logoutButtonLocator);
        driver.findElement(logoutButtonLocator).click();
    }

    @Step("Проверка, что страница профиля открыта")
    public boolean isProfilePageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInputLocator));
        return isElementDisplayed(userNameInputLocator);
    }
}
