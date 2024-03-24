# TALLER 6: PATRONES ARQUITECTURALES EN AMAZON WEB SERVICES

## Descripción de la aplicación 
Este laboratorio se centra en el análisis de patrones arquitecturales dentro del entorno de Amazon Web Services (AWS) y su aplicación en el desarrollo de aplicaciones web. En este caso específico, la aplicación web consiste en un servicio de registro de eventos que captura y almacena cadenas de texto para su posterior visualización en una página web. Para llevar a cabo esta tarea, se emplea el framework [SparkJava](https://sparkjava.com/) para la creación de servicios REST, junto con el motor de base de datos [MongoDB](https://www.mongodb.com/) para el almacenamiento de datos.

La infraestructura de la aplicación se gestiona mediante contenedores Docker, lo que proporciona portabilidad y consistencia en el entorno de desarrollo y despliegue. Finalmente, la aplicación se despliega en una instancia EC2 de AWS, lo que ofrece escalabilidad y flexibilidad para adaptarse a las necesidades cambiantes de carga y demanda.

Este enfoque arquitectural aprovecha las capacidades de AWS para proporcionar una infraestructura robusta y escalable para el desarrollo y despliegue de aplicaciones web, garantizando un rendimiento óptimo y una gestión eficiente de los recursos.

### Requisitos 

- [Git](https://git-scm.com/) - Control de versiones
- [Maven](https://maven.apache.org/) - Gestor de dependencias
- [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación
- [Docker](https://www.docker.com/) - Motor de contenedores

> [!IMPORTANT]
> Es necesario tener instalado Git, Maven, Docker y Java 17 en aws para poder ejecutar el proyecto.

## Ejecutando la aplicación 

Para poder ejecutar el program primero descarguelo del repositorio.

```bash
git clone https://github.com/cattus09/taller_6_AREP.git
cd src/
```

Para ejecutar la aplicación, ejecute los siguientes comandos en la raíz del proyecto. 

```bash
mvn clean install
docker-compose up -d
```

El anterior comando limpiará las contrucciones previas, compilará y luego ejecutará en distintos contenedores los servicios de la aplicación.

En su navegador entre a: [http://localhost:35080](http://localhost:35080) para ver la aplicación en funcionamiento.

## Generando Javadoc 

Para generar la documentación de la aplicación, ejecute el siguiente comando, los archivos Javadoc se generarán en el directorio `target/site/apidocs` dentro del proyecto.

```bash
mvn site
```

Despues de ejecutar el comando anterior, abra el archivo `index.html` que se encuentra en el directorio `target/site/` con su navegador de preferencia luego búsque la sección **project reports** y haga click en la opción que dice `Project Javadoc` para ver la documentación de la aplicación.

## metáfora de arquitectura

La aplicación que describes tiene varios componentes interconectados

**Aplicación web APP-LB-RoundRobin:**

- Está compuesta por un cliente web y al menos un servicio REST.
- El cliente web tiene un campo de entrada de texto y un botón.
- Cuando un usuario envía un mensaje, el cliente web lo envía al servicio REST.
- El servicio REST procesa el mensaje y actualiza la pantalla del cliente web con la información devuelta en formato JSON.
- Implementa un algoritmo de balanceo de cargas de Round Robin para distribuir la carga entre tres instancias del servicio LogService.

**LogService:**

- Es un servicio REST que recibe cadenas de texto.
- Almacena estas cadenas en la base de datos MongoDB.
- Responde con un objeto JSON que contiene las 10 últimas cadenas almacenadas y sus fechas correspondientes.

**Servicio MongoDB:**

- Es una instancia de MongoDB que se ejecuta dentro de un contenedor Docker en una máquina virtual EC2. Su función principal es almacenar datos las cadenas de texto que recibe el servicio LogService.

![image](https://github.com/ELS4NTA/AREP-LAB-06/assets/99996670/a172b6c2-c7d3-4bab-8012-e537fc0f0340)

## Proyecto en AWS


