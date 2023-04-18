import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Vector;

import vaca.Vaca;

public class Main {
	
	public static int obtenerDatos(final String pathname, final Vector<Vaca> vacas) throws FileNotFoundException {
		IO.leerVacas(pathname, vacas);
		
		int espacioDisponible;
		do {
			espacioDisponible = campusvirtual.leer.entero("¿Cuánto espacio (en metros cuadrados) dispone la granga?");
			if (espacioDisponible <= 0) {
				System.err.println("No se puede tener tan poco espacio.");
			}
		} while (espacioDisponible <= 0);

		// convertimos a dm², que son las unidades en las que tarbajan las vacas
		return espacioDisponible * 100;
	}
	
	public static double calcularRatioPromedioEspacioComida(final Vector<Vaca> vacas) {
		int espacio = 0;
		int comida = 0;
		for (Vaca vaca : vacas) {
			espacio += vaca.getOcupaEspacio();
			comida += vaca.getConsumoComida();
		}
		return espacio / (double) comida;
	}

	public static int run(final Vector<Vaca> vacas, final Vector<Vaca> vacasSeleccionadas, int espacioDisponible, final Comparator<Vaca> comparador) {
		int índiceVacaActual = 0;

		/* quicksort requiere un array, no un vector */
		final Vaca[] vacasOrdenadasPorBeneficio = new Vaca[vacas.size()];
		for (int i = 0; i < vacas.size(); ++i) {
			vacasOrdenadasPorBeneficio[i] = vacas.get(i);
		}

		campusvirtual.Ordenar.quickSortC(vacasOrdenadasPorBeneficio, comparador);
		
		/*
		 * Salidas:
		 * ya hemos gastado el espacio
		 * no quedan vacas, este será el peor caso
		 */
		while (
				espacioDisponible > 0                                    /* ! sea solución */
				&& índiceVacaActual < vacasOrdenadasPorBeneficio.length  /* nos queden vacas por seleccionar */
		) {
			Vaca vacaActual = vacasOrdenadasPorBeneficio[índiceVacaActual++];

			/* vemos si la vaca es viable */
			if (espacioDisponible >= vacaActual.getOcupaEspacio() ) {
				/* en ese caso la seleccionados */
				vacasSeleccionadas.add(vacaActual);
				espacioDisponible -= vacaActual.getOcupaEspacio();
			}
		}

		return espacioDisponible;
	}

	public static void mostrarResultados(final Vector<Vaca> vacasSeleccionadas, final int espacioNoUsado) {
		System.out.println("Código Espacio Consumo Producción");
		System.out.println("           dm²      kg          L");
		double sumaLeche = 0;
		int sumaComida = 0;
		for (Vaca vaca : vacasSeleccionadas) {
			sumaLeche += vaca.getProducciónLeche();
			sumaComida += vaca.getConsumoComida();
			System.out.println(vaca);
		}
		System.out.printf("Espacio restante: %ddm². Producción de leche: %.3fL. Consumo de comida: %dkg\n", espacioNoUsado, sumaLeche, sumaComida);
	}

	public static void main(String[] args) {
		try {
			final Vector<Vaca> vacas = new Vector<>(30);

			final int espacioDisponible = obtenerDatos(Constantes.PATHNAME_VACAS, vacas);

			final Vector<Comparator<Vaca>> comparators = new Vector<>(2);
				comparators.add(new vaca.ordenacion.RatioProducciónEspacio());
				comparators.add(new vaca.ordenacion.CosteEspacioYComida(calcularRatioPromedioEspacioComida(vacas)));

			Vector<Vaca> vacasSeleccionadas;
			int espacioNoUsado;

			for (final Comparator<Vaca> comparador : comparators) {
				vacasSeleccionadas = new Vector<>(vacas.size()/2);

				espacioNoUsado = run(vacas, vacasSeleccionadas, espacioDisponible, comparador);

				System.out.println("\nOrdenación por: " + comparador.getClass().toString().substring(22));
				mostrarResultados(vacasSeleccionadas, espacioNoUsado);
			}

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + Constantes.PATHNAME_VACAS + "\".");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
