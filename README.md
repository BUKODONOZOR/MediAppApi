Módulo de Autenticación y Registro de Usuarios (Spring Boot)

Descripción del Proyecto
Este módulo del sistema está desarrollado en Java Spring Boot y actúa como el backend de autenticación y registro de usuarios. Su principal objetivo es gestionar la seguridad de la aplicación y garantizar un flujo seguro y eficiente para registrar y autenticar usuarios. Este componente trabaja en conjunto con el módulo de Laravel, que utiliza los endpoints expuestos por este servicio para almacenar usuarios y gestionar su lógica de negocio.

El sistema implementa Spring Security para garantizar la protección de los datos y gestionar de manera segura las solicitudes de autenticación y registro.

Funcionalidades Principales
1. Autenticación de Usuarios
Validación de credenciales mediante JWT (JSON Web Tokens), asegurando que solo usuarios autorizados puedan acceder al sistema.
Los tokens se generan al inicio de sesión y se utilizan para autorizar solicitudes posteriores.
2. Registro de Usuarios
Los usuarios pueden registrarse mediante solicitudes API externas (como desde Laravel).
Los datos se almacenan en una tabla users, incluyendo información básica como nombre, correo electrónico y contraseña encriptada.
3. Gestión Segura de Contraseñas
Las contraseñas se almacenan utilizando algoritmos de encriptación proporcionados por Spring Security, asegurando que no se puedan leer en texto plano.
Arquitectura del Proyecto
El módulo sigue una estructura MVC (Modelo-Vista-Controlador), adaptada para servicios API REST.

1. Modelo
Entidad User: Representa la tabla users en la base de datos. Incluye atributos como:
id: Identificador único del usuario.
username: Nombre del usuario o correo electrónico.
password: Contraseña encriptada.
roles: Rol asignado al usuario (por ejemplo, USER o ADMIN).
2. Controladores
AuthController:
Maneja las solicitudes de inicio de sesión (/auth/login) y registro (/auth/register).
Retorna respuestas en formato JSON con mensajes claros y, en el caso de autenticación, un JWT.
3. Servicios
UserService:
Gestiona la lógica del negocio relacionada con los usuarios, como el registro y la búsqueda de usuarios existentes.
JwtService:
Genera y valida tokens JWT.
4. Seguridad
Spring Security Config:
Configura las rutas públicas y protegidas.
Gestiona la autenticación y autorización mediante JWT.
