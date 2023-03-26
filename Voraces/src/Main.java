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
		
		return espacioDisponible;
	}
	
	public static int run(final Vector<Vaca> vacas, final Vector<Vaca> vacasSeleccionadas, final int espacioDisponible, final Comparator<Vaca> comparador) {
		int índiceVacaActual = 0;
		int sumaEspacioGastado = 0;

		final Vaca[] vacasOrdenadasPorBeneficio = new Vaca[vacas.size()];
		for (int i = 0; i < vacas.size(); ++i) {
			vacasOrdenadasPorBeneficio[i] = vacas.get(i);
		}

		campusvirtual.Ordenar.quickSortC(vacasOrdenadasPorBeneficio, comparador);
		
		/*
		 * Salidas:
		 * ya hemos gastado la comida
		 * no quedan vacas, este será el peor caso
		 */
		while (
				! (sumaEspacioGastado == espacioDisponible)              /* ! sea solución */
				&& índiceVacaActual < vacasOrdenadasPorBeneficio.length  /* nos queden vacas por seleccionar */
		) {
			Vaca vacaActual = vacasOrdenadasPorBeneficio[índiceVacaActual];
			
			/* vemos si la vaca es viable */
			if (sumaEspacioGastado + vacaActual.getOcupaEspacio() <= espacioDisponible) {
				/* en ese caso la seleccionados */
				vacasSeleccionadas.add(vacaActual);
				sumaEspacioGastado  += vacaActual.getOcupaEspacio();
			} else {
				System.out.println(vacaActual);
			}
			
			++índiceVacaActual;
		}
		
		return espacioDisponible - sumaEspacioGastado;
	}

	public static void mostrarResultados(final Vector<Vaca> vacasSeleccionadas, final int espacioNoUsado) {
		System.out.println("\nCódigo Espacio Consumo Producción");
		System.out.println("           dm²      kg          L");
		double suma = 0;
		for (Vaca vaca : vacasSeleccionadas) {
			suma += vaca.getProducciónLeche();
			System.out.println(vaca);
		}
		System.out.printf("El espacio restante es: %d, con una producción de leche: %.3f\n", espacioNoUsado, suma);
	}

	public static void main(String[] args) {
		try {
			final Vector<Vaca> vacas = new Vector<>(30);

			final int espacioDisponible = obtenerDatos(Constantes.PATHNAME_VACAS, vacas);

			final Vector<Vaca> vacasSeleccionadas = new Vector<>(vacas.size()/2);
			
			final int espacioNoUsado = run(vacas, vacasSeleccionadas, espacioDisponible, new vaca.ordenacion.Produción());
			
			mostrarResultados(vacasSeleccionadas, espacioNoUsado);

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + Constantes.PATHNAME_VACAS + "\".");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
