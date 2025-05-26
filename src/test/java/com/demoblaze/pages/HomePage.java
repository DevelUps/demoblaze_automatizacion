package com.demoblaze.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.actions.Open;

public class HomePage extends PageObject {
    public static final String URL = "https://www.demoblaze.com";

    public static HomePage homePage() {
        return new HomePage();
    }

    public static final Target SIGN_UP_BUTTON = Target.the("Botón de registro")
            .locatedBy("#signin2");

    public static final Target SIGN_UP_USERNAME = Target.the("Campo de usuario para registro")
            .locatedBy("#sign-username");

    public static final Target SIGN_UP_PASSWORD = Target.the("Campo de contraseña para registro")
            .locatedBy("#sign-password");

    public static final Target SIGN_UP_SUBMIT = Target.the("Botón de enviar registro")
            .locatedBy("#signInModal .btn-primary[onclick='register()']");

    public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
            .locatedBy("#login2");

    public static final Target LOGIN_USERNAME = Target.the("Campo de usuario para inicio de sesión")
            .locatedBy("#loginusername");

    public static final Target LOGIN_PASSWORD = Target.the("Campo de contraseña para inicio de sesión")
            .locatedBy("#loginpassword");

    public static final Target LOGIN_SUBMIT = Target.the("Botón de enviar inicio de sesión")
            .locatedBy("#logInModal .btn-primary[onclick='logIn()']");

    public static final Target WELCOME_MESSAGE = Target.the("Mensaje de bienvenida")
            .locatedBy("#nameofuser");

    public static final Target LOGOUT_BUTTON = Target.the("Botón de cerrar sesión")
            .locatedBy("#logout2");

    public static final Target CART_BUTTON = Target.the("Botón del carrito")
            .locatedBy("#cartur");

    public static final Target ALERT_MESSAGE = Target.the("Mensaje de alerta")
            .locatedBy(".alert");

    public Target getSignUpButton() {
        return SIGN_UP_BUTTON;
    }

    public Target getSignUpUsername() {
        return SIGN_UP_USERNAME;
    }

    public Target getSignUpPassword() {
        return SIGN_UP_PASSWORD;
    }

    public Target getSignUpSubmitButton() {
        return SIGN_UP_SUBMIT;
    }

    public Target getLoginButton() {
        return LOGIN_BUTTON;
    }

    public Target getLoginUsername() {
        return LOGIN_USERNAME;
    }

    public Target getLoginPassword() {
        return LOGIN_PASSWORD;
    }

    public Target getLoginSubmitButton() {
        return LOGIN_SUBMIT;
    }

    public Target getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public Target getLogoutButton() {
        return LOGOUT_BUTTON;
    }

    public Target getCartButton() {
        return CART_BUTTON;
    }

    public Target getAlertMessage() {
        return ALERT_MESSAGE;
    }

    public Target getCategoryLink(String category) {
        return Target.the("Enlace de categoría " + category)
                .locatedBy("//a[@href='#'][contains(.,'" + category + "')]");
    }

    public Target getProductLink(String product) {
        return Target.the("Enlace del producto " + product)
                .locatedBy("//a[@href][contains(.,'" + product + "')]");
    }

    public Target getProductHeading(String product) {
        return Target.the("Encabezado del producto " + product)
                .locatedBy("//h2[contains(text(), '" + product + "')]");
    }

    public Target getHomeLink() {
        return Target.the("Enlace de inicio")
                .locatedBy("//a[contains(text(), 'Home (current)')]");
    }
}