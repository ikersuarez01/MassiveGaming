# MassiveGaming!

[logoMassiveGaming](https://user-images.githubusercontent.com/58294628/221608428-2faf171a-bc89-4d2b-8e8a-f251603ac100.png)

Nombre de la aplicación web : Massive Gaming

# FASE 1

Descripción aplicación: se trata de una una plataforma de venta online de videojuegos y consolas.

Funcionalidades públicas: sin registrarse se puede acceder al catálogo y ver las valoraciones.

Funcionalidades privadas: al registrarse o iniciar sesión, podrá realizar una compra, comprobar sus últimas compras y realizar valoraciones.

Entidades:

<li>Usuario: es el cliente que se registra para realizar una compra y valora los productos.
<li>Producto: elemento disponible para compra.
<li>Videojuego: producto concreto del que se puede elegir su formato (físico o digital)
<li>Consola: producto concreto del que se puede elegir el color.
<li>Compra: transacción que almacena la fecha, el producto, la cantidad, el precio y el usuario. Se trata de una entidad débil.
<li>Valoración: es la opinión que se hace de cualquier usuario de un producto. Solo un usuario que haya realizado una compra de este producto puede realizar una valoración sobre el mismo.
<li>Carrito: contiene ítems de la compra.
<li>Ítem: referencia a producto.

Funcionalidades servicio interno:

Actualización de stock.
Envío de correo electrónico al registrarse.
Envío de correo electrónico cuando el usuario olvida su contraseña.
Envío de correo electrónico con la información de la compra realizada.

# FASE 2
### Capturas de la Aplicación
### Diagramas
#### Diagrama de Navegación de la Aplicación Web
  ![image](https://user-images.githubusercontent.com/58294628/221549188-7485bb4a-efbe-4d31-802b-8ef949701cc7.png)
#### Diagrama Entidad/Relación con Atributos
![image](https://user-images.githubusercontent.com/58294628/221549220-1b85aba0-1da8-4bf5-a3f2-6fac197bc7e7.png)
