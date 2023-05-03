
/**
 * <p>
 * Clase para llevar un registro de las soluciones encontradas.
 * También se queda con aquella solución que sea mejor.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-03-20
 */
public class Registro {
	// representa el limitante del mínimo de leche que requerimos
	private final double lecheDeseada;
	private Solucion mejor;
	private int cuentaSoluciones = 0;
	
	/**
	 * <p>
	 * Crea un nuevo Registro guardando el mínimo de leche que se requiere para que se consideren válidas las soluciones.
	 * </p>
	 * @param lecheDeseada Mínimo de leche, en las mismas unidades que las vacas.
	 */
	public Registro(final double lecheDeseada) {
		super();
		this.lecheDeseada = lecheDeseada;
	}

	/**
	 * <p>
	 * Guarda <emp>una copia</em> de la mejor solución y cuenta todas las que le pasemos.
	 * </p>
	 * @param nueva Una nueva solución a contemplar
	 */
	public void contemplarSolución(final Solucion nueva) {
		if (mejor == null || mejor.compareTo(nueva) < 0) {
			mejor = nueva.clone();
		}
		
		// si es una posible solución
		if (nueva.getProducciónLeche() >= lecheDeseada) {
			cuentaSoluciones++;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Mejor: %s\nTotal: %d soluciones factibles de %.0f posibilidades totales (%.3f%%).", mejor, cuentaSoluciones, Math.pow(2, mejor.length()), 100 * (cuentaSoluciones / Math.pow(2, mejor.length())));
	}
}
