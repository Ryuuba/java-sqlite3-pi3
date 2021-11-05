# HINTS

## Detalles sobre la creación de la base de datos

La base de datos `datastudent.db` se construyó mediante la ejecución de los siguiente comandos dentro del directorio del proyecto:

```Bash
$ sqlite3 datastudent.db
sqlite> CREATE TABLE student (id INT, firstname VARCHAR(20), lastname VARCHAR(20), campus VARCHAR(3));
sqlite> INSERT INTO student VALUES (12345123,'Manuel','Mendoza','LER'); 
sqlite> .mode csv
sqlite> .import student.csv student
sqlite> .quit
```

El comando `sqlite3 datastudent.db` crea una base de datos nueva de nombre `datastudent.db`. El segundo comando crea una tabla de cuatro columnas que contienen los siguientes datos: la matrícula del estudiante, su nombre, su apellido y el campus al que pertence. El tercer comando inserta en la tabla una fila nueva que contiene los datos que se leen en el comando. El cuarto comando configura el motor de la base de datos en modo CSV para procesar un archivo separado por comas. El quinto comando importa un archivo que contiene filas de cuatro columnas que, justamente, corresponden a las columnas de la tabla `student`. Es importante que los datos almacenados en el archivo CSV a ser importados no tengan un encabezado. El último comando cierra la herramienta sqlite3.

## Detalles sobre la configuración del proyecto MAVEN

El archivo POM.xml que configura el proyecto MAVEN contiene las siguientes secciones extras que permiten conectar el programa JAVA con una base de datos basada en sqlite3.

```XML
    <dependency>
      <groupId>org.xerial</groupId>
      <artifactId>sqlite-jdbc</artifactId>
      <version>3.36.0.3</version>
    </dependency>
```
La dependecia anterior descarga el JAR del conecto sqlite-jdbc en el repositorio local `~/.m2/repository/org/xerial/sqlite-jdbc/3.36.0.3`.

```XML
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <configuration>
    <archive>
        <manifest>
        <mainClass>com.uamc.App</mainClass>
        </manifest>
    </archive>
    <descriptorRefs>
        <descriptorRef>jar-with-dependencies</descriptorRef>
    </descriptorRefs>
    </configuration>
</plugin>
```

El *plugin* anterior se coloca en la sección de *plugins* y permite crear un archivo JAR que contiene todas las dependencias que el programa necesita para ser ejecutado. Nota que la sección `<mainClass>com.uamc.App</mainClass>` debe configurarse de acuerdo con los datos que correspondan a la clase principal.

Para construir el archivo JAR es necesario ejecutar el siguiente comando en la raíz del proyecto.

```Bash
mvn clean compile assembly:single
```

Si no se desea compilar el archivo JAR, entonces la aplicación puede ejecutarse mediante la ejecución de los siguientes comandos, dentro del directorio del proyecto.

```Bash
export CLASSPATH="~/dbdemo/target/classes:~/.m2/repository/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar"
java com.uamc.App
```

El primer comando configura la variable CLASSPATH para que el comando `java` sea capaz de encontrar las dependencias de la aplicación. El segundo comando ejecuta la aplicación. Nota que el argumento `com.uamc.App` corresponde al nombre completo de la clase principal (*fully-qualified main class name*).
