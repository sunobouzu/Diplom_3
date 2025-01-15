package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ForgotPasswordPage extends BasePage {

    private final By signInLinkLocator = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Кликаем на ссылку 'Войти'")
    public void clickSignInLink() {
        waitForElementToBeClickable(signInLinkLocator);
        driver.findElement(signInLinkLocator).click();
    }

}
