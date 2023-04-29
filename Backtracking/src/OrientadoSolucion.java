import java.util.Stack;

import vaca.Vaca;

/**
 * <p>
 * Clase para representar una posible solución al problema.
 * Contiene las vacas de la solución y las estadísticas de Espacio y Leche totales.
 * </p>
 * <p>
 * Usa un Stack para guardar las vacas.
 * </p>
 * <h3>Implements:</h3>
 * <ul>
 * <li>Cloneable: Permite clonar la solución dada</li>
 * </ul>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-03-20
 */
public class OrientadoSolucion implements Cloneable {
	// estructura para guardar las vacas
	private Stack<Vaca> vacas;
	
	// estadísticas de la solución, se mide en las mismas unidades que las estadísticas de las vacas
	private int consumoEspacio;
	private double producciónLeche;
	
	
	/**
	 * <p>
	 * Constructor para crear una nueva solución vacía.
	 * </p>
	 */
	public OrientadoSolucion() {
		super();
		vacas = new Stack<Vaca>();
		consumoEspacio = 0;
		producciónLeche = 0;
	}
	
	/**
	 * <p>
	 * Constructor para crear una nueva con los parámetros dados.
	 * </p>
	 * @param vacas Pila que puede ya contener vacas
	 * @param consumoEspacio Consumo de espacio de las vacas contenidas en la pila dada
	 * @param producciónLeche Producción de leche de las vacas contienidas en la pila dada
	 */
	public OrientadoSolucion(final Stack<Vaca> vacas, final int consumoEspacio, final double producciónLeche) {
		super();
		this.vacas = vacas;
		this.consumoEspacio = consumoEspacio;
		this.producciónLeche = producciónLeche;
	}

	public int getConsumoEspacio() { return consumoEspacio; }

	public double getProducciónLeche() { return producciónLeche; }

	/**
	 * <p>
	 * Añade una vaca a la solución y actualiza las estadísticas de la solución.
	 * </p>
	 * @param nueva Vaca a añadir
	 * @return Valor especificado en add(...) in List
	 */
	public boolean add(final Vaca nueva) {
		consumoEspacio += nueva.getOcupaEspacio();
		producciónLeche += nueva.getProducciónLeche();

		return vacas.add(nueva);
	}
	
	/**
	 * <p>
	 * Elimina la última Vaca añadida a la pila.
	 * </p>
	 * @return Vaca eliminada
	 */
	public Vaca pop() {
		final Vaca quitada = vacas.pop();

		consumoEspacio -= quitada.getOcupaEspacio();
		producciónLeche -= quitada.getProducciónLeche();

		return quitada;
	}

	/**
	 * <p>
	 * Crea una copia de la solución.
	 * De esta forma, los cambios en el Stack de una solución y la copia no se ven afectados.
	 * <em>No se hace una copia de las vacas.</em>
	 * </p>
	 */
	@Override
	public OrientadoSolucion clone() {
		final Stack<Vaca> copy = new Stack<Vaca>();
		for (final Vaca vaca : vacas) {
			copy.add(vaca);
		}
		return new OrientadoSolucion(copy, consumoEspacio, producciónLeche);
	}

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
		
		return String.format("%s}, Leche: %.3f, Espacio: %d]", str, producciónLeche, consumoEspacio);
	}
}
