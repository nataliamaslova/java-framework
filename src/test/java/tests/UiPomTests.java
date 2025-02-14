package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Story("UI POM tests")
@Tag(("ui"))
public class UiPomTests extends BaseTest {
    @Test
    public void loginPomTest() {
        LoginPage loginPage = new LoginPage(driver, longWait);
        loginPage.login();

        assertThat(driver.getCurrentUrl()).contains("login-sucess");
    }
}
