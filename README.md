# Automatización Demoblaze

Este proyecto contiene pruebas automatizadas para el sitio web Demoblaze utilizando Serenity BDD, Cucumber y Java.

## Requisitos

- Java JDK 11 o superior
- Maven 3.6 o superior
- Chrome Browser

## Instalación

1. Clonar el repositorio
2. Instalar las dependencias:
```bash
mvn clean install
```

## Estructura del Proyecto

```
demoblaze_automatizacion/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── demoblaze/
│   │   │           ├── pages/           # Page Objects
│   │   │           ├── runners/         # Runners de Cucumber
│   │   │           └── stepdefinitions/ # Definiciones de pasos
│   │   └── resources/
│   │       └── features/    # Archivos .feature de Cucumber
├── target/                  # Directorio de compilación y reportes
└── pom.xml                 # Configuración de Maven
```

## Ejecución de Pruebas

Para ejecutar todas las pruebas:
```bash
mvn clean verify
```

Para ejecutar pruebas específicas:
```bash
mvn clean verify -Dcucumber.filter.tags="@tag"
```

## Reportes

Los reportes de Serenity se generan automáticamente en:
```
target/site/serenity/index.html
```

## Características Implementadas

- Registro de usuario
- Inicio de sesión
- Agregar productos al carrito
- Validación del carrito
- Cierre de sesión 