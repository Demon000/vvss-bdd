package org.example.features.login;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.MCMusicSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/user_data.csv")
public class MCMusicStory {
    public WebDriver webDriver;

    public MCMusicStory() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        webDriver = new ChromeDriver(options);
    }

    @Steps
    public MCMusicSteps mcMusicSteps;

    String email, password, valid;

    @Test
    public void auth() {
        mcMusicSteps.init();
        mcMusicSteps.assertLoggedIn(false);
        mcMusicSteps.step_login(email, password);
        if(valid .equals("1")) {
            mcMusicSteps.assertLoggedIn(true);
            mcMusicSteps.search();
            mcMusicSteps.assertSearchResult();
            mcMusicSteps.addToCart();
            mcMusicSteps.toCart();
            mcMusicSteps.assertPrice();
            mcMusicSteps.step_logout();
            mcMusicSteps.assertLoggedIn(false);
        }
    }

    @Test
    public void loginSuccess() {
        mcMusicSteps.init();
        mcMusicSteps.assertLoggedIn(false);
        mcMusicSteps.step_login(email, password);
        if(valid .equals("1")) {
            mcMusicSteps.assertLoggedIn(true);
        }
    }

    @Test
    public void loginFail() {
        mcMusicSteps.init();
        mcMusicSteps.assertLoggedIn(false);
        mcMusicSteps.step_login(email, password);
        if(valid .equals("0")) {
            mcMusicSteps.assertLoggedIn(false);
        }
    }

}
