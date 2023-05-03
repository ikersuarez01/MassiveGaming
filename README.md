<div align="center">
  <h1> MassiveGaming</h1>
  <img src="https://user-images.githubusercontent.com/58294628/221609108-527ae253-c774-45b3-bb9e-1808059f6f6b.png" width="400" height="400"/>
</div>

<div align="center">

<h1>VIDEO DEMOSTRACIÓN</h1>
</div>
<hr class="divider" />

https://youtu.be/x83EA_9kFCM
  
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

Envío de correo electrónico al registrarse.
Envío de correo electrónico al modificar datos privados
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

-Pantalla de perfil (permite editar datos del usuario y visualizar compras realizadas):
![image](https://user-images.githubusercontent.com/79792065/221648325-ba5a111b-f40d-491a-80cc-b14979bf695c.png)

-Pantalla de contacto:
![image](https://user-images.githubusercontent.com/79792065/221647951-bc202e27-3cb8-4742-b02f-4590761ef896.png)



### Diagramas
#### Diagrama de Navegación de la Aplicación Web
  ![image](https://user-images.githubusercontent.com/58294628/221549188-7485bb4a-efbe-4d31-802b-8ef949701cc7.png)
#### Diagrama Entidad/Relación con Atributos
![image](https://user-images.githubusercontent.com/58294628/221549220-1b85aba0-1da8-4bf5-a3f2-6fac197bc7e7.png)

<div align="center">
<h1>FASE 3</h1>
</div>
<hr class="divider" />

### Navegacion

Los diagramas de navegación se han mantenido desde la fase anterior

### Diagrama de clases de la aplicación

![image](https://user-images.githubusercontent.com/58294628/228015065-c8e91f78-9b70-4d4f-966b-8229fa816c7b.png)

  
### Documentación de la interfaz del servicio interno.

El servicio interno utiliza RabbitMq para crear una cola de mensajes y comunicarse con la página web, de esta forma, se obtienen los mensajes que tiene que mandar con todos los datos necesarios (email, asunto y el cuerpo del mensaje).
Para el envío de mensajes se han utilizado los recursos proporcionados por Spring Email.
  
### Instrucciones para desplegar la aplicación

Utilizando el entorno de desarrollo de eclipse, se ha realizado la compilación de los dos proyectos en archivos "jar", consiguiendo así un "jar" para desplegar la página web Massive Gaming y otro "jar" para desplegar el servicio interno de la página.
```
Project >> Run As >> Maven Install
```
Una vez creada la máquina virtual en oracle, necesitamos crear un par de claves privada-publica que nos permita conectarnos a la máquina. La clave privada que se genera la tenemos que agregar a una carpeta .ssh. El comando para realizar la conexión a la maquina virtual es el siguiente:
```
ssh -i /.ssh/MassiveGaming.pem ubuntu@10.100.139.2
```
Para subir los archivos jar mediante la terminal se utiliza el siguiente comando:
```
scp -i ~/.ssh/MassiveGaming.pem nombreDelArchivoJar.jar ubuntu@10.100.139.2:/home/ubuntu
```
Para ejecutar los archivos jar se necesita instalar el jdk:
```
sudo apt install default-jdk
```
Y para ejecutarlos se utiliza el siguiente comando:
```
java -jar nombreDelArchivoJar.jar
```

Sin embargo, ya que nuestra aplicación tiene implementado un servicio interno y una base de datos, ha sido necesario instalar mySql y RabbitMQ.

Instalación y configuración de mySql :
```
sudo apt install mysql-server
sudo mysql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Tapatapita123';
exit
sudo mysql_secure_instalation
```
Instalación de RabbitMQ:
```
sudo apt-get install curl gnupg apt-transport-https -y
curl -1sLf "https://keys.openpgp.org/vks/v1/by-fingerprint/0A9AF2115F4687BD29803A206B73A36E6026DFCA" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/com.rabbitmq.team.gpg > /dev/null
curl -1sLf "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0xf77f1eda57ebb1cc" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg > /dev/null
curl -1sLf "https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/io.packagecloud.rabbitmq.gpg > /dev/null
sudo tee /etc/apt/sources.list.d/rabbitmq.list <<EOF
deb [signed-by=/usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg] http://ppa.launchpad.net/rabbitmq/rabbitmq-erlang/ubuntu bionic main
deb-src [signed-by=/usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg] http://ppa.launchpad.net/rabbitmq/rabbitmq-erlang/ubuntu bionic main
deb [signed-by=/usr/share/keyrings/io.packagecloud.rabbitmq.gpg] https://packagecloud.io/rabbitmq/rabbitmq-server/ubuntu/ bionic main
deb-src [signed-by=/usr/share/keyrings/io.packagecloud.rabbitmq.gpg] https://packagecloud.io/rabbitmq/rabbitmq-server/ubuntu/ bionic main
EOF
sudo apt-get update -y
sudo apt-get install libssl1.1 
sudo apt-get install -y erlang-base \
                        erlang-asn1 erlang-crypto erlang-eldap erlang-ftp erlang-inets \
                        erlang-mnesia erlang-os-mon erlang-parsetools erlang-public-key \
                        erlang-runtime-tools erlang-snmp erlang-ssl \
                        erlang-syntax-tools erlang-tftp erlang-tools erlang-xmerl
sudo apt-get install rabbitmq-server -y --fix-missing
```
<div align="center">
<h1>FASE 4</h1>
</div>
<hr class="divider" />

### Diagrama de clases de la aplicación actualizado
![image](https://user-images.githubusercontent.com/58294628/235978931-9d0df667-9cf8-422a-ad32-813d932b54e4.png)

### Diagrama de infraestructura con los contenedores y sus comunicaciones
<div align="center">
  <img src="https://user-images.githubusercontent.com/58294628/235983413-ebaff400-087c-4160-bc77-8872a7d9a1d7.png"/>
</div>
