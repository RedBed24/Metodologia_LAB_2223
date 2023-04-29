import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import vaca.Vaca;

public class Main {

	public static OrientadoSolucion runA(final Vaca vacasDisponibles[], final double lecheDeseada, final int límiteEspacio) {
		final OrientadoSolucion solucion = new OrientadoSolucion();
		runA(0, -1, vacasDisponibles, lecheDeseada, límiteEspacio, solucion);
		return solucion;
	}
	
	public static boolean runA(
			final int etapa,
			final int últimoElegido,
			final Vaca vacasDisponibles[],
			final double lecheDeseada,
			final int límiteEspacio,
			final OrientadoSolucion solucion
	) {
		boolean soluciónEncontrada = lecheDeseada <= 0;
		if (últimoElegido == vacasDisponibles.length) {
			// ya no quedan elementos por coger en el conjunto
		} else {
			for (int i = últimoElegido + 1; i < vacasDisponibles.length && !soluciónEncontrada; i++) {
				Vaca añadir = vacasDisponibles[i];
				if (añadir.getOcupaEspacio() <= límiteEspacio) {
					solucion.add(añadir);
					soluciónEncontrada = runA(etapa + 1, i, vacasDisponibles, lecheDeseada - añadir.getProducciónLeche(), límiteEspacio - añadir.getOcupaEspacio(), solucion);
					solucion.pop();
				}
			}
		}
		return soluciónEncontrada;
	}

	/**
	 * <p>
	 * Abstrae la primera llamada al algoritmo.
	 * </p>
	 * @param vacasDisponibles
	 * @param lecheDeseada
	 * @param límiteEspacio
	 * @return
	 */
	public static Registro run(final Vaca vacasDisponibles[], final double lecheDeseada, final int límiteEspacio) {
		final Registro registro = new Registro();
		run(0, vacasDisponibles, lecheDeseada, límiteEspacio, new Solucion(vacasDisponibles.length), registro);
		return registro;
	}

	private static void run(
			final int etapa,
			final Vaca vacasDisponibles[],
			final double lecheDeseada,
			final int límiteEspacio,
			final Solucion solucionTemporal,
			final Registro posiblesSoluciones
	) {
		// si no quedan más vacas sobre las que dedicir, hemos decidido por todas
		if (etapa == vacasDisponibles.length) {
			// si es una posible solución
			if (lecheDeseada <= 0) {
				posiblesSoluciones.contemplarSolución(solucionTemporal);
			}

		// si quedan vacas disponibles
		} else {
			// obtenemos la vaca correspondiente
			Vaca vacaEtapa = vacasDisponibles[etapa];

			// vemos si es viable
			if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
				solucionTemporal.añadirVaca(vacaEtapa, etapa); 

				run(
						// avanzamos la etapa
						etapa + 1,
						vacasDisponibles,
						// actualizamos la cantidad de leche deseada
						lecheDeseada - vacaEtapa.getProducciónLeche(),
						// actualizamos el espacio que gastamos
						límiteEspacio - vacaEtapa.getOcupaEspacio(),
						solucionTemporal,
						posiblesSoluciones
				);
			}

			// exploramos la solución de no añadir la vaca
			solucionTemporal.quitarVaca(etapa);

			run(
					// sólo avanzamos la etapa
					etapa + 1,
					vacasDisponibles,
					lecheDeseada,
					límiteEspacio,
					solucionTemporal,
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

			final OrientadoSolucion primera = runA(vacas, lecheDeseada, espacioDisponible);

			if (primera.getProducciónLeche() >= lecheDeseada) {
				System.out.printf("La primera solución encontrada es: %s.\n", primera);

				final Registro registro = run(vacas, lecheDeseada, espacioDisponible);

				System.out.println(registro);
			} else {
				System.out.println("No se ha encontrado una solución.\nEl límite de espacio es muy pequeño y la producción de leche desesada es muy grande.");
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
