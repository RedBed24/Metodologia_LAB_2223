import java.io.FileNotFoundException;
import java.util.Vector;

import campusvirtual.leer;

public class Main {

	/**
	 * <p>
	 * Método encargado de obtener los datos para la ejecución del programa.
	 * </p>
	 * @param pathname Ruta y nombre al archivo de datos a leer.
	 * @param coches Vector <i>ya creada</i> donde se añadirán los coches leídos.
	 * @return Vector con las distancias entre los POIs.
	 * @throws FileNotFoundException Cuando el fichero de datos a leer no se encuentra.
	 */
	public static double[] obtenerDatos(final String pathname, final Vector<Coche> coches) throws FileNotFoundException {
		/* leemos un entero el cual será el número de puntos de interés (numPOIs) */
		int leído;
		do {
			leído = leer.entero("¿Cuántos puntos de interés hay?");
			/* este entero debe ser positivo */
			if (leído <=0 ) System.err.println("Debe haber un número positivo de puntos de interés.");
		} while (leído <= 0);
		
		/* una vez vemos que cumple las condiciones, nos lo quedamos como el número de POIs */
		final int numPOIs = leído;
		
		/* Generamos las distancias entre los POIs, entre numPOIs hay una distancia menos, entre 2 puntos hay 1 recta */
		final double[] distanciasPOIs = new double[numPOIs - 1];
		for (int i = 0; i < distanciasPOIs.length; i++)
			/* Seleccionamos una distanca en km aleatoria entre [20, 60) entre dos POIs (i - 1, i) */
			distanciasPOIs[i] = 20 + Math.random() * 40;
		
		/* leemos los coches */
		IO.leerCoches(pathname, coches);
		
		return distanciasPOIs;
	}
	
	/**
	 * <p>
	 * Simula la ejecucuón del programa
	 * </p>
	 * @param coches Vector con los coches sobre los que se realizará la ejecución.
	 * @param distanciasPOIs Vector con las distancias en kilómetros entre los POIs
	 */
	public static void run(final Vector<Coche> coches, final double[] distanciasPOIs) {
		/* TODO:
		 * [ ] Hacer esto mediante divide y vencerás
		 */

		/* para cada coche */
		for (final Coche coche : coches) {
			/* para cada distancia */
			for (final double distancia : distanciasPOIs)
				/* Hacemos que el coche recorra la distancia */
				/* si el coche ya no puede recorrer más, pasamos al siguiente */
				if (distancia != coche.recorrer(distancia)) {
					/* marcamos que no ha sido capaz de recorrer todo */
					coche.setHaRecorridoTodo(false);
					break;
				}
		}
		
		final Vector<Coche> cochesOrdenados = Ordenar.ordenarPorConsumo(coches);
		
		for (final Coche coche : cochesOrdenados) {
			System.out.printf("\nEl coche %s ha consumido %.3fL.", coche, coche.getCapacidadLibre());
			if (!coche.isHaRecorridoTodo())
				System.out.print(" No ha sido capaz de llegar al final.");
		}
	}

	public static void main(String[] args) {
		try {
			final Vector<Coche> coches = new Vector<>();
			
			final double[] distanciasPOIs = obtenerDatos("cars_dataset.csv", coches);
			
			run(coches, distanciasPOIs);
			
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"cars_dataset.csv\".");
		} catch (Exception e) {
			System.err.println("Error inesperado: " + e);
		}
	}

}
