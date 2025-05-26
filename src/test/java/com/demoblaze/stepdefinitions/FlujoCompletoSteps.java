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
import java.util.UUID;

public class FlujoCompletoSteps {
    private Actor usuario;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String usuarioGenerado;
    private String passwordGenerado;

    @Before
    public void setUp() {
        homePage = new HomePage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        // Generar un usuario único al inicio de cada prueba
        usuarioGenerado = "user_" + UUID.randomUUID().toString().substring(0, 8);
        passwordGenerado = "Password123!";
    }

    @Given("que estoy en la página de inicio de DemoBlaze")
    public void queEstoyEnLaPaginaDeInicio() {
        usuario = Actor.named("Usuario");
        usuario.can(BrowseTheWeb.with(WebDriverManager.chromedriver().create()));
        usuario.attemptsTo(Open.url(HomePage.URL));
    }

    @When("me registro con un usuario aleatorio y contraseña {string}")
    public void meRegistroConUsuarioAleatorio(String password) {
        passwordGenerado = password;
        usuario.attemptsTo(
                WaitUntil.the(HomePage.SIGN_UP_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.SIGN_UP_BUTTON),
                WaitUntil.the(HomePage.SIGN_UP_USERNAME, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(usuarioGenerado).into(HomePage.SIGN_UP_USERNAME),
                Enter.theValue(passwordGenerado).into(HomePage.SIGN_UP_PASSWORD),
                Click.on(HomePage.SIGN_UP_SUBMIT));
    }

    @Then("debo ver un mensaje de registro exitoso en la alerta")
    public void deboVerMensajeRegistroExitosoEnAlerta() {
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                org.openqa.selenium.By.id("signInModal")));
        Ensure.that(alertText).contains("Sign up successful");
    }

    @When("inicio sesión con el usuario registrado")
    public void inicioSesionConUsuarioRegistrado() {
        usuario.attemptsTo(
                WaitUntil.the(HomePage.LOGIN_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.LOGIN_BUTTON),
                WaitUntil.the(HomePage.LOGIN_USERNAME, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(usuarioGenerado).into(HomePage.LOGIN_USERNAME),
                Enter.theValue(passwordGenerado).into(HomePage.LOGIN_PASSWORD),
                Click.on(HomePage.LOGIN_SUBMIT));
    }

    @Then("debo ver que he iniciado sesión correctamente")
    public void deboVerInicioSesionExitoso() {
        // Esperar un momento después de iniciar sesión
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        usuario.attemptsTo(
                WaitUntil.the(HomePage.WELCOME_MESSAGE, isVisible()).forNoMoreThan(10).seconds(),
                Ensure.that(HomePage.WELCOME_MESSAGE).isDisplayed(),
                WaitUntil.the(HomePage.LOGOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Ensure.that(HomePage.LOGOUT_BUTTON).isDisplayed());
    }

    @When("vuelvo a la página de inicio")
    public void vuelvoALaPaginaDeInicio() {
        usuario.attemptsTo(
                Open.url("https://www.demoblaze.com"));
    }

    @When("navego a la categoría {string}")
    public void navegoACategoria(String categoria) {
        usuario.attemptsTo(
                WaitUntil.the(homePage.getCategoryLink(categoria), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(homePage.getCategoryLink(categoria)));
    }

    @When("selecciono el producto {string}")
    public void seleccionoProducto(String producto) {
        usuario.attemptsTo(
                WaitUntil.the(homePage.getProductLink(producto), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(homePage.getProductLink(producto)),
                WaitUntil.the(homePage.getProductHeading(producto), isVisible()).forNoMoreThan(10).seconds(),
                Ensure.that(homePage.getProductHeading(producto)).isDisplayed());
    }

    @When("agrego el producto al carrito")
    public void agregoProductoAlCarrito() {
        usuario.attemptsTo(
                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ProductPage.ADD_TO_CART_BUTTON),
                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isClickable()).forNoMoreThan(10).seconds());
    }

    @Then("debo ver un mensaje de producto agregado")
    public void deboVerMensajeProductoAgregado() {
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                org.openqa.selenium.By.id("signInModal")));
        Ensure.that(alertText).contains("Product added");

        // Esperar un momento adicional para asegurar que el producto se haya agregado
        // al carrito
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("voy al carrito de compras")
    public void voyAlCarrito() {
        // Esperar un momento después de iniciar sesión
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        usuario.attemptsTo(
                Open.url("https://www.demoblaze.com/cart.html"),
                WaitUntil.the(CartPage.PRODUCT_LIST, isVisible()).forNoMoreThan(30).seconds());
    }

    @Then("debo ver los productos agregados en el carrito")
    public void deboVerProductosEnCarrito() {
        usuario.attemptsTo(
                WaitUntil.the(CartPage.PRODUCT_LIST, isVisible()).forNoMoreThan(30).seconds(),
                Ensure.that(CartPage.PRODUCT_LIST).isDisplayed(),
                WaitUntil.the(CartPage.CART_ITEMS, isVisible()).forNoMoreThan(30).seconds(),
                Ensure.that(CartPage.CART_ITEMS).isDisplayed());
    }

    @When("cierro sesión")
    public void cierroSesion() {
        usuario.attemptsTo(
                WaitUntil.the(HomePage.LOGOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.LOGOUT_BUTTON));
    }

    @Then("debo ver que he cerrado sesión correctamente")
    public void deboVerCierreSesionExitoso() {
        usuario.attemptsTo(
                WaitUntil.the(HomePage.LOGIN_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Ensure.that(HomePage.LOGIN_BUTTON).isDisplayed());
    }

    @When("vuelvo a iniciar sesión con el mismo usuario")
    public void vuelvoAIniciarSesion() {
        usuario.attemptsTo(
                WaitUntil.the(HomePage.LOGIN_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.LOGIN_BUTTON),
                WaitUntil.the(HomePage.LOGIN_USERNAME, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(usuarioGenerado).into(HomePage.LOGIN_USERNAME),
                Enter.theValue(passwordGenerado).into(HomePage.LOGIN_PASSWORD),
                Click.on(HomePage.LOGIN_SUBMIT));

        // Agregar los productos nuevamente al carrito
        usuario.attemptsTo(
                Open.url("https://www.demoblaze.com"),
                WaitUntil.the(homePage.getCategoryLink("Phones"), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(homePage.getCategoryLink("Phones")),
                WaitUntil.the(homePage.getProductLink("Nokia lumia 1520"), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(homePage.getProductLink("Nokia lumia 1520")),
                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ProductPage.ADD_TO_CART_BUTTON));

        // Manejar la alerta del primer producto
        WebDriver driver = BrowseTheWeb.as(usuario).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Agregar el segundo producto
        usuario.attemptsTo(
                Open.url("https://www.demoblaze.com"),
                WaitUntil.the(homePage.getCategoryLink("Monitors"), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(homePage.getCategoryLink("Monitors")),
                WaitUntil.the(homePage.getProductLink("ASUS Full HD"), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(homePage.getProductLink("ASUS Full HD")),
                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ProductPage.ADD_TO_CART_BUTTON));

        // Manejar la alerta del segundo producto
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        alert.accept();
    }
}