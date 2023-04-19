package lecturadatos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Usuario {

	public static final Scanner TECLADO = new Scanner(System.in);

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
