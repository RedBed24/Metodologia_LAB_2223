import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*********************************************************************
*
* Class Name: IO
* Author/s name: Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
* Release/Creation date:25/02/2023
* Class version:
* Class description: Sirve para generar y escribir en el csv los 
* tiempos de ejecución de cada uno de los métodos y fotos.
*
**********************************************************************
*/
public class IO {
	public static void generarCSV(final String pathname, final long[][] datos) throws IOException {
		File csv = new File(pathname); //Creación del fichero
		if (!csv.createNewFile()) throw new IOException("File " + pathname + " already exist..."); 
		//Si el fichero existe, salta la excepción
		FileWriter salida = new FileWriter(csv); //Para escribir caracteres en el fichero 
		
		for (long[] row : datos) {
			for (long dato : row) {
				salida.write(dato + ","); //Escribe cada dato separandolos por una coma
			}
			salida.write("\n"); //Cambiar de fila
		}
		
		salida.close(); //Cerrar el fichero
		
	}

}
