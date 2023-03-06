import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import excepciones.CarCreationException;

/**
 * <p>
 * Clase que se encarga de la salida y entrada en ficheros.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @version 2
 * @since 2023-02-27
 */
public class IO {
	
	/**
	 * <p>
	 * Método encargado de leer el fichero que contiene los datos.
	 * </p>
	 * @param pahtname El camino y nombre al fichero a leer
	 * @param coches Lista <b>ya creada</b> que contendrá los coches que se leerán del fichero
	 * @throws FileNotFoundException En caso de no encontrar el fichero con el pathname dado
	 */
	public static void leerCoches(final String pahtname, final List <Coche> coches) throws FileNotFoundException {
		/* Declaración del scanner a usar para leer las líneas */
		final Scanner input = new Scanner(new File(pahtname));

		StringTokenizer st;
		
		while (input.hasNextLine()) {
			try {
				/* Vamos obteniendo los tokens los cuales están separados por "," */
				st = new StringTokenizer(input.nextLine(), ",");;
				
				/* Token modelo */
				final String modelo = st.nextToken();
				
				/* Intentamos obtener el tipo de combustible a partir del token */
				final Coche.TipoCombustible comb = Coche.parseTipoCombustible(st.nextToken());
				
				/* Pasamos a entero porque en el fichero se encuentra como doble, pero no tiene sentido que este no sea entero */
				final int asientos = (int) Double.parseDouble(st.nextToken());

				final Coche.TipoTansmisión trans = Coche.parseTipoTransmisión(st.nextToken());
				
				final double capacidad = Double.parseDouble(st.nextToken());

				final double consumoMedio = Double.parseDouble(st.nextToken());
				
				/* Creamos y añadimos el coche a la lista */
				coches.add(new Coche(modelo, comb, asientos, trans, capacidad, consumoMedio));

			} catch (NumberFormatException e) {
				System.err.println("Error trying to read number: " + e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println("Error trying to read enum: " + e.getMessage());
			} catch (NoSuchElementException e) {
				System.err.println("Error trying to read next token.");
			} catch (CarCreationException e) {
				System.err.println(e.getMessage());
			}
		}
		
		input.close();
	}
}
