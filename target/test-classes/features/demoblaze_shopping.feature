# language: es
@demoblaze
Característica: Flujo de compra en DemoBlaze
  Como usuario de DemoBlaze
  Quiero poder registrarme, iniciar sesión y realizar compras
  Para poder comprar productos en la tienda

  Antecedentes:
    Dado que estoy en la página de inicio de DemoBlaze

  Escenario: Registro de nuevo usuario
    Cuando me registro como un nuevo usuario
    Entonces debo ver un mensaje de registro exitoso

  Escenario: Inicio de sesión con usuario registrado
    Cuando inicio sesión con mis credenciales
    Entonces debo ver mi nombre de usuario en la barra de navegación

  Escenario: Agregar productos al carrito
    Dado que he iniciado sesión con mis credenciales
    Cuando agrego un teléfono al carrito
    Y agrego un monitor al carrito
    Entonces debo ver los productos en el carrito

  Escenario: Validar carrito después de cerrar sesión
    Dado que he iniciado sesión con mis credenciales
    Y he agregado productos al carrito
    Cuando cierro sesión
    Entonces el carrito debe estar vacío

  Escenario: Validar carrito después de volver a iniciar sesión
    Dado que he iniciado sesión con mis credenciales
    Y he agregado productos al carrito
    Y he cerrado sesión
    Cuando inicio sesión nuevamente
    Entonces el carrito debe contener los productos previamente agregados 