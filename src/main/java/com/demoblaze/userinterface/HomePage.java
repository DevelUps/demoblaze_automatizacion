package com.demoblaze.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage extends PageObject {
    public static final Target SIGN_UP_BUTTON = Target.the("Botón de registro")
            .located(By.id("signin2"));

    public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
            .located(By.id("login2"));

    public static final Target USERNAME_FIELD = Target.the("Campo de nombre de usuario")
            .located(By.id("sign-username"));

    public static final Target PASSWORD_FIELD = Target.the("Campo de contraseña")
            .located(By.id("sign-password"));

    public static final Target SIGN_UP_SUBMIT = Target.the("Botón de enviar registro")
            .located(By.xpath("//button[contains(text(),'Sign up')]"));

    public static final Target LOGIN_USERNAME = Target.the("Campo de usuario para login")
            .located(By.id("loginusername"));

    public static final Target LOGIN_PASSWORD = Target.the("Campo de contraseña para login")
            .located(By.id("loginpassword"));

    public static final Target LOGIN_SUBMIT = Target.the("Botón de enviar login")
            .located(By.xpath("//button[contains(text(),'Log in')]"));

    public static final Target LOGOUT_BUTTON = Target.the("Botón de cerrar sesión")
            .located(By.id("logout2"));

    public static final Target CART_BUTTON = Target.the("Botón del carrito")
            .located(By.id("cartur"));

    public static final Target WELCOME_MESSAGE = Target.the("Mensaje de bienvenida")
            .located(By.id("nameofuser"));

    public static final Target SIGN_UP_SUCCESS_MESSAGE = Target.the("Mensaje de registro exitoso")
            .located(By.xpath("//div[contains(@class,'alert-success')]"));
}