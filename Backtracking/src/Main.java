import java.io.FileNotFoundException;
import java.util.Vector;

import vaca.Vaca;

public class Main {

	public static boolean runA (
			final Vector<Vaca> vacasDisponibles,
			final double lecheDeseada,
			final int límiteEspacio,
			final int etapa,
			final Solucion solucion
	) {
		boolean soluciónEncontrada = lecheDeseada <= 0;
		
		// si no hemos llegado a una solución
		if (!soluciónEncontrada) {
			// si no quedan más vacas disponibles
			if (etapa == vacasDisponibles.size()) {
				// no se ha llegado a una solución por aquí

			// si quedan vacas disponibles
			} else {
				// obtenemos la vaca correspondiente
				Vaca vacaEtapa = vacasDisponibles.elementAt(etapa);

				// TODO: Comprobar si es mejor ir añadiendo las vacas "a la que volvemos", primero por la rama del null

				// vemos si es viable
				if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
					solucion.añadirVaca(vacaEtapa, etapa); 

					soluciónEncontrada = runA(
							vacasDisponibles,
							// actualizamos la cantidad de leche deseada
							lecheDeseada - vacaEtapa.getProducciónLeche(),
							// actualizamos el espacio que gastamos
							límiteEspacio - vacaEtapa.getOcupaEspacio(),
							// avanzamos la etapa
							etapa + 1,
							solucion
					);
				}

				// si todavía no hemos encontrado la solución, seguimos buscando
				if (!soluciónEncontrada) {
					// probamos a no añadir la vaca
					solucion.añadirVaca(null, etapa);

					// probamos la posibilidad de no añadirla
					soluciónEncontrada = runA(
							vacasDisponibles,
							lecheDeseada,
							límiteEspacio,
							// avanzamos la etapa
							etapa + 1,
							solucion
					);
				}
			}
		}

		return soluciónEncontrada;
	}

	public static void runB (
			final Vector<Vaca> vacasDisponibles,
			final double lecheDeseada,
			final int límiteEspacio,
			final int etapa,
			final Solucion solucion,
			final Vector<Solucion> posiblesSoluciones
	) {
		// si es una posible solución
		if (lecheDeseada <= 0) {
			// guardamos una copia
			posiblesSoluciones.add(new Solucion(solucion));
		}
		
		// si no quedan más vacas disponibles
		if (etapa == vacasDisponibles.size()) {
			// no se ha llegado a una solución por aquí

		// si quedan vacas disponibles
		} else {
			// obtenemos la vaca correspondiente
			Vaca vacaEtapa = vacasDisponibles.elementAt(etapa);

			// TODO: Comprobar si es mejor ir añadiendo las vacas "a la que volvemos", primero por la rama del null

			// vemos si es viable
			if (vacaEtapa.getOcupaEspacio() <= límiteEspacio) {
				solucion.añadirVaca(vacaEtapa, etapa); 

				runB(
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

			// probamos a no añadir la vaca
			solucion.añadirVaca(null, etapa);

			// probamos la posibilidad de no añadirla
			runB(
					vacasDisponibles,
					lecheDeseada,
					límiteEspacio,
					// avanzamos la etapa
					etapa + 1,
					solucion,
					posiblesSoluciones
			);
		}
	}
	
	public static Solucion runC(final Vector<Solucion> posiblesSoluciones) {
		Solucion mejor = posiblesSoluciones.elementAt(0);

		for (final Solucion solu : posiblesSoluciones) {
			if (mejor.getProducciónLeche() < solu.getProducciónLeche()) {
				mejor = solu;
			}
		}

		return mejor;
	}

	public static void main (String[] args) {
		final String nombreFicheroDatos = lecturadatos.Constantes.PATHNAME_VACAS;
		try {
			final Vector<Vaca> vacas = new Vector<>(30);

			lecturadatos.Fichero.leerVacas(nombreFicheroDatos, vacas);

			final double lecheDeseada = lecturadatos.Usuario.obtenerLecheDeseada();

			Solucion solucion = new Solucion(vacas.size());

			runA(vacas, lecheDeseada, lecturadatos.Constantes.ESPACIO_DISPONIBLE, 0, solucion);

			System.out.println(solucion);
			
			final Vector<Solucion> posiblesSoluciones = new Vector<Solucion>(vacas.size());
			
			runB(vacas, lecheDeseada, lecturadatos.Constantes.ESPACIO_DISPONIBLE, 0, solucion, posiblesSoluciones);
			
			System.out.printf("Existen %d soluciones\n", posiblesSoluciones.size());
			
			final Solucion mejor = runC(posiblesSoluciones);
			
			System.out.println(mejor);

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + nombreFicheroDatos + "\".");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
			e.printStackTrace();
		}
	}

}
