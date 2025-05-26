package com.demoblaze.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductPage extends PageObject {
    public static final Target PHONES_CATEGORY = Target.the("Categoría de teléfonos")
            .located(By.xpath("//a[contains(text(),'Phones')]"));

    public static final Target MONITORS_CATEGORY = Target.the("Categoría de monitores")
            .located(By.xpath("//a[contains(text(),'Monitors')]"));

    public static final Target FIRST_PHONE = Target.the("Primer teléfono")
            .located(By.xpath("(//a[contains(@class,'hrefch')])[1]"));

    public static final Target FIRST_MONITOR = Target.the("Primer monitor")
            .located(By.xpath("(//a[contains(@class,'hrefch')])[1]"));

    public static final Target ADD_TO_CART_BUTTON = Target.the("Botón de agregar al carrito")
            .located(By.xpath("//a[contains(text(),'Add to cart')]"));

    public static final Target CART_ITEMS = Target.the("Elementos del carrito")
            .located(By.xpath("//tbody/tr"));

    public static final Target EMPTY_CART_MESSAGE = Target.the("Mensaje de carrito vacío")
            .located(By.xpath("//h3[contains(text(),'Products')]"));
}