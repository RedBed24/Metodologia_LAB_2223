import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

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
	 * Abstrae la primera llamada al algoritmo de Backtracking del apartado A.
	 * Resuelve el problema propuesto:
	 * Listar la primera solución.
	 * </p>
	 * @param vacasDisponibles Array de vacas disponibles
	 * @param lecheDeseada Limitante de la leche en las mismas medidas de las vacas.
	 * @param límiteEspacio Limitante del espacio en las mismas medidas de las vacas.
	 * @return primera Solucion encontrada en caso de que exista. null si no existe solución
	 */
	public static OrientadoSolucion runA(final Vaca vacasDisponibles[], final double lecheDeseada, final int límiteEspacio) {
		return runA(0, vacasDisponibles, lecheDeseada, límiteEspacio, new OrientadoSolucion());
	}
	
	/**
	 * <p>
	 * Algoritmo Backtracing orientado a la solución que resuelve el problema del apartado A:
	 * Listar la primera solución.
	 * </p>
	 * @param siguienteAElegir Índice de aquella vaca por la que empezaremos a contemplar cuál va a entrar a la solución en esta etapa
	 * @param vacasDisponibles Array de vacas disponibles
	 * @param lecheDeseada Limitante de la leche en las mismas medidas de las vacas.
	 * @param límiteEspacio Limitante del espacio en las mismas medidas de las vacas.
	 * @param solucionTemporal Solución temporal sobre la que iremos añadiendo y quitando vacas
	 * @return primera Solucion encontrada en caso de que exista. null si no existe solución
	 */
	public static OrientadoSolucion runA(
			final int siguienteAElegir,
			final Vaca vacasDisponibles[],
			final double lecheDeseada,
			final int límiteEspacio,
			final OrientadoSolucion solucionTemporal
	) {
		// si es una solución, se copia
		OrientadoSolucion primera = lecheDeseada > 0 ? null : solucionTemporal.clone();

		// vemos las vacas que nos quedan por probar si es que no hemos encontardo ya una solución
		for (int i = siguienteAElegir; i < vacasDisponibles.length && primera == null; i++) {
			Vaca añadir = vacasDisponibles[i];
			
			// vemos si es viable
			if (añadir.getOcupaEspacio() <= límiteEspacio) {
				// la añadimos
				solucionTemporal.add(añadir);
				// exploramos esta rama
				primera = runA(i + 1, vacasDisponibles, lecheDeseada - añadir.getProducciónLeche(), límiteEspacio - añadir.getOcupaEspacio(), solucionTemporal);
				// volvemos al estado inicial
				solucionTemporal.pop();
			}
		}
		return primera;
	}

	/**
	 * <p>
	 * Abstrae la primera llamada al algoritmo de Backtracking del apartado B y C.
	 * Resuelve el problema propuesto:
	 * Contar las posiblidades que cumplan los limitantes y obtener la mejor solución.
	 * </p>
	 * @param vacasDisponibles Array de vacas disponibles
	 * @param lecheDeseada Limitante de la leche en las mismas medidas de las vacas.
	 * @param límiteEspacio Limitante del espacio en las mismas medidas de las vacas.
	 * @return Registro de las soluciones encontradas y la mejor
	 */
	public static Registro run(final Vaca vacasDisponibles[], final double lecheDeseada, final int límiteEspacio) {
		final Registro registro = new Registro();
		run(0, vacasDisponibles, lecheDeseada, límiteEspacio, new Solucion(vacasDisponibles.length), registro);
		return registro;
	}

	/**
	 * <p>
	 * Algoritmo Backtracing orientado a elementos que resuelve el problema del apartado B y C:
	 * Contar las posiblidades que cumplan los limitantes y obtener la mejor solución.
	 * </p>
	 * @param etapa Etapa en la que nos encontramos, indica sobre qué vaca de las disponibles nos toca elegir Etapa en la que nos encontramos, indica sobre qué vaca de las disponibles nos toca elegir
	 * @param vacasDisponibles Array de vacas disponibles
	 * @param lecheDeseada Limitante de la leche en las mismas medidas de las vacas.
	 * @param límiteEspacio Limitante del espacio en las mismas medidas de las vacas.
	 * @param solucionTemporal Solución temporal sobre la que iremos añadiendo y quitando vacas
	 * @param posiblesSoluciones Registro que lleva la cuenta de las soluciones encontradas y la mejor solución hasta el momento
	 */
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

			if (primera != null) {
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
