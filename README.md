[![Codacy Badge](https://api.codacy.com/project/badge/Grade/62678ef56d424b1cb7b719e3d279ba67)](https://www.codacy.com/app/jelabra/InciManager_e2a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_e2a&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Arquisoft/Agents_e2a.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e2a)
[![codecov](https://codecov.io/gh/Arquisoft/InciManager_e2a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e2a)

# InciManagement #

Toda la documentacion en informacion de este modulo y el proyecto al que pertenece se encuentra en este otro [repositorio](https://github.com/Arquisoft/inci_e2a)

## Autores ##

Estos son los autores de la versión del proyecto del curso 2017-2018.

+ Jesús García Minas.
> [@JesusGarciaMinas](https://github.com/JesusGarciaMinas)

> UO250999

+ Pelayo García Torre.
> [@Pelayo-Torre](https://github.com/Pelayo-Torre)

> UO251143

+ José Antonio García García.
> [@MrKarrter](https://github.com/MrKarrter)

> UO251317

+ Herminio García. (Profesor) 
+ Creador del esqueleto.
> [@herminiogg](https://github.com/herminiogg)

## Arrancar el proyecto

1. Descargaremos Kafka 2.11 pinchando en el siguiente [enlace](http://apache.rediris.es/kafka/1.0.1/kafka_2.11-1.0.1.tgz)

2. Abrimos una ventana de comandos y realizamos la siguiente orden:
  * 2.a Windows: bin\windows\zookeeper-server-start.bat config\zookeeper.properties
  * 2.b MacOS: bin\zookeeper-server-start.sh config\zookeeper.properties
  
3. Abrimos otra ventana de comandos y realizamos la siguiente orden:
  * 3.a Windows: bin\windows\kafka-server-start.bat config\server.properties
  * 3.b MacOS: bin\kafka-server-start.sh config\server.properties
  
4. Con una tercera linea de comandos nos situaremos en la raíz del proyecto y ejecutaremos la orden: mvn spring-boot:run

5. Accedemos a la dirección: http://localhost:8070/

6. Una vez estamos en la aplicación podremos acceder con un usuario registrado y:
  * 6.a Enviar una nueva incidencia
  * 6.b Ver todas las incidencias enviadas por el usuario
  
6.a En el caso de que queramos enviar una incidencia deberemos seguir una serie de normas:
* Está prohibido el uso de los símbolos "@" y "$" (Utilizados para mandar la información a través de Apache Kafka).
* El título y la descripción deberán tener un tamaño coherente (Entre 5 y 24/200 caracteres).
* Las etiquetas se separarán por comas sin dejar espacios.
* Los campos se separarán por comas sin dejar espacios y cada campo seguirá la forma Clave:Valor.
* La localización se registrará automáticamente, sin embargo, será posible cambiarla a mano siempre y cuando siga siendo un número decimal.
  
### Usuarios por defecto
* Login: juan, pedro, raul
* Password: 1234
* King: Person, Entity, Sensor (Respectivamente)
