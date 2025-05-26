import pytest
from utils.driver_factory import DriverFactory
from pages.login_page import LoginPage
from pages.home_page import HomePage

class TestCompra:
    @pytest.fixture
    def setup(self):
        self.driver = DriverFactory.get_driver()
        self.driver.get("https://www.demoblaze.com/")
        self.login_page = LoginPage(self.driver)
        self.home_page = HomePage(self.driver)
        yield
        self.driver.quit()
        
    def test_compra_productos(self, setup):
        # 1. Iniciar sesión
        self.login_page.login("test_user", "test_password")
        assert self.login_page.is_logged_in(), "No se pudo iniciar sesión"
        
        # 2. Agregar teléfono al carrito
        self.home_page.select_category("phones")
        self.home_page.select_product("Samsung galaxy s6")
        self.home_page.add_to_cart()
        
        # 3. Agregar monitor al carrito
        self.home_page.select_category("monitors")
        self.home_page.select_product("Apple monitor 24")
        self.home_page.add_to_cart()
        
        # 4. Verificar productos en el carrito
        self.home_page.go_to_cart()
        productos = self.home_page.get_product_titles()
        assert "Samsung galaxy s6" in productos, "El teléfono no está en el carrito"
        assert "Apple monitor 24" in productos, "El monitor no está en el carrito" 