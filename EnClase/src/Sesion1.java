import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import profes.Auxiliar;
import profes.leer;

/*********************************************************************
*
* Class Name: Complejidad
* Author/s name: Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
* Release/Creation date:25/02/2023
* Class version:
* Class description: Invoca varios métodos (cuyo código se proporciona en Auxiliar.java) que manipulan las imágenes. Se divide en 4 apartados:
* a. Tranforma una imagen a escala de grises, b. Calcula el histograma de una imagen, c. Imprime el histograma, d. Ordena las columnas de una imagen
* de forma ascendente según el algoritmo de la burbuja o el algoritmo Quicksort.
* Calcula la complejidad de cada apartado y foto. Por último, genera un csv los resultados.
*
**********************************************************************
*/
public class Sesion1 {
	
	public static void main(String[] args) {
		String[] fotoEntrada = { "320x214.png", "640x360.png", "640x427.png", "1024x1024.png", "1536x1536.png", }; // Almacena las fotos
		long[][] tiempoPasado = new long[5][fotoEntrada.length]; //Creación de la matriz tiempoPasado

		String fotoSalida;
		long tiempoActual;

		int[] histograma = null;

		// Seleccionar si el tiempo de ejecución se calcula en nanosegundos o milisegundos
		boolean calcularTiempoNano = 's' == Character.toLowerCase(leer.caracter(
				"¿Quieres calcular el tiempo en Nanosegundos? ['S', cualquier otra respuesta se medirá en milisegundos]:"));

		try {
			for (int i = 0; i < fotoEntrada.length; i++) {
				System.out.println("Processing " + fotoEntrada[i] + "..."); //Informa de que foto se va a procesar

				if (!fotoEntrada[i].endsWith(".png")) throw new IllegalArgumentException(fotoEntrada[i]); //Si la foto no acaba en .png, salta una excepción

				System.out.println("Empezando apartado a");

				fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4) + "_g.png"; //Asignación de la variable temporal fotoSalida (de fotoEntrada[i], ej 320x214_g.png)

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis(); //Según la opción que se selecciono anteriormente, se calcula el tiempo actual en nanosegundos o milisegundos
				Auxiliar.GenerarImagenGrises(fotoEntrada[i], fotoSalida); //Transforma la foto a escala de grises
				tiempoPasado[i][0] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;//Calcula y almacena el tiempo de ejecución del apartado a de la foto i
				
				if (comprobarEscalaGrises(fotoSalida)) //Comprueba si la foto está representada a escala de grises
					System.out.println("La imagen " + fotoSalida + " está en escala de grises.");

				System.out.println("Empezando apartado b");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				histograma = Auxiliar.HistogramaImagen(fotoEntrada[i]); // Calcula el histograma de la foto i
				tiempoPasado[i][1] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;//Calcula y almacena el tiempo de ejecución del apartado b de la foto i

				System.out.println("Empezando apartado c");

				tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
				Auxiliar.ImprimeHistograma(histograma);//Imprime por pantalla el histograma
				tiempoPasado[i][2] = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis() - tiempoActual;//Calcula y almacena el tiempo de ejecución del apartado c de la foto i

				System.out.println("Empezando apartado d");

				for (int método = 0; método <= 1; método++) { //si método es 0 según el algoritmo de la burbuja, si método es 1 según el algoritmo Quicksort 
					System.out.println("Usando método " + método + " en apartado d");
					
					fotoSalida = fotoEntrada[i].substring(0, fotoEntrada[i].length() - 4);
					switch (método) {
					case 0:
						fotoSalida += "_b.png";//Añade _b.png al final del string fotoSalida 
						break;
					case 1:
						fotoSalida += "_q.png";//Añade _q.png al final del string fotoSalida 
						break;
					}

					tiempoActual = calcularTiempoNano ? System.nanoTime() : System.currentTimeMillis();
					Auxiliar.GenerarImagenOrdenandoColumnas(fotoEntrada[i], fotoSalida, método); //Ordena las columnas de la imagen de manera ascendente
					tiempoPasado[i][3 + método] = calcularTiempoNano ? System.nanoTime(): System.currentTimeMillis() - tiempoActual; //Calcula y almacena el tiempo de ejecución del apartado d de la foto i
				}

				System.out.println("Se ha terminado de procesar la imagen " + fotoEntrada[i]);
			}

			System.out.println("Generando fichero datos.csv");
			IO.generarCSV("datos.csv", tiempoPasado); //Generación del CSV con los datos de tiempoPasado

			System.out.println("Finalización correcta");
		
		//Captura de excepciones
		} catch (IllegalArgumentException e) {
			System.err.println("El nombre de la imagen no tiene el formato esperado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error de Entrada / Salida: " + e.getMessage());
		} catch (Exception e) {
			System.err.print("Error inesperado: " + e.getMessage());
		}

		System.out.println("Ending program.");
	}
	
	/*********************************************************************
	 *
	 * Method name: comprobarEscalaGrises
	 *
	 * Name of the original author (if the module author is different than the
	 * author of the file): Samuel Espejo Gil
	 *
	 * Description of the Method: Sirve para comprobar si una imagen se encuentra 
	 * en escala de grises.
	 *
	 * Calling arguments: Parámetro pathname de tipo String, contiene la ruta de 
	 * la imagen. Será empleado para crear el fichero que almacenará la imagen.
	 *
	 * Return value: Devuelve esGris de tipo boolean. Se inicializa a true, no
	 * obstante, si la imagen no se encuentra en escala de grises, cambia a false.
	 *
	 * Required Files: Crea un fichero que almacena la imagen. 
	 *
	 * IOExcepcion: Clase base para las excepciones.
	 *
	 *********************************************************************/
	public static boolean comprobarEscalaGrises(final String pathname) throws IOException {
		boolean esGris = true;
		
		File Imagen = new File(pathname); //Creación de un fichero que almacena la imagen
		BufferedImage input = ImageIO.read(Imagen); //Para leer la imagen       

		int w = input.getWidth(); //Guarda la anchura en píxeles de la imagen
		int h = input.getHeight();//Guarda la altura en píxeles de la imagen

		for (int i = 0; i < w && esGris; i++) {
			for (int j = 0; j < h && esGris; j++) {
				int rgb = input.getRGB(i, j); //Almacena un pixel del color predeterminado
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb) & 0xff;
				if (r != g || g != b) esGris = false; //Si 'r' es distinto de 'g' o si 'g' es
				//distinto de 'b', esGris cambia a false
			}
		}
		
		return esGris;
	}

}
