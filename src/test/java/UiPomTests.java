import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UiPomTests extends BaseTest {
    @Test
    public void loginPomTest() {
        LoginPage loginPage = new LoginPage(driver, longWait);
        loginPage.login();

        assertThat(driver.getCurrentUrl()).contains("login-sucess");
    }
}
