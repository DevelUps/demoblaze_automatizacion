package com.demoblaze.questions;

import com.demoblaze.userinterface.HomePage;
import com.demoblaze.userinterface.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;

public class CartQuestions {
    public static Question<Boolean> isEmpty() {
        return Question.about("El carrito está vacío")
                .answeredBy(actor -> actor.asksFor(Visibility.of(ProductPage.EMPTY_CART_MESSAGE)));
    }

    public static Question<Boolean> hasItems() {
        return Question.about("El carrito tiene elementos")
                .answeredBy(actor -> actor.asksFor(Visibility.of(ProductPage.CART_ITEMS)));
    }

    public static Question<String> welcomeMessage() {
        return Question.about("Mensaje de bienvenida")
                .answeredBy(actor -> actor.asksFor(Text.of(HomePage.WELCOME_MESSAGE)));
    }

    public static Question<Boolean> signUpSuccess() {
        return Question.about("Mensaje de registro exitoso")
                .answeredBy(actor -> actor.asksFor(Visibility.of(HomePage.SIGN_UP_SUCCESS_MESSAGE)));
    }
}