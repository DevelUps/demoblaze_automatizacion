Feature: Flujo completo de DemoBlaze
  Como usuario de DemoBlaze
  Quiero poder registrarme, iniciar sesión y realizar compras
  Para poder comprar productos en la tienda

  Scenario: Registro, inicio de sesión y compra de productos
    Given que estoy en la página de inicio de DemoBlaze
    When me registro con un usuario aleatorio y contraseña "usuario564"
    Then debo ver un mensaje de registro exitoso en la alerta
    When inicio sesión con el usuario registrado
    Then debo ver que he iniciado sesión correctamente
    When navego a la categoría "Phones"
    And selecciono el producto "Nokia lumia 1520"
    And agrego el producto al carrito
    Then debo ver un mensaje de producto agregado
    When vuelvo a la página de inicio
    When navego a la categoría "Monitors"
    And selecciono el producto "ASUS Full HD"
    And agrego el producto al carrito
    Then debo ver un mensaje de producto agregado
    When cierro sesión
    Then debo ver que he cerrado sesión correctamente
    When vuelvo a iniciar sesión con el mismo usuario
    When voy al carrito de compras
    Then debo ver los productos agregados en el carrito 