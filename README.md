# Demo Docker para aplicaciones Java

## Imagenes Docker

* Imagen de Mongo en [Docker Hub](https://hub.docker.com/_/mongo)

```
docker pull mongo
docker run -it --hostname mongodb --name=mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin mongo
```

Options

**-i / --interactive** Mantiene abierto el STDIN

**-t / --tty** Pseudo TTY  

**-h / --hostname** Nombre del host

**--name** Nombre del contendor

**-p / --publish** Publica y mapea el puerto

**-e / --env** Estable una variable de entorno

## Dockerfile

* App con Spring Boot
[Construyendo un API REST con Spring](https://www.youtube.com/watch?v=u9p1Wdp3Z0w)

La applicacion tiene la configuracion para la base de datos en ***/src/main/resources/application-local.properties***

Para lanzar la app con Maven usaremos un perfil 'local' que apunta al contendor de Mongo
```
mvn clean spring-boot:run -Dspring.profiles.active=local
```

* API REST:
http://localhost:8080/api/user

* Generamos la imagen y la lazamos.
El dockerfile en la misma carpeta donde se ejecuten los comandos
```
docker build -t users:demo .
docker run -it -p 8080:8080 --name usersdocker --link mongodb users:demo
```

## Comandos de Docker
```
docker inspect mongo
docker images --all
docker container ps -a
docker rm [CONTAINER_ID]
docker rmi [IMAGE_ID]
```