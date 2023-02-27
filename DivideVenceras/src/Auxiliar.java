
public class Auxiliar {
	
	public static int mínimo(final int[] vector, int límiteInferior, int límiteSuperior) {
		int mid, min = 0, minIzquierda, minDerecha;

		if (límiteInferior == límiteSuperior) {
			min = vector[límiteInferior];
		} else if (límiteInferior < límiteSuperior) {
			mid = (límiteInferior + límiteSuperior)/2;
			min = mínimo(vector, límiteInferior, mid);
			minDerecha = mínimo(vector, mid + 1, límiteSuperior);
			
			if (minDerecha < min) min = minDerecha;
		}

		return min;
	}

}
