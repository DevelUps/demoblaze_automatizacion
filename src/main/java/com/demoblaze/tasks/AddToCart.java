package com.demoblaze.tasks;

import com.demoblaze.userinterface.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddToCart implements Task {
    private final String productType;

    public AddToCart(String productType) {
        this.productType = productType;
    }

    public static AddToCart aPhone() {
        return Tasks.instrumented(AddToCart.class, "phone");
    }

    public static AddToCart aMonitor() {
        return Tasks.instrumented(AddToCart.class, "monitor");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (productType.equals("phone")) {
            actor.attemptsTo(
                    Click.on(ProductPage.PHONES_CATEGORY),
                    WaitUntil.the(ProductPage.FIRST_PHONE, isVisible()),
                    Click.on(ProductPage.FIRST_PHONE),
                    WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isVisible()),
                    Click.on(ProductPage.ADD_TO_CART_BUTTON));
        } else {
            actor.attemptsTo(
                    Click.on(ProductPage.MONITORS_CATEGORY),
                    WaitUntil.the(ProductPage.FIRST_MONITOR, isVisible()),
                    Click.on(ProductPage.FIRST_MONITOR),
                    WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isVisible()),
                    Click.on(ProductPage.ADD_TO_CART_BUTTON));
        }
    }
}