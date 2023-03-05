import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
 * @since 25/02/2023
 * @version 1
 * <p>
 * Sirve para manejar ficheros externos. Lectura y Escritura de ellos.
 * </p>
 */
public class IO {
	/**
	 * <p>
	 * Escribe todos datos de la matriz en un fichero csv siguiendo el mismo esquema que la matriz.
	 * </p>
	 * @param pathname Nombre del fichero a crear. En este se pondrán los datos.
	 * @param datos Matriz de datos a escirbir en el fichero.
	 * @throws IOException Si el archivo ya existe.
	 */
	public static void generarCSV(final String pathname, final long[][] datos) throws IOException {
		/* Creación del fichero */
		File csv = new File(pathname);

		/* Si el fichero existe, salta la excepción */
		if (!csv.createNewFile()) throw new IOException("File " + pathname + " already exist..."); 
	
		FileWriter salida = new FileWriter(csv);
		
		/* por cada dato */
		for (long[] row : datos) {
			for (long dato : row) {
				/* Escribe cada dato separandolos por una coma */
				salida.write(dato + ",");
			}
			/* Cambiar de fila */
			salida.write("\n");
		}
		
		/* Guardamos y cerramos el fichero */
		salida.close();
	}

}
