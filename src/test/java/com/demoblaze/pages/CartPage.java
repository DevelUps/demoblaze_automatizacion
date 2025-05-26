package com.demoblaze.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class CartPage extends PageObject {

    public static final Target CART_ITEMS = Target.the("Elementos del carrito")
            .locatedBy("#tbodyid tr");

    public static final Target PRODUCT_LIST = Target.the("Lista de productos")
            .locatedBy("#tbodyid");

    public static final Target NOKIA_PRODUCT = Target.the("Producto Nokia")
            .locatedBy("//td[contains(text(), 'Nokia lumia 1520')]");

    public static final Target ASUS_PRODUCT = Target.the("Producto ASUS")
            .locatedBy("//td[contains(text(), 'ASUS Full HD')]");

    public Target getCartItems() {
        return CART_ITEMS;
    }
}