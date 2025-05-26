package com.demoblaze.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class ProductPage extends PageObject {

    public static final Target ADD_TO_CART_BUTTON = Target.the("Botón de agregar al carrito")
            .locatedBy("//a[contains(text(), 'Add to cart')]");

    public static final Target ALERT_MESSAGE = Target.the("Mensaje de alerta")
            .locatedBy(".alert");

    public Target getAddToCartButton() {
        return ADD_TO_CART_BUTTON;
    }

    public Target getAlertMessage() {
        return ALERT_MESSAGE;
    }
}