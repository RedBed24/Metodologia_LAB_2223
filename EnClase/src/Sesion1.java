import profes.Auxiliar;
import profes.leer;

public class Sesion1 {

	public static void main(String[] args) {
		long[][] tiempoPasado = new long[5][5];
		String[] fotoEntrada = { "1024x1024.png", "1536x1536.png", "320x214.png", "640x360.png", "640x427.png" };
		String[] fotoSalida = new String[5];
		long tiempoActual;
		int[] histograma = null;
		boolean calcularTiempoNano = 's' == Character.toLowerCase(leer.caracter(
				"¿Quieres calcular el tiempo en Nanosegundos? ['S', cualier otra resppuesta se medirá en milisegundos]:"));
		boolean salir = false;

		do {
			try {
				/*
				 * switch (Character.toLowerCase(leer.caracter(
				 * "Elige un apartado: ['A', 'B', 'C', 'D', 'S'] (Selecionar 'S' sale del programa):"
				 * ))) { case 'a':
				 */
				for (int i = 0; i < 5; i++) {
					/* = leer.cadena("Introduce el nombre de la foto del apartado A:"); */

					if (!fotoEntrada[i].endsWith(".png")) {
						System.err.println("El nombre de la imagen no tiene el formato esperado");
						throw new Exception();
					}

					fotoSalida[i] = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4) + "_g.png";

					tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
					Auxiliar.GenerarImagenGrises(fotoEntrada[i], fotoSalida[i]);
					tiempoPasado[0][i] = calcularTiempoNano ? System.nanoTime()
							: System.currentTimeMillis() - tiempoActual;
				/*
				 * break; case 'b':
				 */
					/* String ruta = leer.cadena("Escribe la ruta de la foto del apartado B: "); */
					tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
					histograma = Auxiliar.HistogramaImagen(fotoEntrada[i]);
					tiempoPasado[1][i] = calcularTiempoNano ? System.nanoTime()
							: System.currentTimeMillis() - tiempoActual;

				/*
				 * break; case 'c':
				 */
					if (histograma == null) {
						System.err.println(
								"El histograma todavía no está inicializado, por favor, usa el apartado 'B' antes.");
						throw new Exception();
					}

					System.out.println("El histograma de la imagen anterior es:");
					tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
					Auxiliar.ImprimeHistograma(histograma);
					tiempoPasado[2][i] = calcularTiempoNano ? System.nanoTime()
							: System.currentTimeMillis() - tiempoActual;
				/*
				 * break; case 'd':
				 
				String fotoEntradaD = leer.cadena("Introduce el nombre de la foto del apartado D:");*/

				if (!fotoEntrada[i].endsWith(".png")) {
					System.err.println("El nombre de la imagen no tiene el formato esperado");
					throw new Exception();
				}

				for (int método = 0; método <= 1; método++) {/*leer.entero("Introduce el método deseado:\n0: BubbleSort.\n1: QuickSort.\n", 0, 1);*/

				fotoSalida[i] = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4);
				switch (método) {
				case 0:
					fotoSalida[i] += "_b.png";
					break;
				case 1:
					fotoSalida[i] += "_q.png";
					break;
				}

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				Auxiliar.GenerarImagenOrdenandoColumnas(fotoEntrada[i], fotoSalida[i], método);
				tiempoPasado[3+método][i] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;
				}
				/*
				 * break; case 's': salir = true; break; default:
				 * System.err.println("El apartado no existe."); throw new Exception(); }
				 */
				}
			} catch (Exception e) {
				System.err.print("Error");
			}

	}while(!salir);System.out.println("Ending program.");

  }
}
