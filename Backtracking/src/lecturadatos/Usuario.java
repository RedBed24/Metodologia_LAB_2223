package lecturadatos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Usuario {

	public static double obtenerLecheDeseada() {
		final Scanner teclado = new Scanner(System.in);
		
		double lecheDeseada = -1;
		do {
			try {
				System.out.println("¿Cuánta leche (en litros) se desea obtener?");
				lecheDeseada = teclado.nextDouble();
				if (lecheDeseada <= 0) {
					System.err.println("Se necesita una cantidad de leche positiva.");
				}
			} catch (InputMismatchException e) {
				teclado.nextLine();
				System.err.println("Se espera un dato númerico.");
			}
		} while (lecheDeseada <= 0);
		
		teclado.close();
		
		return lecheDeseada;
	}

}
