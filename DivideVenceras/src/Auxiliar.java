
/**
 * <p>
 * Clase que se encarga de métodos generales de divide y vencerás.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 2
 * @since 2023-02-27
 */
public class Auxiliar {
	
	/**
	 * <p>
	 * Mínimo obtenido mediante divide y vencerás.
	 * </p>
	 * @param vector Vector del cuál obtendremos el mínimo
	 * @param límiteInferior Posición inferior del trozo que nos toca mirar
	 * @param límiteSuperior Posición superior del trozo que nos toca mirar
	 * @return Mínimo local del trozo que nos toca mirar
	 */
	private static int mínimoRec(final int[] vector, int límiteInferior, int límiteSuperior) {
		int medio, mínimo, mínimoDerecha;

		/* si sólo hay 1 elemento entre el límite inferior y el superior */
		if (límiteInferior == límiteSuperior) {
			mínimo = vector[límiteInferior];
		} else  {
			/* debemos partir por la mitad el trozo que estábamos buscando */
			medio = (límiteInferior + límiteSuperior) / 2;
			
			/* obtenemos el mínimo de cada lado */
			mínimo = mínimoRec(vector, límiteInferior, medio);
			mínimoDerecha = mínimoRec(vector, medio + 1, límiteSuperior);
			
			/* comprobamos cuál es el mínimo de esos dos */
			if (mínimoDerecha < mínimo) mínimo = mínimoDerecha;
		}

		/* este será el mínimo del trozo que estábamos viendo */
		return mínimo;
	}
	
	
	/**
	 * <p>
	 * Mínimo obtenido mediante divide y vencerás.
	 * Esta se encarga de abstraer de hacer la primera llamada al algoritmo.
	 * </p>
	 * @param vector Vector del cuál obtendremos el mínimo
	 * @return Mínimo del Vector
	 */
	public static int mínimo(final int[] vector) {
		/* El trozo por el que debemos a empezar a mirar es [0, longitud) */
		return mínimoRec(vector, 0, vector.length - 1);
	}
	
	/**
	 * <p>
	 * Se encarga de sumar mediante Divide y Vencerás el array de doubles.
	 * </p>
	 * @param A array de doubles a sumar.
	 * @param limInferior Índice inferior del que empezaremos a sumar.
	 * @param limSuperior Índice superior que marcará el último a sumar.
	 * @return La suma de sub-array delimitado por [limInferior, limSuperior]
	 */
	private static double sum(final double[] A, int limInferior, int limSuperior) {
		/* suponemos que no lo hemos encontrado */
		double sum = 0;
		
		/* cuando sólo nos quede un elemento */
		if (limInferior == limSuperior) {
			sum = A[limInferior];
		} else {
			/* Dividimos por la mitad, b = 2 */
			/* coste de división 1, k = 0 */
			final int mid = (limInferior + limSuperior) / 2;
			
			/* hacemos dos llamadas, a = 2 */
			double sumizq = sum(A, limInferior, mid);
			double sumdrc = sum(A, mid + 1, limSuperior);
			
			sum = sumizq + sumdrc;
		} 
            
		return sum;
	} 
	
	/**
	 * <p>
	 * Primera llamada al algoritmo divide y vencerás para sumar todas las posiciones del array.
	 * </p>
	 * @param A array de doubles a sumar.
	 * @return suma de todo el vector.
	 */
	public static double sum(final double[] A) {
		return sum(A, 0, A.length - 1);
	}
}
