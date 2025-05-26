import pytest
import random
import string
from utils.driver_factory import DriverFactory
from pages.login_page import LoginPage
from pages.home_page import HomePage

class TestRegistro:
    @pytest.fixture
    def setup(self):
        self.driver = DriverFactory.get_driver()
        self.driver.get("https://www.demoblaze.com/")
        self.login_page = LoginPage(self.driver)
        self.home_page = HomePage(self.driver)
        yield
        self.driver.quit()
        
    def generar_usuario_aleatorio(self):
        letras = string.ascii_lowercase
        numero_aleatorio = ''.join(random.choices(string.digits, k=8))
        return f"user_{numero_aleatorio}"
        
    def test_registro_y_compra(self, setup):
        # Generar usuario aleatorio
        username = self.generar_usuario_aleatorio()
        password = "usuario564"
        
        # 1. Registro de usuario
        self.login_page.click_signup_button()
        self.login_page.enter_signup_username(username)
        self.login_page.enter_signup_password(password)
        self.login_page.click_signup_submit()
        
        # Verificar mensaje de registro exitoso
        alert = self.driver.switch_to.alert
        assert "Sign up successful" in alert.text
        alert.accept()
        
        # 2. Inicio de sesión
        self.login_page.login(username, password)
        assert self.login_page.is_logged_in(), "No se pudo iniciar sesión"
        
        # 3. Agregar teléfono al carrito
        self.home_page.select_category("phones")
        self.home_page.select_product("Nokia lumia 1520")
        self.home_page.add_to_cart()
        
        # 4. Agregar monitor al carrito
        self.home_page.select_category("monitors")
        self.home_page.select_product("ASUS Full HD")
        self.home_page.add_to_cart()
        
        # 5. Cerrar sesión
        self.login_page.logout()
        assert not self.login_page.is_logged_in(), "No se pudo cerrar sesión"
        
        # 6. Volver a iniciar sesión
        self.login_page.login(username, password)
        assert self.login_page.is_logged_in(), "No se pudo iniciar sesión nuevamente"
        
        # 7. Verificar productos en el carrito
        self.home_page.go_to_cart()
        productos = self.home_page.get_product_titles()
        assert "Nokia lumia 1520" in productos, "El teléfono no está en el carrito"
        assert "ASUS Full HD" in productos, "El monitor no está en el carrito" 