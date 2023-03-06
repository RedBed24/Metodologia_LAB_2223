import java.io.FileNotFoundException;
import java.util.Vector;

import campusvirtual.leer;

/**
 * <p>
 * Clase que se encarga de la ejecución principal del programa.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 1
 * @since 2023-02-27
 */
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
			if (leído <= 1) System.err.println("Debe haber al menos 2 puntos de interés.");
		} while (leído <= 1);
		
		/* una vez vemos que cumple las condiciones, nos lo quedamos como el número de POIs */
		final int numPOIs = leído;
		
		/* Generamos las distancias entre los POIs, entre numPOIs hay una distancia menos, entre 2 puntos hay 1 recta */
		final double[] distanciasPOIs = new double[numPOIs - 1];
		for (int i = 0; i < distanciasPOIs.length; i++)
			/* Seleccionamos una distancia en km aleatoria entre [20, 60) entre dos POIs (i - 1, i) */
			distanciasPOIs[i] = 20 + Math.random() * 40;
		
		/* leemos los coches */
		IO.leerCoches(pathname, coches);
		
		return distanciasPOIs;
	}

	/**
	 * <p>
	 * Se encarga de realizar la primera llamada al algoritmo de Divide y Vencerás para recorrer todo el vector de coches.
	 * Con cada coche hacemos que recorra las distancias y lo clasificamos en un vector dependiendo de si ha sido capaz de terminar el trayecto o no.
	 * </p>
	 * @param coches Vector de Coches a recorrer.
	 * @param distancias array de doubles que representan las distancias en kilómetros que debe recorrer cada coche.
	 * @param cochesQueTerminan Vector de Coches donde añadiremos aquellos que sí son capaces de terminar el trayecto.
	 * @param cochesQueNoTerminan Vector de Coches donde añadiremos aquellos que no son capaces de terminar el trayecto.
	 */
	public static void recorrer(final Vector<Coche> coches, final double[] distancias, final Vector<Coche> cochesQueTerminan, final Vector<Coche> cochesQueNoTerminan) {
		recorrer(coches, 0, coches.size() - 1, distancias, cochesQueTerminan, cochesQueNoTerminan);
	}

	/**
	 * <p>
	 * Se encarga de recorrer, mediante Divide y Vencerás, un sub-Vector de Coches delimitado por [limInferior, limSuperior].
	 * Con cada coche hacemos que recorra las distancias y lo clasificamos en un vector dependiendo de si ha sido capaz de terminar el trayecto o no.
	 * </p>
	 * @param coches Vector de Coches a recorrer.
	 * @param limInferior
	 * @param limSuperior
	 * @param distancias array de doubles que representan las distancias en kilómetros que debe recorrer cada coche.
	 * @param cochesQueTerminan Vector de Coches donde añadiremos aquellos que sí son capaces de terminar el trayecto.
	 * @param cochesQueNoTerminan Vector de Coches donde añadiremos aquellos que no son capaces de terminar el trayecto.
	 */
	private static void recorrer(final Vector<Coche> coches, int limInferior, int limSuperior, final double[] distancias, final Vector<Coche> cochesQueTerminan, final Vector<Coche> cochesQueNoTerminan) {
		/* cuando sólo nos quede un elemento */
		if (limInferior == limSuperior) {
			final Coche coche = coches.get(limInferior);
			/* hacemos que el coche intente recorrer las distancias */
			recorrer(distancias, coche);
			
			/* dependiendo de si ha sido capaz de terminar o no, lo añadimos a un vector u otro */
			if (coche.getCapacidadActual() >= 0) cochesQueTerminan.add(coche);
			else cochesQueNoTerminan.add(coche);
		} else {
			/* Dividimos por la mitad, b = 2 */
			/* coste de división 1, k = 0 */
			final int mid = (limInferior + limSuperior) / 2;
			
			/* hacemos dos llamadas, a = 2 */
			recorrer(coches, limInferior, mid, distancias, cochesQueTerminan, cochesQueNoTerminan);
			recorrer(coches, mid + 1, limSuperior, distancias, cochesQueTerminan, cochesQueNoTerminan);
		} 
	} 

	/**
	 * <p>
	 * Primera llamada al algoritmo divide y vencerás para recorrer las posiciones del array hasta que pueda el coche.
	 * </p>
	 * @param distancias array de doubles que representan distancias en kilómetros a recorrer.
	 * @param coche Coche que debe recorrera las distancias.
	 */
	public static void recorrer(final double[] distancias, final Coche coche) {
		recorrer(distancias, 0, distancias.length - 1, coche);
	}

	/**
	 * <p>
	 * Se encarga de hacer que el coche recorra cada distancia del array de doubles mediante Divide y Vencerás.
	 * </p>
	 * @param distancias array de doubles que representan distancias en km a recorrer.
	 * @param limInferior Índice inferior del que empezaremos a recorrer.
	 * @param limSuperior Índice superior que marcará el último a recorrer.
	 * @param coche Coche que debe recorrera las distancias.
	 */
	private static void recorrer(final double[] distancias, int limInferior, int limSuperior, final Coche coche) {
		/* cuando sólo nos quede un elemento */
		if (limInferior == limSuperior) {
			/* intentamos recorrer esa distancia */
			coche.recorrer(distancias[limInferior]);
		} else {
			/* Dividimos por la mitad, b = 2 */
			/* coste de división 1, k = 0 */
			final int mid = (limInferior + limSuperior) / 2;
			
			/* hacemos dos llamadas, a = 2 */
			recorrer(distancias, limInferior, mid, coche);
			
			/* si ha sido capaz de recorrer hasta aqui, intentamos seguir */
			if (coche.getCapacidadActual() > 0)
				recorrer(distancias, mid + 1, limSuperior, coche);
		} 
	} 
	
	/**
	 * <p>
	 * Muestra todos los coches del Vector, indicando cuánto han gastado y si han sido capaz de recorrer todo el trayecto.
	 * </p>
	 * <p>
	 * El recorrido del vector se hace mediante un algoritmo Divide y Vencerás.
	 * Esta función se encarga de hacer la primera llamada al algoritmo.
	 * </p>
	 * @param coches Vector de Coches que deberán recorrer la distancia dada.
	 * @return String con toda la información recogida
	 */
	public static String mostrar(final Vector<Coche> coches) {
		return mostrar(coches, 0, coches.size() - 1, "");
	}
	
	/**
	 * <p>
	 * Se encarga de recorrer mediante Divide y Vencerás el vector de coches y mostrarlos.
	 * </p>
	 * @param vectorAMostrar Vector de Coches a mostrar.
	 * @param limInferior Índice inferior del que empezaremos a mostrar.
	 * @param limSuperior Índice superior que marcará el último a mostrar.
	 * @return String con toda la información recogida
	 */
	private static String mostrar(final Vector<Coche> vectorAMostrar, final int limInferior, final int limSuperior, String devolver) {
		/* cuando sólo nos quede un elemento */
		if (limInferior == limSuperior) {
			devolver += "\n" + vectorAMostrar.get(limInferior);
		} else {
			/* Dividimos por la mitad, b = 2 */
			/* coste de división 1, k = 0 */
			final int mid = (limInferior + limSuperior) / 2;
			
			/* hacemos dos llamadas, a = 2 */
			devolver = mostrar(vectorAMostrar, limInferior, mid, devolver);
			devolver = mostrar(vectorAMostrar, mid + 1, limSuperior, devolver);
		} 
		
		return devolver;
	} 

	public static void main(String[] args) {
		final String nombreDelFichero = "cars_dataset.csv";
		try {
			/* necesitamos que los vectores de coches puedan crecer ya que no sabemos a priori cuántos hay */
			final Vector<Coche> coches = new Vector<>();
			final Vector<Coche> cochesQueTerminan = new Vector<>();
			final Vector<Coche> cochesQueNoTerminan = new Vector<>();

			/* el número de distancias está definido una vez el usuario indica un número */
			final double[] distanciasPOIs = obtenerDatos(nombreDelFichero, coches);

			/* Hacemos que los coches recorran la suma de todas las distancias */ 
			recorrer(coches, distanciasPOIs, cochesQueTerminan, cochesQueNoTerminan);

			System.out.printf("\n%-35s : Consumo\n", "Modelo");

			if (cochesQueTerminan.size() > 0) {
				System.out.println("Los coches que sí han llegado al final son, ordenados por consumo:");
				System.out.println(mostrar(Ordenar.ordenarPorConsumo(cochesQueTerminan)));
			} else
				System.out.println("Ningún coche ha llegado al final.");

			System.out.println();

			if (cochesQueNoTerminan.size() > 0) {
				System.out.println("Los coches que no han conseguido llegar al final son:");
				System.out.println(mostrar(cochesQueNoTerminan));
			} else
				System.out.println("Todos los coches han llegado al final.");
			
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + nombreDelFichero + "\".");
		} catch (Exception e) {
			System.err.println("Error inesperado: " + e);
		}
	}

}
