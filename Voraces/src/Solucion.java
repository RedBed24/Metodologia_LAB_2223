import java.util.Stack;

import vaca.Vaca;

/**
 * <p>
 * Clase para representar una posible solución al problema.
 * Contiene las vacas de la solución y las estadísticas de Espacio, Comida y Leche totales.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-03-20
 */
public class Solucion {
	// estructura para guardar las vacas
	private Stack<Vaca> vacas = new Stack<>();

	// estadísticas de la solución, se mide en las mismas unidades que las estadísticas de las vacas
	private int consumoEspacio = 0;
	private int consumoComida = 0;
	private double producciónLeche = 0;


	/**
	 * <p>
	 * Método para añadir una nueva vaca a la solución.
	 * Actualiza las estadísticas de la solución con las de la vaca nueva
	 * </p>
	 * @param nueva Vaca nueva a añadir a la solución
	 */
	public void añadirVaca(final Vaca nueva) {
		vacas.add(nueva);

		consumoEspacio += nueva.getOcupaEspacio();
		consumoComida += nueva.getConsumoComida();
		producciónLeche += nueva.getProducciónLeche();
	}
	
	public int getConsumoEspacio() { return consumoEspacio; }

	public int getConsumoComida() { return consumoComida; }

	public double getProducciónLeche() { return producciónLeche; }
	
	@Override
	public String toString() {
		String str = "[Solución: Vacas: {";
		boolean primera = true;
		
		for (Vaca vaca : vacas) {
			if (vaca != null) {
				str += (primera ? "" : ", ") + vaca;
				primera = false;
			}
		}
		
		return String.format("%s}, Espacio: %d, Comida: %d, Leche: %.3f]", str, consumoEspacio, consumoComida, producciónLeche);
	}

}
