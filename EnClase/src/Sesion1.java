import profes.Auxiliar;
import profes.leer;

public class Sesion1 {

	public static void main(String[] args) {
		String[] fotoEntrada = { "320x214.png", "640x360.png", "640x427.png", "1024x1024.png", "1536x1536.png",  };
		long[][] tiempoPasado = new long[5][fotoEntrada.length];

		String fotoSalida;
		long tiempoActual;

		int[] histograma = null;

		boolean calcularTiempoNano = 's' == Character.toLowerCase(leer.caracter("¿Quieres calcular el tiempo en Nanosegundos? ['S', cualier otra resppuesta se medirá en milisegundos]:"));

		try {
			for (int i = 0; i < fotoEntrada.length; i++) {
				
				System.out.println("Processing " + fotoEntrada[i] + "...");

				System.out.println("Empezando apartado a");

				fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4) + "_g.png";

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				Auxiliar.GenerarImagenGrises(fotoEntrada[i], fotoSalida);
				tiempoPasado[0][i] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;

				System.out.println("Empezando apartado b");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				histograma = Auxiliar.HistogramaImagen(fotoEntrada[i]);
				tiempoPasado[1][i] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;

				System.out.println("Empezando apartado c");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				Auxiliar.ImprimeHistograma(histograma);
				tiempoPasado[2][i] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;

				System.out.println("Empezando apartado d");

				for (int método = 0; método <= 1; método++) {
					System.out.println("Usando método " + método + "en apartado d");

					fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4);
					switch (método) {
					case 0: fotoSalida += "_b.png"; break;
					case 1: fotoSalida += "_q.png"; break;
					}

					tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
					Auxiliar.GenerarImagenOrdenandoColumnas(fotoEntrada[i], fotoSalida, método);
					tiempoPasado[3+método][i] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;
				}
				
				System.out.println("Se ha terminado de procesar la imagen " + fotoEntrada[i]);
			}
			
			System.out.println("Generando fichero datos.csv");
			IO.generarCSV("datos.csv", tiempoPasado);

			System.out.println("Finalización correcta");
		} catch (Exception e) {
			System.err.print("Error");
		}

		System.out.println("Ending program.");

  }
}
