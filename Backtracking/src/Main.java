import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import vaca.Vaca;

public class Main {
	
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
			solucion.añadirVaca(null, etapa);

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
			
			// contenedor de soluciones
			final Registro registro = new Registro();

			run(
					vacas,
					lecturadatos.Usuario.obtenerLecheDeseada(),
					lecturadatos.Constantes.ESPACIO_DISPONIBLE,
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
