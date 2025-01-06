# BDA_LAB3

Prerequisitos de instalacion:

-Postgres 16

-JDK 17

-MongoDB version mas actual

-Visual Studio Code version mas actual

-Intellij version mas actual

-Postgis version mas actual

-Node js 20.16.0

Intrucciones de instalacion:

Una vez descargado/clonado el repositorio siga los siguientes pasos:

1.-Abra pgadmin

2.-Abra una consola en la direccion dentro de Database y ejecute el siguiente comando: "psql -U El usuario con el que instalo postgres -h localhost -p el puerto con cual instalo postgres -f dbCreate.sql"

3.-Ahora en la misma consola ejecute el siguiente comando para hacer el rellenado de datos:"psql -U El usuario con el que instalo postgres -h localhost -d lab3bda -f dump.sql"

4.- Abra MongoDB Compass

5.- Cree una conexion con cualquier nombre con la uri: "mongodb://localhost:27017"

6.-Abra intellij idea y abra la carpeta ""BackendLab3"

7.-Ahora modifique el aplicaction properties:

    -DB_URL=jdbc:postgresql://localhost:el puerto con cual instalo postgres/lab3bda

    -USER=El usuario con el que instalo postgres

    -PASSWORD=La contraseña con la que instalo postgres

8.- Ahora corra el archivo BackendLab3Application presionando el boton de play

9.- Luego dentro de Visual Studio Code abra "FrontEndLab3"

10.- Acceda a este link con el correo usach https://drive.google.com/drive/folders/1A9UYUIQ9KQy_Yf2CQyWUI7lB-p42thyZ?usp=sharing descargue el archivo .env y peguelo en "FrontEndLab3"  y renombrelo a ".env"

11.- Abra un terminal y ejecute "npm install"

12.- Luego ejecute "npm audit fix" y "npm install axios"

13.- Haga correr el frontend en la consola anote "npm run dev" y presione con ctrl + click izquierdo http://localhost:3000/ o este link



