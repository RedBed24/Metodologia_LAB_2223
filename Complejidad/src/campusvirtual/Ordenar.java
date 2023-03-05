package campusvirtual;

public class Ordenar {

	/**********************************************************************
	 * Method name: burbuja
	 *
	 * Description of the Method: bubble sort algorithm that sorts a one-dimensional array of integers in ascending order.
	 * 
	 *********************************************************************/
	public static void burbuja(int[] t) {
		for (int k = 0; k < t.length - 1; k++)
			for (int p = 0; p < t.length - 1 - k; p++)
				if (t[p] > t[p + 1]) {
					int aux = t[p];
					t[p] = t[p + 1];
					t[p + 1] = aux;
				}
	}


	/**********************************************************************
	 * Method name: seleccionDirecta
	 *
	 * Description of the Method: Direct Selection sort algorithm that sorts a one-dimensional array of integers in ascending order.
	 * 
	 *********************************************************************/
	public static void seleccionDirecta(int[] t) {
		for (int k = 0; k < t.length - 1; k++) {
			int men = k;
			for (int p = k + 1; p < t.length; p++) {
				if (t[p] < t[men])
					men = p;
			}
			int aux = t[k];
			t[k] = t[men];
			t[men] = aux;
		}
	}


	/**********************************************************************
	 * Method name: insercionDirecta
	 *
	 * Description of the Method: Direct Insertion sort algorithm that sorts a one-dimensional array of integers in ascending order.
	 * 
	 *********************************************************************/
	public static void insercionDirecta(int[] t) {
		for (int i = 1; i < t.length; i++) {
			int x = t[i];
			int j = i - 1;
			while (j >= 0 && x < t[j]) {
				t[j + 1] = t[j];
				j = j - 1;
			}
			t[j + 1] = x;
		}
	}


	/**********************************************************************
	 * Method name: quicksortA
	 *
	 * Description of the Method: Quicksort algorithm that recursively sorts a one-dimensional array of integers in ascending order.
	 * 
	 *********************************************************************/
	public static void quicksortA(int[] v) {
		quicksortA(v, 0, v.length - 1);
	}


	/**********************************************************************
	 * Method name: quickSortA
	 *
	 * Description of the Method: Quicksort algorithm that sorts a one-dimensional array of integers in ascending order establishing the starting index and the ending index
	 * 
	 *********************************************************************/
	private static void quicksortA(int[] v, int li, int ls) {
		if (li < ls) {
			int pos = divideA(v, li, ls);
			quicksortA(v, li, pos - 1);
			quicksortA(v, pos + 1, ls);
		}
	}


	/**********************************************************************
	 * Method name: divideA
	 *
	 * Description of the Method: rearranges the sub-arrays in a place.
	 * 
	 *********************************************************************/
	private static int divideA(int[] v, int li, int ls) {
		int pivote = v[li];
		int izq = li + 1;
		int der = ls;
		while (izq < der && v[izq] <= pivote)
			izq++;
		while (v[der] > pivote)
			der--;
		while (izq < der) {
			intercambia(v, izq, der);
			do {
				izq++;
			} while (v[izq] <= pivote);
			do {
				der--;
			} while (v[der] > pivote);
		}
		intercambia(v, li, der);
		return der;
	}


	/**********************************************************************
	 * Method name: intercambia
	 *
	 * Description of the Method: it swaps two elements of the array
	 * 
	 *********************************************************************/
	private static void intercambia(int[] v, int pos1, int pos2) {
		int aux = v[pos1];
		v[pos1] = v[pos2];
		v[pos2] = aux;
	}

}