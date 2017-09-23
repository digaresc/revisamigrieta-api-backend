## Revisa Mi Grieta API Backend

### Stack
- Google App Engine
- Google Datastore
- Google Storage 
- Google Cloud Endpoints
- Java 7
- OpenApi

### Instrucciones

Tomar de referencia [openapi.json](https://github.com/digaresc/revisamigrieta-api-backend/blob/master/openapi.json) para frontend o apps que consuman el api.

Documentación del API: 
[http://digaresc.info/revisamigrieta-api-backend/](http://digaresc.info/revisamigrieta-api-backend/)

Endpoint de subida de archivos:
HTTP URL: [https://revisamigrieta.appspot.com/_ah/api/upload](https://revisamigrieta.appspot.com/_ah/api/upload)
HTTP Method: POST form-data multipart 

### Pasos para hacer submit de grietas:

Subir la imagen al endpoint de archivos. Al subir la imagen se recibe un id de grieta (grietaId), el cual es requerido para continuar con el formulario.

Agregar grietaId al endpoint de subida de información de grieta. Sin un id previamente obtenido de subir imagenes/video, no sera posible subir información.

Ejemplo de imágenes:

- Todas las imágenes se guardan en formato fuente: [Imagen tamaño completo](https://storage.googleapis.com/revisamigrieta-images/3f6cc669-c105-4e60-9106-a188820df25e-1506069507417.jpeg).
- Todas las imagenes cuentan con un thumb: [Imagen 300*300](https://storage.googleapis.com/revisamigrieta-images/3f6cc669-c105-4e60-9106-a188820df25e-1506069507417-thumb.jpeg).

Ejemplo en CURL:

``` curl -X POST \
  https://revisamigrieta.appspot.com/_ah/api/upload \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data;' \
  -F file1=@Foto.jpeg \
  -F file2=@IMG_2977.jpg \
  -F file3=@IMG_20170905_183133149.jpg 
```
  
Response:

``` 
{
    "grietaId": 5634472569470976
} 
```
