# spring-day-covid

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