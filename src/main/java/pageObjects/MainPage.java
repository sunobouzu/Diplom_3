package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage extends BasePage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By loginButtonLocator = By.xpath("//button[text()='Войти в аккаунт']");
    private  final By profileLinkLocator = By.xpath("//p[text()='Личный Кабинет']");
    private final By titleLocator = By.xpath("//h1[text()='Соберите бургер']");
    private final By bunsSectionLocator = By.xpath("//span[text()='Булки']");
    private final By saucesSectionLocator = By.xpath("//span[text()='Соусы']");
    private final By fillingsSectionLocator = By.xpath("//span[text()='Начинки']");

    private final By selectedBunsLocator = By.xpath("//div[contains(@class, 'current')]/span[text()='Булки']");
    private final By selectedSaucesLocator = By.xpath("//div[contains(@class, 'current')]/span[text()='Соусы']");
    private final By selectedFillingsLocator = By.xpath("//div[contains(@class, 'current')]/span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Кликаем по кнопке 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }

    @Step("Кликаем по ссылке 'Личный Кабинет'")
    public void clickProfileLink() {
        WebElement profileLink = driver.findElement(profileLinkLocator);
        profileLink.click();
    }

    @Step("Проверяем заголовок 'Соберите бургер'")
    public boolean isTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
        WebElement titleElement = driver.findElement(titleLocator);
        return titleElement.isDisplayed();
    }

    @Step("Кликаем на раздел 'Булки'")
    public void selectBunsSection() {
        waitForElementToBeClickable(bunsSectionLocator);
        WebElement bunsSection = driver.findElement(bunsSectionLocator);
        bunsSection.click();
    }

    @Step("Кликаем на раздел 'Соусы'")
    public void selectSaucesSection() {
        waitForElementToBeClickable(saucesSectionLocator);
        WebElement saucesSection = driver.findElement(saucesSectionLocator);
        saucesSection.click();
    }

    @Step("Кликаем на раздел 'Начинки'")
    public void selectFillingsSection() {
        waitForElementToBeClickable(fillingsSectionLocator);
        WebElement fillingsSection = driver.findElement(fillingsSectionLocator);
        fillingsSection.click();
    }

    @Step("Проверка текущего выбранного раздела 'Булки'")
    public boolean isCurrentlySelectedBuns() {
        WebElement selectedBuns = driver.findElement(selectedBunsLocator);
        return selectedBuns.isDisplayed();
    }

    @Step("Проверка текущего выбранного раздела 'Соусы'")
    public boolean isCurrentlySelectedSauces() {
        WebElement selectedSauces = driver.findElement(selectedSaucesLocator);
        return selectedSauces.isDisplayed();
    }

    @Step("Проверка текущего выбранного раздела 'Начинки'")
    public boolean isCurrentlySelectedFillings() {
        WebElement selectedFillings = driver.findElement(selectedFillingsLocator);
        return selectedFillings.isDisplayed();
    }

}
