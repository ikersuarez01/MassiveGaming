<div align="center">
  <h1> MassiveGaming</h1>
  <img src="https://user-images.githubusercontent.com/58294628/221609108-527ae253-c774-45b3-bb9e-1808059f6f6b.png" width="400" height="400"/>
</div>

<div align="center">
<h1>FASE 1</h1>
</div>
<hr class="divider" />

Nombre de la aplicación web : Massive Gaming

Descripción aplicación: se trata de una una plataforma de venta online de videojuegos y consolas.

Funcionalidades públicas: sin registrarse se puede acceder al catálogo y ver las valoraciones.

Funcionalidades privadas: al registrarse o iniciar sesión, podrá realizar una compra, comprobar sus últimas compras y realizar valoraciones.

Entidades:

<li><strong>Usuario:</strong> es el cliente que se registra para realizar una compra y valora los productos.
<li><strong>Producto:</strong> elemento disponible para compra.
<li><strong>Videojuego:</strong> producto concreto del que se puede elegir su formato (físico o digital)
<li><strong>Consola:</strong> producto concreto del que se puede elegir el color.
<li><strong>Compra:</strong> transacción que almacena la fecha, el producto, la cantidad, el precio y el usuario. Se trata de una entidad débil.
<li><strong>Valoración:</strong> es la opinión que se hace de cualquier usuario de un producto. Solo un usuario que haya realizado una compra de este producto puede realizar una valoración sobre el mismo.
<li><strong>Carrito:</strong> contiene ítems de la compra.
<li><strong>Ítem:</strong> referencia a producto.

Funcionalidades servicio interno:

Actualización de stock.
Envío de correo electrónico al registrarse.
Envío de correo electrónico cuando el usuario olvida su contraseña.
Envío de correo electrónico con la información de la compra realizada.

<div align="center">
<h1>FASE 2</h1>
</div>
<hr class="divider" />
 
### Capturas de la Aplicación

-Pantalla principal sin iniciar sesión:
 ![image](https://user-images.githubusercontent.com/58294628/221614973-5ffbd391-a345-44e3-9a8e-2a8194cd0d69.png)

-Pantalla de inicio de sesión:
![image](https://user-images.githubusercontent.com/79792065/221647497-53c5a7b0-e2d9-4c61-b8f3-033a515f599b.png)

-Pantalla de creación de cuenta:
![image](https://user-images.githubusercontent.com/79792065/221648677-f99d0b61-0dd4-4891-b757-08a1d8daa788.png)

-Pantalla de selección de producto:
![image](https://user-images.githubusercontent.com/79792065/221647577-81bd5f33-d301-40d6-824c-48f27da4cb86.png)

-Pantalla de producto concreto sin iniciar sesión (permite visualizar el producto y sus valoraciones):
![image](https://user-images.githubusercontent.com/79792065/221647688-041b5f93-3924-44eb-a27d-d3b83f367403.png)

-Pantalla de producto concreto con sesión iniciada (permite adicionalmente añadir producto al carrito y valorar el producto):
![image](https://user-images.githubusercontent.com/79792065/221647869-0edf3021-6d11-4435-a9c5-b3453883d1b6.png)

-Pantalla de carrito (permite manipular el carrito y realizar compra):
![image](https://user-images.githubusercontent.com/79792065/221648117-b4c822ea-1f55-4dca-82dc-f0b74c299d49.png)

-Pantalla de perfil (permite editar datos del usuario y visualizar compras realizazdas):
![image](https://user-images.githubusercontent.com/79792065/221648325-ba5a111b-f40d-491a-80cc-b14979bf695c.png)

-Pantalla de contacto:
![image](https://user-images.githubusercontent.com/79792065/221647951-bc202e27-3cb8-4742-b02f-4590761ef896.png)



### Diagramas
#### Diagrama de Navegación de la Aplicación Web
  ![image](https://user-images.githubusercontent.com/58294628/221549188-7485bb4a-efbe-4d31-802b-8ef949701cc7.png)
#### Diagrama Entidad/Relación con Atributos
![image](https://user-images.githubusercontent.com/58294628/221549220-1b85aba0-1da8-4bf5-a3f2-6fac197bc7e7.png)
