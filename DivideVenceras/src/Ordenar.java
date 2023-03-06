import java.util.Vector;

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
			if (c1.getCapacidadLibre() < c2.getCapacidadLibre()) {
				/* sólo lo añadimos si ha sido capaz de recorrer todo */
				//if (c1.isHaRecorridoTodo())
					cochesOrdenados.add(c1);
				índice1++;
			} else {
				//if (c2.isHaRecorridoTodo())
					cochesOrdenados.add(c2);
				índice2++;
			}
		}
		
		/* sólo uno de estos se ejecutará, se encargan de terminar de añadir el resto de coches al vector */
		for (; índice1 < end1; índice1++)
			/* el coche debe cumplir que ha recorrido todo */
			//if (primerVector.get(índice1).isHaRecorridoTodo())
				/* en ese caso, lo añadimos */
				cochesOrdenados.add(primerVector.get(índice1)); 

		for (; índice2 < end2; índice2++)
			//if (segundoVector.get(índice2).isHaRecorridoTodo())
				cochesOrdenados.add(segundoVector.get(índice2)); 

		return cochesOrdenados;
	}
	
	/**
	 * <p>
	 * Ordena mediante el algoritmo MergeSort un sub-Vector de Coches del vector <code>coches</code> delimitado por los límites.
	 * La ordenación se hace atendiendo a la ordenación que indica <code>combinar</code>.
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
			/* debemos ordenar cada mitad */
			final int mitad = (limInferior + limSuperior) / 2;

			/* Devolvemos la combinación de */
			cochesOrdenados = combinar(
								/* la primera mitad ordenada */
								mergeSort(coches, limInferior, mitad),
								/* la segunda mitad ordenada */
								mergeSort(coches, mitad + 1, limSuperior)
								);
		}
		
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
	 * @param coches Vector de Coches del que se obtendrán los coches a ordenar. Este no se ordenará.
	 * @return Vector de Coches ordenados.
	 */
	public static Vector<Coche> ordenarPorConsumo(final Vector<Coche> coches) {
		return mergeSort(coches, 0, coches.size() - 1);
	}
}
