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
	 * Abstrae la primera llamada al algoritmo de Backtracking del apartado B.
	 * Resuelve el problema propuesto:
	 * Contar las posiblidades que cumplan los limitantes.
	 * </p>
	 * @param vacasDisponibles Array de vacas disponibles
	 * @param lecheDeseada Limitante de la leche en las mismas medidas de las vacas.
	 * @param límiteEspacio Limitante del espacio en las mismas medidas de las vacas.
	 * @return Cuenta de las soluciones encontradas
	 */
	public static int apartadoB(final Vaca vacasDisponibles[], double lecheDeseada, int límiteEspacio) {
		return apartadoB(0, vacasDisponibles, lecheDeseada, límiteEspacio, new Solucion(vacasDisponibles.length), 0);
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
	 * @param cuentaSoluciones Cuenta de las solcuciones encontradas
	 * @return Cuenta de las solciones encontradas
	 */
	private static int apartadoB(
			int etapa,
			final Vaca vacasDisponibles[],
			double lecheDeseada,
			int límiteEspacio,
			Solucion solucionTemporal,
			int cuentaSoluciones
	) {
		// si no quedan más vacas sobre las que dedicir, hemos decidido por todas
		if (etapa == vacasDisponibles.length) {
			if (lecheDeseada <= 0) {
				cuentaSoluciones++;
			}

		// si quedan vacas disponibles
		} else {
			// obtenemos la vaca correspondiente
			Vaca vacaEtapa = vacasDisponibles[etapa];

			// vemos si es viable
			if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
				solucionTemporal.añadirVaca(vacaEtapa, etapa); 

				cuentaSoluciones = apartadoB(
						// avanzamos la etapa
						etapa + 1,
						vacasDisponibles,
						// actualizamos la cantidad de leche deseada
						lecheDeseada - vacaEtapa.getProducciónLeche(),
						// actualizamos el espacio que gastamos
						límiteEspacio - vacaEtapa.getOcupaEspacio(),
						solucionTemporal,
						cuentaSoluciones
				);
			}

			// exploramos la solución de no añadir la vaca
			solucionTemporal.quitarVaca(etapa);

			cuentaSoluciones = apartadoB(
					// sólo avanzamos la etapa
					etapa + 1,
					vacasDisponibles,
					lecheDeseada,
					límiteEspacio,
					solucionTemporal,
					cuentaSoluciones
			);
		}
		return cuentaSoluciones;
	}
	
	/**
	 * <p>
	 * Abstrae la primera llamada al algoritmo de Backtracking del apartado C.
	 * Resuelve el problema propuesto:
	 * Obtener la mejor solución.
	 * </p>
	 * @param vacasDisponibles Array de vacas disponibles
	 * @param límiteEspacio Limitante del espacio en las mismas medidas de las vacas.
	 * @return La mejor solución encontrada
	 */
	public static Solucion apartadoC(final Vaca vacasDisponibles[], int límiteEspacio) {
		return apartadoC(0, vacasDisponibles, límiteEspacio, new Solucion(vacasDisponibles.length), new Solucion(vacasDisponibles.length));
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
	 * @param mejor La mejor solución encontrada hasta el momento
	 */
	private static Solucion apartadoC(
			int etapa,
			final Vaca vacasDisponibles[],
			int límiteEspacio,
			Solucion solucionTemporal,
			Solucion mejor
	) {
		// si no quedan más vacas sobre las que dedicir, hemos decidido por todas
		if (etapa == vacasDisponibles.length) {
			if (mejor.getProducciónLeche() < solucionTemporal.getProducciónLeche()) {
				mejor = solucionTemporal.clone();
			}

		// si quedan vacas disponibles
		} else {
			// obtenemos la vaca correspondiente
			Vaca vacaEtapa = vacasDisponibles[etapa];

			// vemos si es viable
			if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
				solucionTemporal.añadirVaca(vacaEtapa, etapa); 

				mejor = apartadoC(
						// avanzamos la etapa
						etapa + 1,
						vacasDisponibles,
						// actualizamos el espacio que gastamos
						límiteEspacio - vacaEtapa.getOcupaEspacio(),
						solucionTemporal,
						mejor
				);
			}

			// exploramos la solución de no añadir la vaca
			solucionTemporal.quitarVaca(etapa);

			mejor = apartadoC(
					// sólo avanzamos la etapa
					etapa + 1,
					vacasDisponibles,
					límiteEspacio,
					solucionTemporal,
					mejor
			);
		}
		return mejor;
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
				
				final int cuentaSoluciones = apartadoB(vacas, lecheDeseada, espacioDisponible);

				System.out.printf("Total: %d soluciones factibles de %.0f posibilidades totales (%.3f%%).\n", cuentaSoluciones, Math.pow(2, vacas.length), 100 * (cuentaSoluciones / Math.pow(2, vacas.length)));
			} else {
				System.out.println("No se ha encontrado una solución.\nEl límite de espacio es muy pequeño y la producción de leche desesada es muy grande.");
			}
			
			final Solucion mejor = apartadoC(vacas, espacioDisponible);

			System.out.printf("La mejor solución encontrada es: %s\n", mejor);

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + nombreFicheroDatos + "\".");
		} catch (NoSuchElementException e) {
			System.err.println("El usuario ha dedicidido no introduir datos.");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
		}
	}

}
