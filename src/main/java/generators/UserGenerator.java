package generators;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    private static final String EMAIL_DOMAIN = "@yandex.com";

    @Step("Генерация уникального email")
    public static String generateUniqueEmail() {
         String randomEmailPart = RandomStringUtils.randomAlphabetic(8);
         return randomEmailPart + EMAIL_DOMAIN;
    }

    @Step("Генерация валидного пароля")
    public static String generateRandomPassword(int length) {
        return RandomStringUtils.randomAlphanumeric(Math.max(length, 6));
    }

    @Step("Генерация недопустимого пароля")
    public static String generateInvalidPassword(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }


    @Step("Генерация рандомного имени")
    public static String generateRandomName() {
        return RandomStringUtils.randomAlphabetic(8);
    }
}
