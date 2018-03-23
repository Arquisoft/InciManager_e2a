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

1. Descargaremos Kafka 2.11 de este [enlace] (http://apache.rediris.es/kafka/1.0.1/kafka_2.11-1.0.1.tgz)

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
  
### Usuarios por defecto
* Login: juan (Agent: Person), pedro (Agent: Entity), raul (Agent: Sensor)
* Password: 1234
