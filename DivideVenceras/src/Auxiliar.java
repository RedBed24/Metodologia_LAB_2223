
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
	
	public static double sum(final double[] A) {
		return sum(A, 0, A.length - 1);
	}
}
