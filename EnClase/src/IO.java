import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IO {
	public static void generarCSV(final String pathname, final long[][] datos) throws IOException {
		File csv = new File(pathname);
		if (!csv.createNewFile()) throw new IOException("File " + pathname + " already exist...");
		
		FileWriter salida = new FileWriter(csv);
		
		for (long[] row : datos) {
			for (long dato : row) {
				salida.write(dato + ",");
			}
			salida.write("\n");
		}
		
		salida.close();
		
	}

}
