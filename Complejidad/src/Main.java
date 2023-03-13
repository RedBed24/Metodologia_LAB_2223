import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import campusvirtual.Auxiliar;
import campusvirtual.leer;

/**
 * @author Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
 * @since 25/02/2023
 * @version 1
 * <p>
 * Invoca varios métodos (cuyo código se proporciona en Auxiliar.java) que manipulan las imágenes.
 * Se divide en 4 apartados:
 * <ul>
 * <li>a. Tranforma una imagen a escala de grises</li>
 * <li>b. Calcula el histograma de una imagen</li>
 * <li>c. Imprime el histograma</li>
 * <li>d. Ordena las columnas de una imagen de forma ascendente según el algoritmo de la burbuja o el algoritmo Quicksort.</li>
 * </ul>
 * Calcula la complejidad de cada apartado y foto. Por último, genera un csv los resultados.
 * </p>
 */
public class Main {
	
	public static void main(String[] args) {
		/* Almacena los nombres de las fotos a procesar */
		String[] fotoEntrada = { "320x214.png", "640x360.png", "640x427.png", "1024x1024.png", "1536x1536.png", };
		/* Creación de la matriz tiempoPasado */
		long[][] tiempoPasado = new long[5][fotoEntrada.length];

		String fotoSalida;
		long tiempoActual;

		int[] histograma = null;

		/* Seleccionar si el tiempo de ejecución se calcula en nanosegundos o milisegundos */
		boolean calcularTiempoNano = 's' == Character.toLowerCase(leer.caracter("¿Quieres calcular el tiempo en Nanosegundos? ['S', cualquier otra respuesta se medirá en milisegundos]:"));

		try {
			for (int i = 0; i < fotoEntrada.length; i++) {
				/* Informa de qué foto se va a procesar */
				System.out.println("Processing " + fotoEntrada[i] + "...");

				/* Si el nombre de la foto no acaba en .png, salta una excepción */
				if (!fotoEntrada[i].endsWith(".png")) throw new IllegalArgumentException(fotoEntrada[i]);

				System.out.println("Empezando apartado a");

				/* Asignación de la variable temporal fotoSalida (de fotoEntrada[i], ej 320x214_g.png) */
				fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4) + "_g.png";

				/* Según la opción que se selecciono anteriormente, se calcula el tiempo actual en nanosegundos o milisegundos */
				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				/* Transforma la foto a escala de grises */
				Auxiliar.GenerarImagenGrises(fotoEntrada[i], fotoSalida);
				/* Calcula y almacena el tiempo de ejecución del apartado a de la foto i */
				tiempoPasado[i][0] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;
				
				/* Comprueba si la foto está representada a escala de grises */
				if (comprobarEscalaGrises(fotoSalida))
					System.out.println("La imagen " + fotoSalida + " está en escala de grises.");

				System.out.println("Empezando apartado b");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				/* Calcula el histograma de la foto i */
				histograma = Auxiliar.HistogramaImagen(fotoEntrada[i]);
				tiempoPasado[i][1] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;

				System.out.println("Empezando apartado c");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				/* Imprime por pantalla el histograma */
				Auxiliar.ImprimeHistograma(histograma);
				tiempoPasado[i][2] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;

				System.out.println("Empezando apartado d");

				/* Si método es 0 según el algoritmo de la burbuja, si método es 1 según el algoritmo Quicksort */
				/* Haremos los dos seguidos */
				for (int método = 0; método <= 1; método++) {
					System.out.println("Usando método " + método + " en apartado d");
					
					fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4);
					switch (método) {
					case 0: fotoSalida += "_b.png"; break;
					case 1: fotoSalida += "_q.png"; break;
					}

					tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
					/* Ordena las columnas de la imagen de manera ascendente */
					Auxiliar.GenerarImagenOrdenandoColumnas(fotoEntrada[i], fotoSalida, método);
					tiempoPasado[i][3 + método] = calcularTiempoNano ? System.nanoTime(): System.currentTimeMillis() - tiempoActual;
				}

				System.out.println("Se ha terminado de procesar la imagen " + fotoEntrada[i]);
			}

			/* Generación del CSV con los datos de tiempoPasado */
			System.out.println("Generando fichero datos.csv");
			IO.generarCSV("datos.csv", tiempoPasado);

			System.out.println("Finalización correcta");
		
		/* Captura de excepciones */
		} catch (IllegalArgumentException e) {
			System.err.println("El nombre de la imagen no tiene el formato esperado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error de Entrada / Salida: " + e.getMessage());
		} catch (Exception e) {
			System.err.print("Error inesperado: " + e.getMessage());
		}

		System.out.println("Ending program.");
	}
	
	/**
	 * <p>
	 * Sirve para comprobar si una imagen se encuentra en escala de grises.
	 * </p>
	 * @param pathname String con la ruta de la imagen a comprobar
	 * @return True si la imagen está en escala de grises. False en caso contrario.
	 * @throws IOExcepcion
	 */
	public static boolean comprobarEscalaGrises(final String pathname) throws IOException {
		/* Una imagen está en escala de gises si para todos los píxeles, son grises
		 * Un pixel es gris cuando sus valores RGB son iguales
		 */
		boolean esGris = true;
		
		/* se lee la imagen */
		File Imagen = new File(pathname);
		BufferedImage input = ImageIO.read(Imagen);

		/* nos quedamos con la altura y anchura de la imagen */
		int w = input.getWidth();
		int h = input.getHeight();

		/* con que 1 pixel no sea gris, la imágen ya no es gris */
		for (int i = 0; i < w && esGris; i++) {
			for (int j = 0; j < h && esGris; j++) {
				int rgb = input.getRGB(i, j);
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				
				/* si son diferentes valores, no es gris */
				if (r != g || g != b) esGris = false;
			}
		}
		
		return esGris;
	}

}
