## Revisa Mi Grieta API Backend

### Stack
- Google App Engine
- Google Datastore
- Google Storage 
- Google Cloud Endpoints
- Java 7
- OpenApi

### Instrucciónes

Tomar de referencia [openapi.json](https://github.com/digaresc/revisamigrieta-api-backend/blob/master/openapi.json) para frontend o apps que consuman el api.

- Documentación del API: [http://digaresc.info/revisamigrieta-api-backend/](http://digaresc.info/revisamigrieta-api-backend/)
- Endpoint de subida de archivos:
[https://files-dot-revisamigrieta.appspot.com/images/](https://files-dot-revisamigrieta.appspot.com/images/) - *POST form-data multipart* 

Pasos para hacer submit de grietas:

1. Subir la imagen al endpoint de archivos. Al subir la imagen se recibe un id de grieta, el cual es requerido para continuar con el formulario.

2. Agregar grietaId al endpoint de subida de información de grieta. Sin un id previamente obtenido de subir imagenes/video, no sera posible subir información.

