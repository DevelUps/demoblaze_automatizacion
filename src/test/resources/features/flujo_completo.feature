# language: es
@flujocompleto
Característica: Flujo completo de DemoBlaze
  Registro, inicio de sesión y compra de productos

  Escenario: Registro, inicio de sesión y compra de productos
    Dado que estoy en la página de inicio de DemoBlaze
    Cuando me registro con un usuario aleatorio y contraseña "usuario564"
    Entonces debo ver un mensaje de registro exitoso en la alerta
    Cuando inicio sesión con el usuario registrado
    Entonces debo ver que he iniciado sesión correctamente
    Cuando navego a la categoría "Phones"
    Y selecciono el producto "Nokia lumia 1520"
    Y agrego el producto al carrito
    Entonces debo ver un mensaje de producto agregado
    Cuando vuelvo a la página de inicio
    Cuando navego a la categoría "Monitors"
    Y selecciono el producto "ASUS Full HD"
    Y agrego el producto al carrito
    Entonces debo ver un mensaje de producto agregado
    Cuando cierro sesión
    Entonces debo ver que he cerrado sesión correctamente
    Cuando vuelvo a iniciar sesión con el mismo usuario
    Cuando voy al carrito de compras
    Entonces debo ver los productos agregados en el carrito 