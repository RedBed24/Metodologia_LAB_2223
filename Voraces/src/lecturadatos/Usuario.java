package lecturadatos;

public class Usuario {
	
	public static int obtenerEspacioDisponible() {
		int espacioDisponible;
		do {
			espacioDisponible = campusvirtual.leer.entero("¿Cuánto espacio (en metros cuadrados) dispone la granga?");
			if (espacioDisponible <= 0) {
				System.err.println("No se puede tener tan poco espacio.");
			}
		} while (espacioDisponible <= 0);

		// convertimos a dm², que son las unidades en las que tarbajan las vacas
		return espacioDisponible * 100;
	}

}
