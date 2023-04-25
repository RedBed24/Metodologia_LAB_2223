import java.util.Stack;

import vaca.Vaca;

/**
 * <p>
 * Clase para representar una posible solución al problema.
 * Contiene las vacas de la solución y las estadísticas de Espacio y Leche totales.
 * </p>
 * @author Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
 */
public class Solucion {
	private Stack<Vaca> vacas = new Stack<>();

	private int consumoEspacio = 0;
	private int consumoComida = 0;
	private double producciónLeche = 0;


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
