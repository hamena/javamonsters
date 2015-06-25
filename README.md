# javamonsters

JavaMonsters es una aplicación para simular batallas entre dos mounstros con las estadísticas personalizables.

Cada mounstro tiene una serie de atributos, entre los que se encuentra los puntos de vida, el ataque, la rapidez, etc... Todos estos atributos son personalizables de forma directa, escribiendo directamente el valor deseado en el campo, ó incrementadolas y decrementadolas a través de la interfaz proporcionada.

El comportamiento de un mounstro se verá determinado por el poder de sus atributos y un componente aleatorio que agregará una naturaleza estocástica a los combates, dando lugar a enfrentamientos que tendrán finales diferentes a pesar de no haber modificado los atributos de los involucrados de combate a combate.

- Compilar y ejecutar:

  $ javac javamonsters/GUI/GUI.java
  
  $ java javamonsters.GUI.GUI

- Empacar:

  Has de estar seguro de que la aplicación ha sido compilada previamente:

  $ javac javamonsters/GUI/GUI.java

  Para empacar ejecutar el siguiente script:

  $ ./package.sh

- Lanzar paquete:

  $ java -jar builds/javamonsters.java

  O simplemente doble click sobre el paquete con extensión .jar en el directorio builds.