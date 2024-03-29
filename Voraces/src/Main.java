import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Vector;

import vaca.Vaca;

/**
 * <p>
 * Clase destinada a realizar los algoritmos principales.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-03-20
 */
public class Main {
	
	/**
	 * <p>
	 * Método Voraz que intenta resolver el problema propuesto.
	 * </p>
	 * @param vacas Array de vacas disponibles previamente ordenadas.
	 * @param espacioDisponible Limitante en las mismas unidades que las vacas dado.
	 * @return Solucion con las vacas seleccionasdas
	 */
	public static Solucion run(final Vaca vacas[], int espacioDisponible) {
		final Solucion vacasSeleccionadas = new Solucion();
		int índiceVacaActual = 0;

		/*
		 * Salidas:
		 * ya hemos gastado el espacio
		 * no quedan vacas, este será el peor caso
		 */
		while (espacioDisponible > 0 && índiceVacaActual < vacas.length) {
			Vaca vacaActual = vacas[índiceVacaActual];

			/* vemos si la vaca es viable */
			if (espacioDisponible >= vacaActual.getOcupaEspacio() ) {
				/* en ese caso la seleccionados */
				vacasSeleccionadas.añadirVaca(vacaActual);
				espacioDisponible -= vacaActual.getOcupaEspacio();
			}
			índiceVacaActual++;
		}

		return vacasSeleccionadas;
	}

	public static void main(String[] args) {
		final String nombreFicheroDatos = lecturadatos.Constantes.PATHNAME_VACAS;
		try {
			final Vaca vacas[] = lecturadatos.Fichero.leerVacas(nombreFicheroDatos);
			final int espacioDisponible = lecturadatos.Usuario.obtenerEspacioDisponible();

			final Vector<Comparator<Vaca>> comparators = new Vector<>(2);
				comparators.add(new vaca.ordenacion.MayorRatioProducciónEspacio());
				comparators.add(new vaca.ordenacion.MenorEspacioYComida());

			Solucion vacasSeleccionadas;

			for (final Comparator<Vaca> comparador : comparators) {

				campusvirtual.Ordenar.quickSortC(vacas, comparador);

				vacasSeleccionadas = run(vacas, espacioDisponible);

				System.out.println("\nOrdenación por: " + comparador);
				System.out.println(vacasSeleccionadas);
			}

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + nombreFicheroDatos + "\".");
		} catch (NoSuchElementException e) {
			System.err.println("El usuario ha dedicidido no introduir datos.");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
