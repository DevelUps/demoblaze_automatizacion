# Automatización DemoBlaze con Serenity BDD

Este proyecto implementa pruebas automatizadas para el sitio web DemoBlaze utilizando Serenity BDD y el patrón Screenplay.

## 🎯 Justificación del Patrón Screenplay

Se ha elegido el patrón Screenplay por las siguientes razones:

1. **Mejor mantenibilidad**: El patrón Screenplay promueve un código más limpio y mantenible al separar claramente las responsabilidades.
2. **Mayor legibilidad**: Las pruebas se escriben en un lenguaje más cercano al negocio, lo que facilita su comprensión.
3. **Reutilización de código**: Permite crear tareas y acciones reutilizables de manera más eficiente.
4. **Escalabilidad**: Facilita la adición de nuevas funcionalidades sin afectar el código existente.
5. **Mejor manejo de errores**: Proporciona mensajes de error más claros y detallados.

## 🛠️ Tecnologías Utilizadas

- Java 11
- Serenity BDD 3.6.15
- Cucumber 7.11.0
- Maven
- JUnit 4.13.2

## 📋 Requisitos Previos

- Java JDK 11 o superior
- Maven 3.6.0 o superior
- Navegador Chrome instalado

## 🚀 Ejecución de las Pruebas

Para ejecutar las pruebas, utiliza el siguiente comando:

```bash
mvn clean verify
```

Los reportes se generarán automáticamente en la carpeta `target/site/serenity/`.

## 📁 Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── com/
│           └── demoblaze/
│               ├── models/
│               ├── tasks/
│               ├── userinterface/
│               └── utils/
└── test/
    ├── java/
    │   └── com/
    │       └── demoblaze/
    │           ├── runners/
    │           └── stepdefinitions/
    └── resources/
        └── features/
```

## 🔄 Flujo de Prueba

1. Registro de nuevo usuario
2. Inicio de sesión
3. Agregar teléfono al carrito
4. Agregar monitor al carrito
5. Cerrar sesión
6. Validar carrito vacío
7. Iniciar sesión nuevamente
8. Validar productos en carrito

## 📊 Reportes

Los reportes de Serenity incluyen:
- Resumen de pruebas ejecutadas
- Capturas de pantalla
- Detalles de cada paso
- Tiempo de ejecución
- Estado de las pruebas 