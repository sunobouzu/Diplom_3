package factories;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;




public class WebDriverFactory {
    public static WebDriver createWebDriver(BrowserType browserType) {
        WebDriver driver;

        switch (browserType) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tochka1142\\chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tochka1142\\yandexdriver.exe");
                //System.setProperty("webdriver.chrome.driver", "/Users/Tochka1142/yandexdriver");
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(yandexOptions);
                break;


            default:
                throw new IllegalArgumentException("Browser is not supported: " + browserType);
        }

        return driver;
    }
}
