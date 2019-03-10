# Demo Docker para aplicaciones Java

## Introduccion 
Durante la demo usaremos una aplicacion basada en este otro webinar:
[Construyendo un API REST con Spring](https://www.youtube.com/watch?v=u9p1Wdp3Z0w)

Es una aplicacion Java con Spring Boot que compilaremos usando Maven y usaremos una base de datos Mongo para guardar sus datos. 

Esta aplicacion expone una API REST con servicios para realizar acciones CRUD con usuarios.
La Url de la API es http://localhost:8080/api/user y sobre la que enviaremos peticiones HTTP con los metodos GET, POST, DELETE...

En **src/main/resources/application-local.properties** podemos encontrar la configuracion de acceso a la base de datos de Mongo.

Para lanzar la aplicacion con Maven usaremos un perfil 'local' que apunta al contendor de Mongo
```
mvn clean spring-boot:run -Dspring.profiles.active=local
```

Ademas en directorio raiz se puede encontrar una coleccion de Postman con las peticiones HTTP que hemos usado en la charla.

En la demo levantaremos 2 contenedores, un con una base de datos de Mongo y otra con la aplicacion Java. Ademas las configuraremos para que se puedan comunicar.

## Imagenes Docker

* Imagen de Mongo en [Docker Hub](https://hub.docker.com/_/mongo)

```
# Descargar la imagen del Docker Hub
docker pull mongo
# Lanzar el contenedor a partir de la imagen
docker run -it --hostname mongodb --name=mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin mongo
```

Opciones

**-i / --interactive** Mantiene abierto el STDIN

**-t / --tty** Pseudo TTY  

**-h / --hostname** Nombre del host

**--name** Nombre del contendor

**-p / --publish** Publica y mapea el puerto

**-e / --env** Estable una variable de entorno

## Dockerfile

* Tenemos varios Dockerfile definidos con distintas configuraciones para realizar pruebas.

* Generacion de imagen a partir de un Dockerfile

  El dockerfile en la misma carpeta donde se ejecuten los comandos
```
# Genera la imagen a partir del Dockerfile
docker build -t users:demo -f Dockerfile-maven-compile-once .
# Lanza la imagen que acabamos de generar
docker run -it -p 8080:8080 --name usersdocker --link mongodb users:demo
```

Opciones

**-t** Añadir un Tag a la imagen

**-f** Indicar el fichero del Dockerfile que queremos usar. Esto no es necesario si el fichero se llama Dockerfile

**--link** Conecta a un contenedor que se este ejecutando

## Docker Compose

## Plugins Maven
Algunos plugins que pueden ser utiles para proyectos Java con Maven

* [Spotify Plugin](https://github.com/spotify/dockerfile-maven)
Permite generar imagenes y publicarlas en el registro


* [Fabric8 Plugin](https://github.com/fabric8io/docker-maven-plugin)
Permite, entre otras cosas, lanzar un contenedor durante la fase de los test de integracion.

* [Ejemplos de uso](https://codefresh.io/howtos/using-docker-maven-maven-docker/)


## Comandos de Docker
Estos son algunos comandos que usaremos durante la charla
```bash
# Ver la metainformacion de una imagen/contenedor
docker inspect mongo
# Lista las imagenes que tenemos descargadas
docker images --all
# Lista los contenedores que tenemos y su estado
docker ps --all
# Borra una imagen
docker rmi [IMAGE_ID]
# Borra un contenedor 
docker rm [CONTAINER_ID/CONTAINER_NAME]
```