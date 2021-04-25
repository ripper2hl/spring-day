# spring-day-covid

## Prerequisitos:

* Java 11
* Maven
* Docker
* Nodejs

## Ejecutar el proyecto:

* Levantar la base de datos: 
 
    ```bash
    docker run -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d jesusperales/spring-day-db:latest
    ```

* Levantar backend:
  
    ```bash
    git clone git@github.com:ripper2hl/spring-day.git
    cd spring-day
    mvn spring-boot:run
    ```
    Podras ver la pantalla de swagger para probar algunos endpoints
    http://localhost:8080/swagger-ui/index.html
  
     ## POR ALGUNA RAZON CHROME SIEMPRE DIRIGE A HTTPS FIREFOX NO

* Levantar frontend

    ```bash
    git clone git@github.com:ripper2hl/spring-day-frontend.git
    cd spring-day-frontend
    npm run start
    ```

## Procesar la información de la base de datos 

Debido al enorme cantidad de registros para cargar el archivo completo
se necesita hacer un split de el archivo.

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