# API de @RevisaMiGrieta (Alpha)

## Objetivo

- Brindar un punto de acceso para el registro de solicitudes de revisiones “rápidas” sobre la seguridad de un inmueble (API, Web App)
- Permitir la captura de revisiones sobre las solicitudes y documentar la evidencia
- Emitir una recomendación rápida y enlazarte a otras herramientas / Organismos
- Generar un repositorio de imágenes, cuestionarios y revisiones para usarlo para entrenar un sistema de Machine Learning para en base a resultados evaluar su uso posterior como un sistema de reacción rápida


## Dependencias:

### Stack

- Google App Engine
- Google Datastore
- Google Storage 
- Google Cloud Endpoints
- Java 7
- OpenApi

## Instrucciones
### Desarrollo local
Compila el proyecto y cargalo de manera local:

```
mvn clean package
mvn endpoints-framework:openApiDocs
gcloud service-management deploy target/openapi-docs/openapi.json
```
Crear variable de sistema indicando el servidor de endpoints:

En Linux or MacOS:

```
export ENDPOINTS_SERVICE_NAME=[YOUR_PROJECT_ID].appspot.com
```

En Windows:

```
$Env:ENDPOINTS_SERVICE_NAME="[YOUR_PROJECT_ID].appspot.com"
```

Autenticar

```
gcloud auth application-default login
```


**Correr de manera local:**

```
mvn appengine:run
```

### Staging
Compila el proyecto y cargalo a Google App Engine:

```
mvn clean package
mvn endpoints-framework:openApiDocs
gcloud service-management deploy target/openapi-docs/openapi.json
mvn appengine:deploy
```

## ¿Como consumir el API?
- [Documentación del API en formato openapi.json](http://digaresc.info/revisamigrieta-api-backend/)
- [Endpoint de subida de archivos](https://revisamigrieta.appspot.com/_ah/api/upload)

### Pasos a seguir para insertar grietas e insertar revisiones
Solo tres endpoints solicitan token de autenticación:

- Insertar Grietas
- Insertar Revisión de Grietas

**Draft de objeto JSON de grieta con revisiones**

```json
{
    "id": "5671831268753408",
    "files": [
        "70b462c6-04f9-49a0-a7b8-2c2d5a6703d0-1506493156941.JPG",
        "b6d304f4-6764-42d2-b3fd-c271a1d2f8d3-1506493158467.JPG"
    ],
    "geolocalizacion": {
        "latitude": 32.1,
        "longitude": 2.3
    },
    "tipo": "INTERNA",
    "ubicacion": "LOSA",
    "revisada": true,
    "createdOn": "2017-09-27T06:05:21.598Z",
    "comentario": "comentario",
    "diagonalesLosa": false,
    "diagonalesPiso": false,
    "paralelasPiso": false,
    "userId": "idusuario",
    "estadoDeObra": {
        "hundimientos": false,
        "desplomes": false,
        "golpeteo": false,
        "desprendimiento": false,
        "vibraciones": false,
        "pisosHuecos": false,
        "mas20porciento": false
    },
    "revisiones": [
        {
            "diagonalesLosa": false,
            "paralelasPiso": true,
            "diagonalesPiso": false,
            "peligroInminente": true,
            "comentarios": "Comentario 1",
            "revisadaPor": "idusuario",
            "createdOn": "2017-09-27T06:07:05.985Z"
        },
        {
            "diagonalesLosa": false,
            "paralelasPiso": true,
            "diagonalesPiso": false,
            "peligroInminente": true,
            "comentarios": "Comentario 2",
            "revisadaPor": "idusuario",
            "createdOn": "2017-09-27T06:07:35.210Z"
        }
    ]
}
```
**Pasos para insertar imagenes:**

1. Obtener token de autenticación via firebase o via solicitud al equipo.
2. Una solicitud insertada no va a ser valida hasta que se cuente con imagenes sobre ella.
3. Al registrar una grieta se obtiene el ID del elemento como respuesta del servidor.
4. Ese ID se requiere para la subida de archivos.


### Ejemplo de subida de archivos

```
curl -X POST \
  https://revisamigrieta.appspot.com/upload/ID-GRIETA \
  -H 'authorization: Bearer JWT-TOKEN-FIREBASE' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; \
  -F file1=@imagen1.png \
  -F fdfgd=@imagen2.jpg 
```

## Documentación sobre el problema
- [Draft de Preguntas actualmente usandose para el modelado de la DB](https://github.com/digaresc/revisamigrieta-api-backend/blob/master/PREGUNTAS.MD)


## Links de ayuda
- [Google Cloud Endpoints Quickstart](https://cloud.google.com/endpoints/docs/frameworks/java/get-started-frameworks-java)
- [Google App Engine Quickstart](https://cloud.google.com/appengine/docs/standard/java/quickstart)

## Contribuye
Revisa los [issues](https://github.com/digaresc/revisamigrieta-api-backend/issues) y manda Pull Request.

## Team de este proyecto
 - [@digaresc](http://digaresc.info)
 - [@xcoatl](https://github.com/xcoatl)
 - [@scriptArchitect](https://github.com/scriptArchitect)
 - [Codeando Mexico](https://github.com/CodeandoMexico/terremoto-cdmx)
 
## Repositorios relacionados:

- [Guía rápida y accesible para usuarios del hashtag #RevisaMiGrieta](https://github.com/codersmexico/grieta-landing)
    - Lead: [@mike3run](https://github.com/mike3run)
- [Revisa Mi Grieta Bot](https://github.com/codersmexico/revisa-mi-grieta-bot)
    - Lead: [@poguez](https://github.com/poguez)
- [Revisa Mi Grieta Frontend](https://github.com/digaresc/revisamigrieta-frontend)
    - Lead: [@digaresc](https://github.com/digaresc)
