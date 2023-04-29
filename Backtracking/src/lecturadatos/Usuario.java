package lecturadatos;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * Clase para interactuar con el usuario y obtener los datos requeridos por el problema.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-04-29
 */
public class Usuario {

	// Constante para obtener los datos del usuario
	public static final Scanner TECLADO = new Scanner(System.in);

	/**
	 * <p>
	 * Pide al usuario la leche deseada en L.
	 * </p>
	 * @return Entero positivo que indica la leche deseada en L
	 */
	public static double obtenerLecheDeseada() {
		double lecheDeseada = -1;
		do {
			try {
				System.out.println("¿Cuánta leche (en litros) se desea obtener?");
				lecheDeseada = TECLADO.nextDouble();
				if (lecheDeseada < 0) {
					System.err.println("Se necesita una cantidad de leche positiva.");
				}
			} catch (InputMismatchException e) {
				TECLADO.nextLine();
				System.err.println("Se espera un dato númerico.");
			}
		} while (lecheDeseada < 0);
		
		return lecheDeseada;
	}

}
