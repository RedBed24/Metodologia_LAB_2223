import java.io.FileNotFoundException;
import java.util.Vector;

import campusvirtual.leer;
import vaca.Vaca;

public class Main {
	
	public static int obtenerDatos(final String pathname, final Vector<Vaca> vacas) throws FileNotFoundException {
		IO.leerVacas(pathname, vacas);

		return leer.entero("¿Cuántas vacas serán seleccionadas?", 0, vacas.size());
	}
	
	public static int run(final Vector<Vaca> vacas, final Vector<Vaca> vacasSeleccionadas, final int comidaDisponible, final int cantidadVacasASeleccionar) {
		int índiceVacaActual = 0;
		int sumaConsumoComida = 0;

		final Vector<Vaca> vacasOrdenadasPorBeneficio = Ordenar.ordenarPorRatio(vacas);
		
		/*
		 * Salidas:
		 * ya hemos gastado la comida
		 * no quedan vacas, este será el peor caso
		 * ya hemos seleccionado el máximo
		 */
		while (
				! (sumaConsumoComida == comidaDisponible)                /* ! sea solución */
				&& índiceVacaActual < vacasOrdenadasPorBeneficio.size()  /* nos queden vacas por seleccionar */
				&& vacasSeleccionadas.size() < cantidadVacasASeleccionar /* podemos seleccionar más vacas */
		) {
			Vaca vacaActual = vacasOrdenadasPorBeneficio.get(índiceVacaActual);
			
			/* vemos si la vaca es viable */
			if (sumaConsumoComida + vacaActual.getConsumoComida() <= comidaDisponible) {
				/* en ese caso la seleccionados */
				vacasSeleccionadas.add(vacaActual);
				sumaConsumoComida += vacaActual.getConsumoComida();
			} else {
				/* TODO: Debug prints */
				System.out.println("Vaca no añadida: " + vacaActual);
				System.out.printf("suma %d, resto %d\n", sumaConsumoComida, comidaDisponible - sumaConsumoComida);
			}
			
			++índiceVacaActual;
		}
		
		/* TODO: Debug prints */
		System.out.println("\nIteraciones: " + índiceVacaActual);
		return comidaDisponible - sumaConsumoComida;
	}

	public static void mostrarResultados(final Vector<Vaca> vacasSeleccionadas, final int cantidadVacasASeleccionar, final int comidaNoUsada) {

		System.out.printf("\nLa comida que queda sin usarse es %dkg\n", comidaNoUsada);
		
		
		System.out.printf("\nLas %d vacas seleccionadas de %d que había que seleccionar son:\n", vacasSeleccionadas.size(), cantidadVacasASeleccionar);
		System.out.println("Código Consumo Producción Ratio");
		System.out.println("            kg          L  L/kg");
		for (Vaca vaca : vacasSeleccionadas) {
			System.out.println(vaca);
		}
	}

	public static void main(String[] args) {
		try {
			final Vector<Vaca> vacas = new Vector<>(50);

			final int cantidadVacasASeleccionar = obtenerDatos(Constantes.PATHNAME_VACAS, vacas);

			final Vector<Vaca> vacasSeleccionadas = new Vector<>(cantidadVacasASeleccionar);
			
			final int comidaNoUsada = run(vacas, vacasSeleccionadas, Constantes.COMIDA_DIARIA_DISPONIBLE, cantidadVacasASeleccionar);
			
			mostrarResultados(vacasSeleccionadas, cantidadVacasASeleccionar, comidaNoUsada);

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + Constantes.PATHNAME_VACAS + "\".");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
