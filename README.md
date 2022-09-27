# holaluz-test

Prueba técnica de Rubén de Paz, Septiembre 2022.

1. La aplicación está construida con Java usando Maven y SpringBoot y librerías de terceros cómo lombok o opencsv.
El proceso **mvn install** genera un .jar que podría ser lanzado directamente para usar la aplicación. 
Simplemente tendríamos que lanzar el jar desde un directorio donde tengamos el fichero de entrada con las lecturas que queremos investigar:


```console
  java -jar holaluz-test-0.0.1-SNAPSHOT.jar 2016-readings.csv
```

2. Para no depender de si la máquina tiene el jdk y las librerías de Maven instaladas he añadido un Dockerfile para contenerizar el jar de la aplicación y el jdk. El proceso de construcción de la imagen es, situándonos en el directorio raíz del proyecto:

```console
  docker build -t holaluz .
```


3. Una vez construida la imagen la podemos lanzar con **docker run**. La aplicación necesita un fichero de entrada, que necesitamos mapear dentro del container en el momento de la ejecución de la siguiente forma:

```console
  docker run -i -v ${PWD}:/app/files holaluz files/2016-readings.csv
```
>En este caso estamos mapeando el directorio actual **${PWD}** en el directorio del container **app/files** y luego le estamos diciendo a la aplicación
que el fichero de entrada es **2016-readings.csv** que es un fichero que tendremos en el directorio en el que nos encontramos.

4. Para no tener que construir la imagen en local, la he subido a mi registro de DockerHub, por lo tanto se puede descargar directamente desde ahí con el comando:

```console
  docker pull rubendepaz1/holaluz:latest
```
>Y entonces para ejecutarla habría que añadir el usuario:
```console
  docker run -i -v ${PWD}:/app/files rubendepaz1/holaluz files/2016-readings.csv
```

5. La aplicación controla que se pasa cómo parámetro el fichero de lecturas, y si no se encuentra se muestra un mensajeç
6. La salida esperada es una tabla con las lecturas desviadas más de un 50% por encima o por debajo de la media de cada cliente:

```
Parsing CSV...
Number of records: 120
| Client              | Month              | Suspicious         | Median
 -------------------------------------------------------------------------------
|583ef6329d916        |2016-09             |2479               |37791.333333333336
|583ef6329d7b9        |2016-09             |3564               |39671.75
|583ef6329d89b        |2016-09             |162078               |63849.75
|583ef6329d89b        |2016-10             |7759               |63849.75

```
