# leal_app
Top Trend Reddit Test App
--------------------------------------------

Arquitectura usada para las capas de la aplicación y clases.
En la presente versión de la app se realizaron las capas de UI, negocio y base de datos las cuales consisten en las clases siguientes clases.
Capa de base de datos: Clase Data la cual se encarga de conectar y leer los datos JSON y la clase post que toma estos datos y da forma a la publicación.
Capa de Negocio: para esta capa se creo la clase Holder la cual mantiene una lista de las publicaciones tomadas del JSON
Capa de UI: Clase Fragment y la Main_activity las cuales hacen uso de las clases anteriores para inicializar y representar los datos ante el usuario.

Legibilidad del código
El código se encuentra escrito enteramente en un solo idioma (acrónimos de las variables), se sigue la estructura de bloques y líneas de código cortas. 

Principio de responsabilidad única
Este principio indica que cada clase debe tener una sola funcionalidad sobre el código desarrollado. Ejm: Clase Data solo se encarga de conectar y leer los datos del JSON.

--------------------------------------------
