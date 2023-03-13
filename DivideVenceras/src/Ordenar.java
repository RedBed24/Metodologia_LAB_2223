import java.util.Vector;

import Coche.Coche;

/**
 * <p>
 * Clase que se encarga de contener los algoritmos para ordenar los coches.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 1
 * @since 2023-02-27
 */
public class Ordenar {

	/**
	 * <p>
	 * Combina ordenadamente dos Vectores de Coches <em>previamente ordenados</em>.
	 * </p>
	 * <h3>Estudio de complejidad:<h3>
	 * <p>
	 * Los cálculos se harán bajo la suposición de que <em>todas</em> las operaciones, excepto las llamadas a otras funciones estudiadas, tienen coste 1.
	 * El coste de este es n + m, siendo n la longitud del primer vector y m la del segundo.
	 * </p>
	 * @param primerVector Vector de Coches <em>ordenado</em> que se unirá junto al otro.
	 * @param segundoVector Vector de Coches <em>ordenado</em> que se unirá junto al otro.
	 * @return Vector de Coches ordenado, resultado de la unión de <code>primerVector</code> y <code>segundoVector</code>.
	 */
	private static Vector<Coche> combinar(final Vector<Coche> primerVector, final Vector<Coche> segundoVector) {
		/* nos guardamos los tamaños de los vectores, para que no tengamos que hacer muchas llamadas */
		final int end1 = primerVector.size(), end2 = segundoVector.size();
		
		/* creamos vector con tamaño igual a la suma de los tamaños de los dos vectores a unir */
		final Vector<Coche> cochesOrdenados = new Vector<>(end1 + end2);
		
		int índice1 = 0, índice2 = 0;
		
		while (índice1 < end1 && índice2 < end2) {
			Coche c1 = primerVector.get(índice1);
			Coche c2 = segundoVector.get(índice2);

			/* añadimos el coche con menor capacidad al vector ordenado
			 * y avanzamos en ese vector
			 */
			if (c1.compareTo(c2) < 0) {
				cochesOrdenados.add(c1);
				índice1++;
			} else {
				cochesOrdenados.add(c2);
				índice2++;
			}
		}
		
		/* sólo uno de estos se ejecutará, se encargan de terminar de añadir el resto de coches al vector */
		for (; índice1 < end1; índice1++)
			cochesOrdenados.add(primerVector.get(índice1)); 

		for (; índice2 < end2; índice2++)
			cochesOrdenados.add(segundoVector.get(índice2)); 

		return cochesOrdenados;
	}
	
	/**
	 * <p>
	 * Ordena mediante el algoritmo MergeSort un sub-Vector de Coches del vector <code>coches</code> delimitado por los límites.
	 * La ordenación se hace atendiendo a la prioridad de coches que indica <code>combinar</code>.
	 * </p>
	 * <h3>Estudio de complejidad:</h3>
	 * <p>
	 * Los cálculos se harán bajo la suposición de que <em>todas</em> las operaciones, excepto las llamadas a otras funciones estudiadas, tienen coste 1.
	 * <ul>
	 * <li>a = 2</li>
	 * <li>b = 2</li>
	 * <li>k = 1</li>
	 * </ul>
	 * a = b<sup>k</sup> --&gt; T(n) pertenece O(n<sup>k</sup>log n) = O(nlog n)
	 * </p>
	 * @param coches Vector de Coches del que se obtendrán los coches a ordenar. Este no se ordenará.
	 * @param limInferior Límite inferior que nos indica qué sub-Vector de <code>coches</code> debemos ordenar.
	 * @param limSuperior Límite superior que nos indica qué sub-Vector de <code>coches</code> debemos ordenar.
	 * @return sub Vector de Coches ya ordenados.
	 */
	private static Vector<Coche> mergeSort(final Vector<Coche> coches, final int limInferior, final int limSuperior) {
		Vector<Coche> cochesOrdenados;
		
		/* si sólo queda un elemento, ya está ordenado */
		if (limInferior == limSuperior) {
			cochesOrdenados = new Vector<>(1);
			cochesOrdenados.add(coches.get(limInferior));
		} else {
			/* debemos ordenar cada mitad
			 * División del problema: b = 2
			 * Coste de división: 1 */
			final int mitad = (limInferior + limSuperior) / 2;

			/* Coste de combinación: n + m, donde m = n, por lo que el coste es 2n, y la complejidad es O(n) */

			/* Devolvemos la combinación de */
			cochesOrdenados = combinar(
								/* Hacemos 2 llamadas recursivas, las cuales se ejecutan ambas: a = 2 */
								/* la primera mitad ordenada */
								mergeSort(coches, limInferior, mitad),
								/* la segunda mitad ordenada */
								mergeSort(coches, mitad + 1, limSuperior)
								);
		}
		
		/* g(n) pertenece a la complejidad O(n) -> k = 1 */
		return cochesOrdenados;
	}
	
	/**
	 * <p>
	 * Devuelve un vector de Coches ordenados atendiendo al consumo que han tenido.
	 * </p>
	 * <p>
	 * La ordenación se hace mediante Merge Sort.
	 * Este método se encarga de hacer la primera llamada al algoritmo.
	 * </p>
	 * <p>
	 * La complejidad de este método es la misma que <code>mergeSort</code>.
	 * </p>
	 * @param coches Vector de Coches del que se obtendrán los coches a ordenar. Este no se ordenará.
	 * @return Vector de Coches ordenados.
	 */
	public static Vector<Coche> ordenarPorConsumo(final Vector<Coche> coches) {
		return mergeSort(coches, 0, coches.size() - 1);
	}
}
