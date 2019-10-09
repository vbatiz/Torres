# Redes de Comunicación.
Tarea 3<br>
Materia: Tecnologías de Programación de la MCC del ITC
<br>
### Tutor: Dra. María Lucía Barrón Estrada ##

#### Autor: Víctor Bátiz

#### Lista de entregables:
1. El código fuente se encuentra en la carpeta: src\Torres (https://github.com/vbatiz/Torres/tree/master/src/pTorres)
2. El Diagrama UML final: DiagramaUML.png 
![alt text](https://github.com/vbatiz/BlackjackMV/blob/master/DiagramaUML.png)

#### Descripción
El presente proyecto es una implementación en Java del proyecto Redes de Comunicaciones utilizando Grafos. Esta desarrollado utilizando la consola como interfaz y utilizando el IDE IntelliJ IDEA Ultimate 2019.2.
<br><br>
En esta implementación se siguieron las siguientes indicaciones:
##### Descripción y Requisitos:
La compañía ENLACE proporciona servicios de comunicación por medio de torres para las compañías de telefonía celular.
1. Cada torre tiene al menos un enlace de comunicación de un solo sentido hacia otra torre. 
2. Los nombres de las torres inician con letra, pueden contener solamente letras y dígitos (a-z, A-Z, 0-9) y ser de longitud desde 1 hasta 15 caracteres. Las letras mayúsculas y minúsculas no se distinguen, ejemplo Culiacan1 es igual que culiacan1.
3. Los datos de entrada se encuentran en un archivo de datos y son un conjunto de parejas de nombres de torres que definen un enlace (abierto o cerrado) de un solo sentido entre las torres o una pregunta (si existe un enlace para dos torres).
4. Los enlaces y las preguntas pueden estar mezclados en los datos de entrada.
5. Un enlace abierto se define con -> y un enlace cerrado con - ambos terminan con un punto final (.)
6. Una pregunta usa => o <= y termina con un símbolo de interrogación final (?)
7. Después de un . o ? pueden venir mas caracteres que deberán ser ignorados.
8. Los nombres de las torres son únicos.

Todos los enlaces se adicionan a la red de comunicación solo una vez. La dirección de la comunicación se representa por medio de la flecha -> Las siguientes dos líneas representan el mismo enlace abierto o cerrado:
A123 -> B456. // enlace abierto desde torre A123 a torre B456
A123 - B456. // enlace cerrado desde torre A123 a torre B456
Una pregunta se efectúa para saber si es posible establecer una comunicación entre dos torres que no necesariamente están directamente enlazadas. La respuesta a la misma pregunta puede variar si entre ellas existen líneas con definiciones de nuevos enlaces entre torres. Las preguntas pueden ser de dos tipos:
A123 => Z987? // existe una ruta desde A123 hasta Z987?
A123 <= C324? // existe una ruta hasta A123 desde C324?

Y las respuestas a las preguntas iniciaran con (-) cuando no exista una ruta y (+) cuando exista una ruta, seguida por los nombres de las torres ordenadas de Torre inicial => Enlace1 => … => Enlacen => Torre final.

####Ejemplo de archivo de entrada:

Culiacan1 -> Hermosillo2.
Hermosillo2 -> Monterrey5.
Culiacan1 => Monterrey5? // esta es una pregunta
Guadalajara3 <- Hermosillo2.
Guadalajara3 -> Guadalajara34. // enlace
Guadalajara34 <= Culiacan1 ? #otra pregunta
Mexico5 <- culiacan1. // este es un enlace
Guadalajara34 => Hermosillo2? % tercera pregunta
Mexico5 => hermosillo2? &una torre desconocida.

####La salida para el archivo anterior sería:

\+ Culiacan1 => Hermosillo2 => Monterrey5

\+ Culiacan1 => Hermosillo2 => Guadalajara3 => Guadalajara34

\- Guadalajara34 => Hermosillo2

\- Mexico5 => hermosillo2

####Requisitos adicionales.

Tu programa debe contener comentarios que documenten la estructura usada así como también, los nombres de los autores y comentarios que documenten el código.
El programa debe llamarse Enlaces.java

####Nota: 
Verifica que tu salida sea EXACTAMENTE igual a como se pide y los datos del archivo de entrada cumplan con las reglas definidas.
<br>
