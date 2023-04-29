package lecturadatos;

/**
 * <p>
 * Clase para interactuar con el usuario y obtener los datos requeridos por el problema.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-04-29
 */
public class Usuario {
	
	/**
	 * <p>
	 * Pide al usuario el espacio disponible de la granja y lo pasa a dm².
	 * </p>
	 * @return Entero positivo que indica el espacio disponible en dm²
	 */
	public static int obtenerEspacioDisponible() {
		int espacioDisponible;
		do {
			espacioDisponible = campusvirtual.leer.entero("¿Cuánto espacio (en metros cuadrados) dispone la granga?");
			if (espacioDisponible <= 0) {
				System.err.println("No se puede tener tan poco espacio.");
			}
		} while (espacioDisponible <= 0);

		// convertimos a dm², que son las unidades en las que trabajan las vacas
		return espacioDisponible * 100;
	}

}
