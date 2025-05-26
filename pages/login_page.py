from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class LoginPage:
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)
        
    # Localizadores
    LOGIN_BUTTON = (By.ID, "login2")
    USERNAME_INPUT = (By.ID, "loginusername")
    PASSWORD_INPUT = (By.ID, "loginpassword")
    SUBMIT_BUTTON = (By.XPATH, "//button[contains(text(), 'Log in')]")
    WELCOME_MESSAGE = (By.ID, "nameofuser")
    SIGNUP_BUTTON = (By.ID, "signin2")
    SIGNUP_USERNAME = (By.ID, "sign-username")
    SIGNUP_PASSWORD = (By.ID, "sign-password")
    SIGNUP_SUBMIT = (By.XPATH, "//button[contains(text(), 'Sign up')]")
    LOGOUT_BUTTON = (By.ID, "logout2")
    
    def click_login_button(self):
        self.wait.until(EC.element_to_be_clickable(self.LOGIN_BUTTON)).click()
        
    def enter_username(self, username):
        self.wait.until(EC.visibility_of_element_located(self.USERNAME_INPUT)).send_keys(username)
        
    def enter_password(self, password):
        self.wait.until(EC.visibility_of_element_located(self.PASSWORD_INPUT)).send_keys(password)
        
    def click_submit(self):
        self.wait.until(EC.element_to_be_clickable(self.SUBMIT_BUTTON)).click()
        
    def is_logged_in(self):
        try:
            self.wait.until(EC.visibility_of_element_located(self.WELCOME_MESSAGE))
            return True
        except:
            return False
            
    def login(self, username, password):
        self.click_login_button()
        self.enter_username(username)
        self.enter_password(password)
        self.click_submit()
        
    def click_signup_button(self):
        self.wait.until(EC.element_to_be_clickable(self.SIGNUP_BUTTON)).click()
        
    def enter_signup_username(self, username):
        self.wait.until(EC.visibility_of_element_located(self.SIGNUP_USERNAME)).send_keys(username)
        
    def enter_signup_password(self, password):
        self.wait.until(EC.visibility_of_element_located(self.SIGNUP_PASSWORD)).send_keys(password)
        
    def click_signup_submit(self):
        self.wait.until(EC.element_to_be_clickable(self.SIGNUP_SUBMIT)).click()
        
    def logout(self):
        self.wait.until(EC.element_to_be_clickable(self.LOGOUT_BUTTON)).click() 