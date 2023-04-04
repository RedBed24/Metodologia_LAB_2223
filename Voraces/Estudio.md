# Optimalidad de la solución

Para el apartado a) estamos sujetos a los problemas que trae el problema de la mochicha donde no se puede fraccionar.
Tendríamos que ver todas las combinaciones para poder saber si existe una solución mejor, pero puesto que tenemos en cuenta ambos parámetros, la producción y el espacio, la solución será bastante acercada a la óptima.

Por otra parte, el apartado b) es más complicado.
No podemos calcular un ratio de beneficio / coste ya que no tenemos beneficio.
Tanto la comida como el espacio son parámetros a minimizar, uno como objetivo y otro como restricción respectivamente.

Podemos tener en cuenta ambos parámetros calculando un precio, una vez lo tengamos, cogeremos las vacas con menor precio.
Para que no sea complicado, simplemente usaremos un fator que sea `RELACIÓN_COSTE_COMIDA_ESPACIO`, este indica cuánto vale la comida en relación a lo que vale el espacio.

- Aumentando este, hacemos que la comida se valore más que el espacio, es decir, se tenga más en cuenta.
- rango = [0, infinito)
- 0 hace que no se tenga en cuenta la comida.
- Un valor de varios órdenes de magnitud hace (prácticamente) que no se tenga en cuenta el espacio.

Ahora para darle un valor significativo a este parámetro, haremos un breve estudio de las vacas.

Los ratios de espacio frente a comida tienen una media de 8.7.
Esto significa que, aproximadamente, cada x comida, una vaca gasta x \* 8.7 espacio.
Este valor nos permite darle el mismo coste a la comida que al espacio, que ambos se tengan en cuenta de igual manera.

# Cálculo de complejidad

*Bajo la suposición de que todos los métodos y operaciones que no se razonen tienen un coste constante.*

Todos los métodos de la carpeta vaca tienen una complejidad constante O(1) ya que no cuentan con bucles.

La lectura del fichero de datos (fichero IO.java) tiene un bucle que se repite tantas veces como líneas tenga el fichero.
Complejidad O(n), donde n es el número de líneas del fichero.

El método obtener datos depende de la entrada de usuario y la lectura del fichero previamente dicha.
El usuario sólo debe introducir un dato.

Mostrar los resultados tiene un bucle que recorre todas las diferentes vacas seleccionadas.
La complejidad es O(n), donde n es el número de vacas seleccionadas.

La ejecución de la simulación principal, `run`, tiene dos bucles separados y una llamada a una función de ordenación:

- El primer bucle recorre todas las vacas, este se hace con el objetivo de pasarle al método de ordenación el tipo de datos que espera.
- El segundo bucle, en el peor de los casos (cuando hay comida de sobra) se recorre tantas veces como vacas haya.
- La llamada a `quickSortC`, este algoritmo tiene una complejidad de O(n \* log(n)) en el peor de los casos.

Por tanto la complejidad de este método sería:

O(n) + O(n \* log(n)) + O(n)

Que se reduciría a: O(n \* log(n))

El main recoge todos los métodos antes mencionados separados.
Si es necesario recalcar que hay un bucle for para cada comparator, que coincide con los diferentes apartados del punto 1.
En este caso son 2, si sólo consideramos las vacas, esto sería constante, por lo que la complejidad total, ya reducida, sería:

O(n \* log(n))

Donde n es el número de vacas (que coincide con las líneas del fichero).
Esta complejidad viene por el algoritmo de ordenación.

