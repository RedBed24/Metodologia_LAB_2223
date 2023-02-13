import profes.Auxiliar;
import profes.leer;

public class Sesion1 {

	public static void main(String[] args) {
		try {
			/* Aparado a */
			String fotoEntradaA = leer.cadena("Introduce el nombre de la foto del apartado A:");

			if (!fotoEntradaA.endsWith(".png")) {
				System.err.println("El nombre de la imagen no tiene el formato esperado");
				throw new Exception();
			}

			String fotoSalidaA = fotoEntradaA.substring(0, fotoEntradaA.length() - 4) + "_g.png";

			Auxiliar.GenerarImagenGrises(fotoEntradaA, fotoSalidaA);
			
			/* Apartado b */
			String ruta = leer.cadena("Escribe la ruta de la foto del apartado B: ");
			int[] histograma = Auxiliar.HistogramaImagen(ruta);
			
			/* Aparatado c */
			System.out.println("El histograma de la imagen anterior es:");
			Auxiliar.ImprimeHistograma(histograma);

			/* Apartado d */
			String fotoEntradaD = leer.cadena("Introduce el nombre de la foto del apartado D:");

			if (!fotoEntradaD.endsWith(".png")) {
				System.err.println("El nombre de la imagen no tiene el formato esperado");
				throw new Exception();
			}
			
			int método = leer.entero("Introduce el método deseado:\n0: BubbleSort.\n1: QuickSort.\n", 0, 1);

			String fotoSalidaD = fotoEntradaD.substring(0, fotoEntradaD.length() - 4);
			switch (método) {
			case 0: fotoSalidaD += "_b.png"; break;
			case 1: fotoSalidaD += "_q.png"; break;
			}

			Auxiliar.GenerarImagenOrdenandoColumnas(fotoEntradaD, fotoSalidaD, método);

			System.out.println("Succesfully");
		} catch (Exception e) {
			System.err.print("Error,");
		}
		System.out.println(" ending program.");
	}

}
