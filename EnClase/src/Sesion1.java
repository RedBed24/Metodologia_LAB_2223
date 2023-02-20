import profes.Auxiliar;
import profes.leer;

public class Sesion1 {

	public static void main(String[] args) {
		long[] tiempoPasado = new long[4];
		long tiempoActual;
		int[] histograma = null;
		boolean calcularTiempoNano = 's' == Character.toLowerCase(leer.caracter("¿Quieres calcular el tiempo en Nanosegundos? ['S', cualier otra resppuesta se medirá en milisegundos]:"));
		boolean salir = false; 

		do {
		try {
		switch (Character.toLowerCase(leer.caracter("Elige un apartado: ['A', 'B', 'C', 'D', 'S'] (Selecionar 'S' sale del programa):"))) {
		case 'a':
			String fotoEntradaA = leer.cadena("Introduce el nombre de la foto del apartado A:");

			if (!fotoEntradaA.endsWith(".png")) {
				System.err.println("El nombre de la imagen no tiene el formato esperado");
				throw new Exception();
			}

			String fotoSalidaA = fotoEntradaA.substring(0, fotoEntradaA.length() - 4) + "_g.png";

			tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
			Auxiliar.GenerarImagenGrises(fotoEntradaA, fotoSalidaA);
			tiempoPasado[0] = System.nanoTime() - tiempoActual;
			
			break;
		case 'b':
			
			String ruta = leer.cadena("Escribe la ruta de la foto del apartado B: ");

			histograma = Auxiliar.HistogramaImagen(ruta);
			
			break;
		case 'c':
			if (histograma == null) {
				System.err.println("El histograma todavía no está inicializado, por favor, usa el apartado 'B' antes.");
				throw new Exception();
			}

			System.out.println("El histograma de la imagen anterior es:");
			Auxiliar.ImprimeHistograma(histograma);

			break;
		case 'd':
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
			
			break;
		case 's':
			salir = true;
			break;
		default:
			System.err.println("El apartado no existe.");
			throw new Exception();
		}
		} catch (Exception e) {
			System.err.print("Error");
		}

		} while (!salir);
		System.out.println("Ending program.");

	}

}
