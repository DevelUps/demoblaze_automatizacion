package com.demoblaze.tasks;

import com.demoblaze.models.User;
import com.demoblaze.userinterface.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {
    private final User user;

    public Login(User user) {
        this.user = user;
    }

    public static Login withUser(User user) {
        return Tasks.instrumented(Login.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.LOGIN_BUTTON),
                WaitUntil.the(HomePage.LOGIN_USERNAME, isVisible()),
                Enter.theValue(user.getUsername()).into(HomePage.LOGIN_USERNAME),
                Enter.theValue(user.getPassword()).into(HomePage.LOGIN_PASSWORD),
                Click.on(HomePage.LOGIN_SUBMIT));
    }
}