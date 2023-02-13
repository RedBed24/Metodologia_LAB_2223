
public class Burbuja {

	public static void main(String[] args) {
		int[] vector = { 1, 3, 2};
		burbuja(vector);
		imprimir(vector);
	}
	
	public static void burbuja(int[] t) {
		int seguir = 1;
		int p = 0;
		while (seguir != t.length) {
			while (t[p] > t[p + 1]) {
				int aux = t[p];
				t[p] = t[p + 1];
				t[p + 1] = aux;
			}
			p++;
			seguir++;
		}
	}
	
	public static void imprimir(int[] t) {
		for (int k = 0; k < t.length; k++) 
			System.out.print(t[k] + " ");
	}

}
