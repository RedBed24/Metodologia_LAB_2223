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
	 * @return solución Solución que se ha encontrado, si no hay niguna, se devuelve null.
	 */
	public static OrientadoSolucion apartadoA(final Vaca vacasDisponibles[], double lecheDeseada, int límiteEspacio) {
		OrientadoSolucion solución = new OrientadoSolucion();
		return apartadoA(0, vacasDisponibles, lecheDeseada, límiteEspacio, solución) ? solución : null;
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
	 * @param solucion Solución temporal sobre la que iremos añadiendo y quitando vacas
	 * @return boolean indicando si se ha encontrado una solución o no
	 */
	public static boolean apartadoA(
			int siguienteAElegir,
			final Vaca vacasDisponibles[],
			double lecheDeseada,
			int límiteEspacio,
			OrientadoSolucion solucion
	) {
		// comprobamos si hemos encontrado una solución
		boolean soluciónEncontrada = lecheDeseada <= 0;

		// vemos las vacas que nos quedan por probar si es que no hemos encontardo ya una solución
		for (int i = siguienteAElegir; i < vacasDisponibles.length && !soluciónEncontrada; i++) {
			Vaca añadir = vacasDisponibles[i];
			
			// vemos si es viable
			if (añadir.getOcupaEspacio() <= límiteEspacio) {
				// la añadimos
				solucion.add(añadir);
				// exploramos esta rama
				soluciónEncontrada = apartadoA(i + 1, vacasDisponibles, lecheDeseada - añadir.getProducciónLeche(), límiteEspacio - añadir.getOcupaEspacio(), solucion);
				
				if (!soluciónEncontrada) {
					// volvemos al estado inicial
					solucion.pop();
				}
			}
		}
		return soluciónEncontrada;
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
	public static Registro apartadoBC(final Vaca vacasDisponibles[], double lecheDeseada, int límiteEspacio) {
		Registro registro = new Registro(lecheDeseada);
		apartadoBC(0, vacasDisponibles, lecheDeseada, límiteEspacio, new Solucion(vacasDisponibles.length), registro);
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
	private static void apartadoBC(
			int etapa,
			final Vaca vacasDisponibles[],
			double lecheDeseada,
			int límiteEspacio,
			Solucion solucionTemporal,
			Registro posiblesSoluciones
	) {
		// si no quedan más vacas sobre las que dedicir, hemos decidido por todas
		if (etapa == vacasDisponibles.length) {
			posiblesSoluciones.contemplarSolución(solucionTemporal);

		// si quedan vacas disponibles
		} else {
			// obtenemos la vaca correspondiente
			Vaca vacaEtapa = vacasDisponibles[etapa];

			// vemos si es viable
			if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
				solucionTemporal.añadirVaca(vacaEtapa, etapa); 

				apartadoBC(
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

			apartadoBC(
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

			final OrientadoSolucion primera = apartadoA(vacas, lecheDeseada, espacioDisponible);

			if (primera != null) {
				System.out.printf("La primera solución encontrada es: %s.\n", primera);
			} else {
				System.out.println("No se ha encontrado una solución.\nEl límite de espacio es muy pequeño y la producción de leche desesada es muy grande.");
			}

			final Registro registro = apartadoBC(vacas, lecheDeseada, espacioDisponible);

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
