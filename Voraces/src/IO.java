import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import Vaca.Vaca;

/**
 * <p>
 * Clase que se encarga de la salida y entrada en ficheros.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 0
 * @since 2023-03-20
 */
public class IO {
	
	/**
	 * <p>
	 * Método encargado de leer el fichero que contiene los datos.
	 * </p>
	 * <h3>Estudio de complejidad:<h3>
	 * <p>
	 * Los cálculos se harán bajo la suposición de que <em>todas</em> las operaciones, excepto las llamadas a otras funciones estudiadas, tienen coste 1.
	 * La complejidad es O(n), siendo n el número de líneas que hay en el fichero.
	 * </p>
	 * @param pahtname El camino y nombre al fichero a leer
	 * @param vacas Lista <b>ya creada</b> que contendrá las Vacas que se leerán del fichero
	 * @throws FileNotFoundException En caso de no encontrar el fichero con el pathname dado
	 */
	public static void leerVacas(final String pahtname, final Vector <Vaca> vacas) throws FileNotFoundException {
		/* Declaración del scanner a usar para leer las líneas */
		final Scanner input = new Scanner(new File(pahtname));

		StringTokenizer st;
		
		while (input.hasNextLine()) {
			try {
				/* Vamos obteniendo los tokens los cuales están separados por "," */
				st = new StringTokenizer(input.nextLine(), ",");;
				
				/* Token modelo */
				final int código = Integer.parseInt(st.nextToken());
				
				final int espacio = Integer.parseInt(st.nextToken());
				
				/* Pasamos a entero porque en el fichero se encuentra como doble, pero no tiene sentido que este no sea entero */
				final int comida = (int) Double.parseDouble(st.nextToken());

				final int agua = (int) Double.parseDouble(st.nextToken());
				
				final double leche = Double.parseDouble(st.nextToken());
				
				vacas.add(new Vaca(código, espacio, comida, agua, leche));

			} catch (NumberFormatException e) {
				System.err.println("Error trying to read number: " + e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println("Error trying to read enum: " + e.getMessage());
			} catch (NoSuchElementException e) {
				System.err.println("Error trying to read next token.");
			}
		}
		
		input.close();
	}
}
