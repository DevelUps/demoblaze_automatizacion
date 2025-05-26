package com.demoblaze.stepdefinitions;

import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.pages.CartPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import java.util.Random;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.UUID;
import org.openqa.selenium.By;

public class FlujoCompletoSteps {
    private Actor usuario;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String usuarioRegistrado;
    private String passwordRegistrado;

    @Before
    public void setUp() {
        homePage = new HomePage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        usuario = Actor.named("Usuario");
        usuario.can(BrowseTheWeb.with(WebDriverManager.chromedriver().create()));
        usuarioRegistrado = "user_" + UUID.randomUUID().toString().substring(0, 8);
        passwordRegistrado = "Password123!";
    }

    @Given("que estoy en la página de inicio de DemoBlaze")
    public void queEstoyEnLaPaginaDeInicio() {
        usuario.attemptsTo(Open.url(HomePage.URL));
    }

    @When("me registro como un nuevo usuario")
    public void meRegistroComoUnNuevoUsuario() {
        usuario.attemptsTo(
                WaitUntil.the(HomePage.SIGN_UP_BUTTON, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(HomePage.SIGN_UP_BUTTON),
                WaitUntil.the(HomePage.SIGN_UP_USERNAME, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Enter.theValue(usuarioRegistrado).into(HomePage.SIGN_UP_USERNAME),
                Enter.theValue(passwordRegistrado).into(HomePage.SIGN_UP_PASSWORD),
                Click.on(HomePage.SIGN_UP_SUBMIT));

        // Esperar y verificar el mensaje de registro exitoso
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Ensure.that(alertText).contains("Sign up successful");
    }

    @And("inicio sesión con mis credenciales")
    public void inicioSesiónConMisCredenciales() {
        usuario.attemptsTo(
                WaitUntil.the(HomePage.LOGIN_BUTTON, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(HomePage.LOGIN_BUTTON),
                WaitUntil.the(HomePage.LOGIN_USERNAME, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Enter.theValue(usuarioRegistrado).into(HomePage.LOGIN_USERNAME),
                Enter.theValue(passwordRegistrado).into(HomePage.LOGIN_PASSWORD),
                Click.on(HomePage.LOGIN_SUBMIT));

        // Esperar y verificar el inicio de sesión exitoso
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            if (alertText.contains("User does not exist")) {
                throw new RuntimeException("El usuario no existe. Asegúrese de registrarse primero.");
            }
        } catch (Exception e) {
            // No hacer nada si no hay alerta, asumimos que el inicio de sesión fue exitoso
        }
        usuario.attemptsTo(WaitUntil.the(HomePage.WELCOME_MESSAGE, isVisible()).forNoMoreThan(Duration.ofSeconds(15)));
    }

    @And("agrego un {string} al carrito")
    public void agregoProductoAlCarrito(String productoTipo) {
        if (productoTipo.equals("teléfono")) {
            navegoACategoria("Phones");
            seleccionoProducto("Nokia lumia 1520");
        } else if (productoTipo.equals("monitor")) {
            usuario.attemptsTo(Open.url(HomePage.URL)); // Volver a la página principal para seleccionar monitor
            navegoACategoria("Monitors");
            seleccionoProducto("ASUS Full HD");
        }
        // Agregar el producto a la cesta
        usuario.attemptsTo(
                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(ProductPage.ADD_TO_CART_BUTTON));
        deboVerMensajeProductoAgregado();
    }

    @When("cierro sesión")
    public void cierroSesion() {
        usuario.attemptsTo(
                WaitUntil.the(HomePage.LOGOUT_BUTTON, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(HomePage.LOGOUT_BUTTON));
        usuario.attemptsTo(WaitUntil.the(HomePage.LOGIN_BUTTON, isVisible()).forNoMoreThan(Duration.ofSeconds(10)));
    }

    @Then("valido que el carrito esté vacío")
    public void validoQueElCarritoEsteVacio() {
        usuario.attemptsTo(
                Open.url("https://www.demoblaze.com/cart.html"));
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(CartPage.CART_ITEMS.getCssOrXPathSelector()),
                0));
    }

    @When("inicio sesión nuevamente")
    public void inicioSesiónNuevamente() {
        inicioSesiónConMisCredenciales();
        // Verificar que el login fue exitoso
        usuario.attemptsTo(WaitUntil.the(HomePage.WELCOME_MESSAGE, isVisible()).forNoMoreThan(Duration.ofSeconds(15)));
    }

    @Then("valido que el carrito contenga los productos previamente agregados")
    public void validoQueElCarritoContengaLosProductosPreviamenteAgregados() {
        usuario.attemptsTo(
                Open.url("https://www.demoblaze.com/cart.html"),
                WaitUntil.the(CartPage.PRODUCT_LIST, isVisible()).forNoMoreThan(Duration.ofSeconds(15)),
                Ensure.that(CartPage.PRODUCT_LIST).isDisplayed());
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.cssSelector(CartPage.CART_ITEMS.getCssOrXPathSelector()), 0));
    }

    // Métodos auxiliares
    public void navegoACategoria(String categoria) {
        usuario.attemptsTo(
                WaitUntil.the(homePage.getCategoryLink(categoria), isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(homePage.getCategoryLink(categoria)));
    }

    public void seleccionoProducto(String producto) {
        usuario.attemptsTo(
                WaitUntil.the(homePage.getProductLink(producto), isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Click.on(homePage.getProductLink(producto)),
                WaitUntil.the(homePage.getProductHeading(producto), isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Ensure.that(homePage.getProductHeading(producto)).isDisplayed());
    }

    public void deboVerMensajeProductoAgregado() {
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Ensure.that(alertText).contains("Product added");
    }
}