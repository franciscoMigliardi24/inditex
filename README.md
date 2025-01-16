# Inditex Pricing Service

## Descripción

Este proyecto implementa un servicio REST desarrollado en **Spring Boot** que permite consultar el precio aplicable de un producto de una cadena comercial (brand) para una fecha y hora específicas. Está diseñado utilizando una arquitectura hexagonal y utiliza una base de datos en memoria **H2** para almacenar los datos de ejemplo proporcionados.

### Características principales
- Exposición de un endpoint REST para consultar precios.
- Uso de arquitectura hexagonal para promover el desacoplamiento y la mantenibilidad.
- Pruebas unitarias y de integración para validar la funcionalidad del servicio.
- Base de datos en memoria H2 con inicialización automática de datos.

### Tabla de precios
La tabla `PRICES` en la base de datos contiene:
- **BRAND_ID**: Identificador de la cadena comercial.
- **PRODUCT_ID**: Identificador del producto.
- **PRICE_LIST**: Tarifa que aplica.
- **START_DATE** y **END_DATE**: Rango de fechas en el que aplica la tarifa.
- **PRIORITY**: Prioridad de la tarifa (en caso de conflictos, se elige la de mayor prioridad).
- **PRICE**: Precio final.
- **CURR**: Moneda del precio.

## Requisitos previos

- **Java 21**.
- **Maven 3.8+**.

## Configuración del proyecto

### Clonar el repositorio
```bash
git clone https://github.com/franciscoMigliardi24/inditex.git
cd inditex
```

### Ejecutar el servicio
1. Compila el proyecto:
   ```bash
   mvn clean install
   ```

2. Inicia el servicio:
   ```bash
   mvn spring-boot:run
   ```

3. El servicio estará disponible en:
   ```
   http://localhost:8080
   ```

## Endpoint principal

### GET `/api/prices`
Permite consultar el precio aplicable de un producto para una fecha y hora específicas.

#### Parámetros:
- **applicationDate**: Fecha y hora de aplicación (formato ISO-8601: `yyyy-MM-dd'T'HH:mm:ss`).
- **productId**: Identificador del producto.
- **brandId**: Identificador de la cadena comercial.

#### Ejemplo de solicitud:
```http
GET http://localhost:8080/api/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1
```

## Cómo correr los tests

1. Para ejecutar las pruebas unitarias y de integración, utiliza el siguiente comando:
   ```bash
   mvn test
   ```

2. El proyecto incluye pruebas que validan los siguientes escenarios:
    - Consultar precio a las 10:00 del 14 de junio de 2020.
    - Consultar precio a las 16:00 del 14 de junio de 2020.
    - Consultar precio a las 21:00 del 14 de junio de 2020.
    - Consultar precio a las 10:00 del 15 de junio de 2020.
    - Consultar precio a las 21:00 del 16 de junio de 2020.

3. Los resultados de las pruebas se mostrarán en la consola. Si todas pasan, verás el mensaje:
   ```
   BUILD SUCCESS
   ```

## Base de datos H2

Para inspeccionar la base de datos en memoria H2:
1. Accede al siguiente enlace mientras el servicio está en ejecución:
   ```
   http://localhost:8080/h2-console
   ```
2. Configura los parámetros de conexión:
    - **JDBC URL**: `jdbc:h2:mem:testdb`
    - **Username**: `sa`
    - **Password**: *(dejar vacío)*

3. Ejecuta consultas SQL directamente desde la consola web.
