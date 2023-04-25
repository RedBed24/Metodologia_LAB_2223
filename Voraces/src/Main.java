import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Vector;

import vaca.Vaca;

public class Main {
	
	public static double calcularRatioPromedioEspacioComida(final Vaca vacas[]) {
		// TODO: El problema de esto es la generalidad, otro tipo de elementos, o más dispersos??
		int espacio = 0;
		int comida = 0;
		for (Vaca vaca : vacas) {
			espacio += vaca.getOcupaEspacio();
			comida += vaca.getConsumoComida();
		}
		return espacio / (double) comida;
	}

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
				comparators.add(new vaca.ordenacion.MenorCosteEspacioYComida(calcularRatioPromedioEspacioComida(vacas)));
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
