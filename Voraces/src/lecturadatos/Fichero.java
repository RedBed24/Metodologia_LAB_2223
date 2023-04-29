package lecturadatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

import vaca.Vaca;

/**
 * <p>
 * Clase que se encarga de la salida y entrada en ficheros.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 1
 * @since 2023-03-20
 */
public class Fichero {
	
	/**
	 * <p>
	 * Método encargado de leer el fichero que contiene los datos.
	 * </p>
	 * @param pahtname El camino y nombre al fichero a leer
	 * @return array de Vacas leídas
	 * @throws FileNotFoundException En caso de no encontrar el fichero con el pathname dado
	 */
	public static Vaca[] leerVacas(final String pahtname) throws FileNotFoundException {
		final Queue<Vaca> colaVacas = new LinkedList<Vaca>();
		/* Declaración del scanner a usar para leer las líneas */
		final Scanner input = new Scanner(new File(pahtname));

		StringTokenizer st;
		
		while (input.hasNextLine()) {
			try {
				/* Vamos obteniendo los tokens los cuales están separados por ";" */
				st = new StringTokenizer(input.nextLine(), ";");
				
				/* Token código */
				final int código = Integer.parseInt(st.nextToken());
				
				final int espacio = Integer.parseInt(st.nextToken());

				/* Pasamos a entero porque en el fichero se encuentra como doble, pero no tiene sentido que este no sea entero */
				final int comida = Integer.parseInt(st.nextToken());

				final double leche = Double.parseDouble(st.nextToken());
				
				colaVacas.add(new Vaca(código, espacio, comida, leche));

			} catch (NumberFormatException e) {
				System.err.println("Error trying to read number: " + e.getMessage());
			} catch (NoSuchElementException e) {
				System.err.println("Error trying to read next token.");
			}
		}
		
		input.close();
		
		return colaVacas.toArray(new Vaca[colaVacas.size()]);
	}
}
