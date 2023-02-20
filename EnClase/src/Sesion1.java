import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

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

				if (!fotoEntrada[i].endsWith(".png")) throw new IllegalArgumentException(fotoEntrada[i]);

				System.out.println("Empezando apartado a");

				fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4) + "_g.png";

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				Auxiliar.GenerarImagenGrises(fotoEntrada[i], fotoSalida);
				tiempoPasado[i][0] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;
				
				if (comprobarEscalaGrises(fotoSalida)) 
					System.out.println("La imagen " + fotoSalida + " está en escala de grises.");

				System.out.println("Empezando apartado b");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				histograma = Auxiliar.HistogramaImagen(fotoEntrada[i]);
				tiempoPasado[i][1] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;

				System.out.println("Empezando apartado c");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				Auxiliar.ImprimeHistograma(histograma);
				tiempoPasado[i][2] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;

				System.out.println("Empezando apartado d");

				for (int método = 0; método <= 1; método++) {
					System.out.println("Usando método " + método + " en apartado d");

					fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4);
					switch (método) {
					case 0: fotoSalida += "_b.png"; break;
					case 1: fotoSalida += "_q.png"; break;
					}

					tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
					Auxiliar.GenerarImagenOrdenandoColumnas(fotoEntrada[i], fotoSalida, método);
					tiempoPasado[i][3+método] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;
				}
				
				System.out.println("Se ha terminado de procesar la imagen " + fotoEntrada[i]);
			}
			
			System.out.println("Generando fichero datos.csv");
			IO.generarCSV("datos.csv", tiempoPasado);

			System.out.println("Finalización correcta");
		} catch (IllegalArgumentException e) {
			System.err.println("El nombre de la imagen no tiene el formato esperado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error de Entrada / Salida: " + e.getMessage());
		} catch (Exception e) {
			System.err.print("Error inesperado: " + e.getMessage());
		}

		System.out.println("Ending program.");
	}

	public static boolean comprobarEscalaGrises(final String pathname) throws IOException {
		boolean esGris = true;
		
		File Imagen = new File(pathname);
		BufferedImage input = ImageIO.read(Imagen);          

		int w = input.getWidth();
		int h = input.getHeight();

		for (int i = 0; i < w && esGris; i++) {
			for (int j = 0; j < h && esGris; j++) {
				int rgb = input.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				if (r != g || g != b) esGris = false;
			}
		}
		
		return esGris;
	}
}
