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

public class Register implements Task {
    private final User user;

    public Register(User user) {
        this.user = user;
    }

    public static Register withNewUser() {
        return Tasks.instrumented(Register.class, new User());
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.SIGN_UP_BUTTON),
                WaitUntil.the(HomePage.USERNAME_FIELD, isVisible()),
                Enter.theValue(user.getUsername()).into(HomePage.USERNAME_FIELD),
                Enter.theValue(user.getPassword()).into(HomePage.PASSWORD_FIELD),
                Click.on(HomePage.SIGN_UP_SUBMIT));
    }
}