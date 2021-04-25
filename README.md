# spring-day-covid

# Video demo 

https://youtu.be/p1c9MQ9317o


## Prerequisitos:

* Java 11
* Maven
* Docker
* Nodejs
* Es necesario tener abiertos los puertos 8080, 5432, 4200

## Ejecutar el proyecto:

* Levantar la base de datos: 
 
    ```bash
    docker run -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword --name spring-day-db -d jesusperales/spring-day-db:latest
    docker logs -f spring-day-db
    ```
  
  Una vez que la base de datos envie el mensaje de que puede recibir conexiones podremos proseguir.
  mensaje como este:
  
      
      2021-04-25 12:47:04.205 UTC [1] LOG:  database system is ready to accept connections
      

* Levantar backend:
  
    ```bash
    git clone git@github.com:ripper2hl/spring-day.git
    cd spring-day
    mvn spring-boot:run
    ```
    Podras ver la pantalla de swagger para probar algunos endpoints
    http://localhost:8080/swagger-ui/index.html
  
     ## POR ALGUNA RAZON CHROME SIEMPRE DIRIGE A HTTPS, FIREFOX NO

* Levantar frontend

    ```bash
    git clone git@github.com:ripper2hl/spring-day-frontend.git
    cd spring-day-frontend
    npm i
    npm run start
    ```

## Para procesar la información de la base de datos si no se tiene la imagen de docker 

Debido al enorme cantidad de registros para cargar el archivo completo
se necesita hacer un split del archivo.

El cual se puede hacer con el siguiente comando:

```bash
split -d -l 1000000 COVID19MEXICO.csv covid-parte.cs
```

Este comando generara archivos de la siguiente forma, los cuales
podremos subir de uno por uno.

```bash
covid-parte.csv06
covid-parte.csv05
covid-parte.csv04
covid-parte.csv03
covid-parte.csv02
covid-parte.csv01
```

La carga de cada archivos de 1 millon de registros toma aproximadamente 5 minutos cada uno.