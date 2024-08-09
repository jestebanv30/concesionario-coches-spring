# CONCESIONARIO DE COCHES - BACKEND

## Descripción

Este proyecto se desarrolla con Spring Boot 3 y Java 17, Spring Data JPA, Spring Security, Spring Cloud, Spring web. Librerías externas: Swagger, Lombok, MapStruct. Gestor de paquetes: Gradle. 

Se implementa **Arquitectura Hexagonal** para una estructura modular, flexible y con prácticas de código limpio. La aplicación permite a los administradores publicar nuevos autos y gestionar el inventario. Se integra con una base de datos PostgreSQL y está configurado para trabajar en diferentes perfiles de entorno (desarrollo y producción).

Principales características:

- Inyección de dependencias.
- Seguridad con JWT y autenticación.
- Integración con JPA y MapStruct.
- Integraciones relacionales(manytomany...) en las entidades.
- Validaciones en el service para testing de caja negra.
- Configuración en múltiples entornos con Azure Key Vault.

## Visuales

Video miniatura de Youtube (por subir).

## Información Despliegue Azure 📦

Se creó un servidor de base de datos en Azure para PostgreSQL, se configuró con claves únicas de acceso, restrincciones para las IP y flujo de seguridad en Azure Active Directory. Por lo tanto, la información que está en la configuración de conexión para Azure Key Vaults no les servirá.

## Construido Con 🛠️

- Spring Boot - Framework utilizado
- PostgreSQL - Sistema de base de datos
- Azure - Servicios en la nube y Key Vault
