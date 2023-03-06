import java.util.Vector;

/**
 * <p>
 * Se encarga de resolver los problemas del ejercicio mediante algoritmos de divide y vencerás.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 0.01
 * @since 2023-02-27
 */
public class AlgoritmosDelProblema {

	/**
	 * <p>
	 * Hace que todos los coches del vector recorran la distancia dada.
	 * Si un coche no es capaz de recorrer la distancia, se marca.
	 * </p>
	 * <p>
	 * El recorrido del vector se hace mediante un algoritmo Divide y Vencerás.
	 * Esta función se encarga de hacer la primera llamada al algoritmo.
	 * </p>
	 * @param coches Vector de Coches que deberán recorrer la distancia dada.
	 * @param distancia Medida en km que deberán recorrer los coches.
	 */
	public static void recorrer(final Vector<Coche> coches, final double distancia) {
		recorrer(coches, 0, coches.size() - 1, distancia);
	}
	
	/**
	 * <p>
	 * Se encarga de recorrer mediante Divide y Vencerás el vector de coches y hacer que cada uno, recorra la distancia.
	 * </p>
	 * @param A Vector de Coches a mostrar.
	 * @param limInferior Índice inferior del que empezaremos a mostrar.
	 * @param limSuperior Índice superior que marcará el último a mostrar.
	 * @param distancia Medida en km que deberán recorrer los coches.
	 */
	private static void recorrer(final Vector<Coche> A, final int limInferior, final int limSuperior, final double distancia) {
		/* cuando sólo nos quede un elemento */
		if (limInferior == limSuperior) {
			if (distancia != A.get(limInferior).recorrer(distancia))
				A.get(limInferior).setHaRecorridoTodo(false);
		} else {
			/* Dividimos por la mitad, b = 2 */
			/* coste de división 1, k = 0 */
			final int mid = (limInferior + limSuperior) / 2;
			
			/* hacemos dos llamadas, a = 2 */
			recorrer(A, limInferior, mid, distancia);
			recorrer(A, mid + 1, limSuperior, distancia);
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
	 */
	public static void mostrar(final Vector<Coche> coches) {
		mostrar(coches, 0, coches.size() - 1);
	}
	
	/**
	 * <p>
	 * Se encarga de recorrer mediante Divide y Vencerás el vector de coches y mostrarlos.
	 * </p>
	 * @param A Vector de Coches a mostrar.
	 * @param limInferior Índice inferior del que empezaremos a mostrar.
	 * @param limSuperior Índice superior que marcará el último a mostrar.
	 */
	private static void mostrar(final Vector<Coche> A, final int limInferior, final int limSuperior) {
		/* cuando sólo nos quede un elemento */
		if (limInferior == limSuperior) {
			final Coche coche = A.get(limInferior);
			System.out.printf("\nEl coche %s ha consumido %.3fL.", coche, coche.getCapacidadLibre());
			if (!coche.isHaRecorridoTodo())
				System.out.print(" No ha sido capaz de llegar al final.");
		} else {
			/* Dividimos por la mitad, b = 2 */
			/* coste de división 1, k = 0 */
			final int mid = (limInferior + limSuperior) / 2;
			
			/* hacemos dos llamadas, a = 2 */
			mostrar(A, limInferior, mid);
			mostrar(A, mid + 1, limSuperior);
		} 
	} 

}
