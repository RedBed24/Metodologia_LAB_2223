
/**
 * <p>
 * Clase para llevar un registro de las soluciones encontradas.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-03-20
 */
public class Registro {
	private Solucion mejor;
	private int cuentaSoluciones = 0;

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
		
		cuentaSoluciones++;
	}
	
	@Override
	public String toString() {
		return String.format("Mejor: %s\nTotal: %d soluciones factibles de %.0f posibilidades totales (%.3f%%).", mejor, cuentaSoluciones, Math.pow(2, mejor.length()), 100 * (cuentaSoluciones / Math.pow(2, mejor.length())));
	}
}
