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
	 * <p>
	 * El coste de este método es:
	 * número de veces que el usuario introduzca mal el dato numérico + número indicado por el usuario + número de coches en el fichero. 
	 * </p>
	 * @param pathname Ruta y nombre al archivo de datos a leer.
	 * @param coches Vector <i>ya creado</i> donde se añadirán los coches leídos.
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
	 * Método encargado de hacer la simulación principal:
	 * Hacer que todos los coches intenten recorrer las distancias.
	 * Clasifica cada coche dependiendo de si ha terminado o no.
	 * Tras ello, ordena los que sí lo han conseguido y los devuelve.
	 * </p>
	 * <p>
	 * El coste de este método es:
	 * número de coches * número de distancias + número de coches que terminan * log(número de coches que terminan).
	 * Donde número de coches que terminan &lt;= número de coches.
	 * <p>
	 * @param coches Vector de Coches que contiene todos los coches sobre los que se realizará la simulación.
	 * @param distanciasPOIs array de doubles que representan las distancias en km entre un POI y el siguiente.
	 * @param cochesQueNoTerminan Vector de Coches <i>ya creado</i> donde se añadirán aquellos que no han terminado la ruta.
	 * @return Vector de Coches ordenados respecto al consumo de aquellos que han sido capaz de terminar la ruta.
	 */
	public static Vector<Coche> run(final Vector<Coche> coches, final double[] distanciasPOIs, final Vector<Coche> cochesQueNoTerminan) {
		final Vector<Coche> cochesQueTerminan = new Vector<>();

		/* Hacemos que los coches recorran la suma de todas las distancias */ 
		/* coste número de coches * número de distancias */
		for (Coche coche : coches) {
			/* coste n, siendo n el número de distancias (la longitud del array) */
			recorrer(distanciasPOIs, coche);
			
			/* si al coche le queda gasolina, ha conseguido terminar el recorrido */
			if (coche.getCapacidadActual() >= 0)
				cochesQueTerminan.add(coche);
			else
				cochesQueNoTerminan.add(coche);
		}

		/* ordenamos por consumo y devolvemos ese Vector */
		/* coste n log n, siendo n el número de coches que terminan (la longitud del Vector) */
		return Ordenar.ordenarPorConsumo(cochesQueTerminan);
	}

	/**
	 * <p>
	 * Método encargado de mostrar todos los resultados por la salida estandar.
	 * </p>
	 * <p>
	 * El coste de este método es:
	 * número de coches ordenados + numero de coches que no terminan
	 * Es decir, número de coches totales.
	 * </p>
	 * @param cochesOrdenados
	 * @param cochesQueNoTerminan
	 */
	public static void mostarResultados(final Vector<Coche> cochesOrdenados, final Vector<Coche> cochesQueNoTerminan) {
		System.out.printf("\n%-35s : Consumo\n", "Modelo");

		System.out.println("Los coches que sí han llegado al final son, ordenados por consumo:");
		for (Coche coche : cochesOrdenados)
			System.out.println(coche);

		System.out.println();

		System.out.println("Los coches que no han conseguido llegar al final son:");
		for (Coche coche : cochesQueNoTerminan)
			System.out.println(coche);
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
	 * <h3>Estudio de complejidad:</h3>
	 * <p>
	 * Los cálculos se harán bajo la suposición de que <em>todas</em> las operaciones, excepto las llamadas a otras funciones estudiadas, tienen coste 1.
	 * <ul>
	 * <li>a = 2</li>
	 * <li>b = 2</li>
	 * <li>k = 0</li>
	 * </ul>
	 * a &gt; b<sup>k</sup> --&gt; T(n) pertenece O(n<sup>log<sub>b</sub> a</sup>) = O(n)
	 * Donde n es el tamaño del array <code>distancias</code>.
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
	
	public static void main(String[] args) {
		final String nombreDelFichero = "cars_dataset.csv";
		try {
			/* necesitamos que los vectores de coches puedan crecer ya que no sabemos a priori cuántos hay */
			final Vector<Coche> coches = new Vector<>();
			final Vector<Coche> cochesQueNoTerminan = new Vector<>();

			/* obtenermos las distancias y los coches */
			final double[] distanciasPOIs = obtenerDatos(nombreDelFichero, coches);

			/* Hacemos la simulación */
			final Vector<Coche> cochesOrdenados = run(coches, distanciasPOIs, cochesQueNoTerminan);
			
			mostarResultados(cochesOrdenados, cochesQueNoTerminan);
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + nombreDelFichero + "\".");
		} catch (Exception e) {
			System.err.println("Error inesperado: " + e);
		}
	}

}
