from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePage:
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)
        
    # Localizadores
    PHONES_CATEGORY = (By.XPATH, "//a[contains(text(), 'Phones')]")
    MONITORS_CATEGORY = (By.XPATH, "//a[contains(text(), 'Monitors')]")
    ADD_TO_CART_BUTTON = (By.XPATH, "//a[contains(text(), 'Add to cart')]")
    CART_BUTTON = (By.ID, "cartur")
    PRODUCT_TITLE = (By.CLASS_NAME, "hrefch")
    
    def select_category(self, category):
        if category.lower() == "phones":
            self.wait.until(EC.element_to_be_clickable(self.PHONES_CATEGORY)).click()
        elif category.lower() == "monitors":
            self.wait.until(EC.element_to_be_clickable(self.MONITORS_CATEGORY)).click()
            
    def select_product(self, product_name):
        product = self.wait.until(EC.element_to_be_clickable(
            (By.XPATH, f"//a[contains(text(), '{product_name}')]")
        ))
        product.click()
        
    def add_to_cart(self):
        self.wait.until(EC.element_to_be_clickable(self.ADD_TO_CART_BUTTON)).click()
        
    def go_to_cart(self):
        self.wait.until(EC.element_to_be_clickable(self.CART_BUTTON)).click()
        
    def get_product_titles(self):
        products = self.wait.until(EC.presence_of_all_elements_located(self.PRODUCT_TITLE))
        return [product.text for product in products] 