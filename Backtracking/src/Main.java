import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import vaca.Vaca;

public class Main {

	public static Solucion runA(final Vaca vacasDisponibles[], final double lecheDeseada, final int límiteEspacio) {
		final Solucion primera = new Solucion(vacasDisponibles.length);
		runA(0, vacasDisponibles, lecheDeseada, límiteEspacio, primera);
		return primera;
	}

	private static boolean runA(
			final int etapa,
			final Vaca vacasDisponibles[],
			final double lecheDeseada,
			final int límiteEspacio,
			final Solucion solucion
	) {
		boolean solucionEncontrada = lecheDeseada <= 0;

		// si no quedan más vacas sobre las que dedicir, hemos decidido por todas o ya hemos llegado a una solución
		if (etapa == vacasDisponibles.length || solucionEncontrada) {
			// paramos y volvemos

		// si quedan vacas disponibles
		} else {
			// obtenemos la vaca correspondiente
			Vaca vacaEtapa = vacasDisponibles[etapa];

			// vemos si es viable
			if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
				solucion.añadirVaca(vacaEtapa, etapa); 

				solucionEncontrada = runA(
						// avanzamos la etapa
						etapa + 1,
						vacasDisponibles,
						// actualizamos la cantidad de leche deseada
						lecheDeseada - vacaEtapa.getProducciónLeche(),
						// actualizamos el espacio que gastamos
						límiteEspacio - vacaEtapa.getOcupaEspacio(),
						solucion
				);
			}

			if (!solucionEncontrada) {
				// exploramos la solución de no añadir la vaca
				solucion.quitarVaca(etapa);

				solucionEncontrada = runA(
						// sólo avanzamos la etapa
						etapa + 1,
						vacasDisponibles,
						lecheDeseada,
						límiteEspacio,
						solucion
				);
			}
		}

		return solucionEncontrada;
	}

	/**
	 * <p>
	 * Abstrae la primera llamada al algoritmo.
	 * </p>
	 * @param vacasDisponibles
	 * @param lecheDeseada
	 * @param límiteEspacio
	 * @param registro
	 */
	public static void run(
			final Vaca vacasDisponibles[],
			final double lecheDeseada,
			final int límiteEspacio,
			final Registro registro
	) {
		run(
				vacasDisponibles,
				lecheDeseada,
				límiteEspacio,
				// etapa
				0,
				// solución temporal para ir explorando
				new Solucion(vacasDisponibles.length),
				// contenedor de soluciones
				registro
		);
	}

	public static void run(
			final Vaca vacasDisponibles[],
			final double lecheDeseada,
			final int límiteEspacio,
			final int etapa,
			final Solucion solucion,
			final Registro posiblesSoluciones
	) {
		// si no quedan más vacas sobre las que dedicir, hemos decidido por todas
		if (etapa == vacasDisponibles.length) {
			// si es una posible solución
			if (lecheDeseada <= 0) {
				posiblesSoluciones.contemplarSolución(solucion);
			}

		// si quedan vacas disponibles
		} else {
			// obtenemos la vaca correspondiente
			Vaca vacaEtapa = vacasDisponibles[etapa];

			// vemos si es viable
			if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
				solucion.añadirVaca(vacaEtapa, etapa); 

				run(
						vacasDisponibles,
						// actualizamos la cantidad de leche deseada
						lecheDeseada - vacaEtapa.getProducciónLeche(),
						// actualizamos el espacio que gastamos
						límiteEspacio - vacaEtapa.getOcupaEspacio(),
						// avanzamos la etapa
						etapa + 1,
						solucion,
						posiblesSoluciones
				);
			}

			// exploramos la solución de no añadir la vaca
			solucion.quitarVaca(etapa);

			run(
					vacasDisponibles,
					lecheDeseada,
					límiteEspacio,
					// sólo avanzamos la etapa
					etapa + 1,
					solucion,
					posiblesSoluciones
			);
		}
	}
	
	public static void main (String[] args) {
		final String nombreFicheroDatos = lecturadatos.Constantes.PATHNAME_VACAS;
		try {
			final Vaca vacas[] = lecturadatos.Fichero.leerVacas(nombreFicheroDatos);
			final double lecheDeseada = lecturadatos.Usuario.obtenerLecheDeseada();
			final int espacioDisponible = lecturadatos.Constantes.ESPACIO_DISPONIBLE;

			final Solucion primera = runA(vacas, lecheDeseada, espacioDisponible);

			System.out.printf("La primera solución encontrada es: %s.\n", primera);

			// contenedor de soluciones
			final Registro registro = new Registro();

			run(
					vacas,
					lecheDeseada,
					espacioDisponible,
					registro
			);
		
			System.out.println(registro);

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + nombreFicheroDatos + "\".");
		} catch (NoSuchElementException e) {
			System.err.println("El usuario ha dedicidido no introduir datos.");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
