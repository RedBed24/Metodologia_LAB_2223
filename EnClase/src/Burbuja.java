
public class Burbuja {

	public static void main(String[] args) {
		int[] vector = { 1, 3, 2};
		burbuja(vector);
		imprimir(vector);
	}
	
	public static void burbuja(int[] t) {
		/* Sirve para saber si ha sido necesario reordenar durante una iteración en específico */
		boolean reordenadoEnIteración = true;
		int aux, i;

		/* mientras hayamos tenido que reordenar durante la iteración pasada */
		while (reordenadoEnIteración) {
			/* Suponemos que no vamos a tener que reordenar más, y por tanto, podremos salir */
			reordenadoEnIteración = false;

			/* recoremos el vector, empezando en 1 hasta t.length ya que iremos viendo una posición y la anterior
			 * Hacemos esto ya que, de esta manera, nos ahorramos una resta (t.length - 1) en cada iteración */
			for (i = 1; i < t.length; i++) {
				/* Ajustamos los índices */
				if (t[i - 1] > t[i]) {
					/* intercambiamos las posciones */
					aux = t[i - 1];
					t[i - 1] = t[i];
					t[i] = aux;
					/* Marcamos que hemos tenido que hacer una iteración
					 * Por tanto, en la siguiente iteración revisaremos si ya está ordenado */
					reordenadoEnIteración = true;
				}
			}
		}
	}
	
	public static void imprimir(int[] t) {
		for (int k = 0; k < t.length; k++) 
			System.out.print(t[k] + " ");
	}

}
