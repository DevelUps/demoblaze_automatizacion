# Automatización Demoblaze

Este proyecto contiene pruebas automatizadas para el sitio web Demoblaze utilizando Selenium y Python.

## Requisitos

- Python 3.8 o superior
- pip (gestor de paquetes de Python)

## Instalación

1. Clonar el repositorio
2. Instalar las dependencias:
```bash
pip install -r requirements.txt
```

## Estructura del Proyecto

```
demoblaze_automatizacion/
├── tests/                    # Directorio de pruebas
│   ├── test_login.py        # Pruebas de inicio de sesión
│   └── test_compra.py       # Pruebas de compra
├── pages/                    # Page Objects
│   ├── login_page.py        # Página de inicio de sesión
│   └── home_page.py         # Página principal
├── utils/                    # Utilidades
│   └── driver_factory.py    # Configuración del WebDriver
└── requirements.txt         # Dependencias del proyecto
```

## Ejecución de Pruebas

Para ejecutar todas las pruebas:
```bash
pytest
```

Para ejecutar pruebas específicas:
```bash
pytest tests/test_login.py
```

Para generar reportes HTML:
```bash
pytest --html=report.html
``` 