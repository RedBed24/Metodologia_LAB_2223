import java.io.FileNotFoundException;
import java.util.Vector;

import vaca.Vaca;

public class Main {

	public static void run (
			final Vaca vacasDisponibles[],
			final double lecheDeseada,
			final int límiteEspacio,
			final int etapa,
			final Solucion solucion,
			final Registro posiblesSoluciones
	) {
		// si es una posible solución
		if (lecheDeseada <= 0) {
			posiblesSoluciones.contemplarSolución(solucion);
		}
		
		// si no quedan más vacas disponibles
		if (etapa == vacasDisponibles.length) {
			// no avanzamos

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
			// si no es viable ya no hay más posibilidades ya que están ordenadas por espacio,
			// es decir, la siguiente ocupa lo mismo o más, por lo que tampoco va a entrar
		}
	}
	
	public static void main (String[] args) {
		final String nombreFicheroDatos = lecturadatos.Constantes.PATHNAME_VACAS;
		try {
			final Vector<Vaca> vacas = new Vector<>(30);

			lecturadatos.Fichero.leerVacas(nombreFicheroDatos, vacas);

			final Vaca vacasOrdenadas[] = new Vaca[vacas.size()];
			for (int i = 0; i < vacas.size(); i++) {
				vacasOrdenadas[i] = vacas.get(i);
			}
			
			// ordenamos por consumo de espacio
			campusvirtual.Ordenar.quickSortC(
					vacasOrdenadas,
					(Vaca o1, Vaca o2) -> {
						return Integer.compare(o1.getOcupaEspacio(), o2.getOcupaEspacio());
					}
			);
			
			final Registro registro = new Registro();

			run(
					vacasOrdenadas,
					lecturadatos.Usuario.obtenerLecheDeseada(),
					lecturadatos.Constantes.ESPACIO_DISPONIBLE,
					// etapa
					0,
					// solución temporal para ir explorando
					new Solucion(vacas.size()),
					// contenedor de soluciones
					registro
			);
		
			// la primera solución no es 1, 2, 3... porque las habíamos ordenado por consumo de espacio
			System.out.println(registro);

		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero \"" + nombreFicheroDatos + "\".");
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error inesperado. " + e);
			e.printStackTrace();
		}
	}

}
