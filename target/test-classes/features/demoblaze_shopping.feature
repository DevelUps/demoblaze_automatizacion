# language: es
@demoblaze
Característica: Flujo de compra en DemoBlaze
  Como usuario de DemoBlaze
  Quiero poder registrarme, iniciar sesión y realizar compras
  Para poder comprar productos en la tienda

  Antecedentes:
    Dado que estoy en la página de inicio de DemoBlaze

  Escenario: Flujo completo de registro, compra y validación de carrito
    Cuando me registro como un nuevo usuario
    Y inicio sesión con mis credenciales
    Y agrego un "teléfono" al carrito
    Y agrego un "monitor" al carrito
    Cuando cierro sesión
    Entonces valido que el carrito esté vacío
    Cuando inicio sesión nuevamente
    Entonces valido que el carrito contenga los productos previamente agregados 