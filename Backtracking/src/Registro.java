
/**
 * <p>
 * Clase para llevar un registro de las soluciones encontradas.
 * </p>
 * @author Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
 *
 */
public class Registro {
	private Solucion primera;
	private Solucion mejor;
	private int cuentaSoluciones = 0;

	/**
	 * <p>
	 * Guarda primera solución, la mejor y cuenta todas las que le pasemos.
	 * </p>
	 * @param nueva --> Una nueva solución a contemplar
	 */
	public void contemplarSolución(final Solucion nueva) {
		if (primera == null) {
			primera = nueva;
			mejor = nueva;
		}
		if (mejor.compareTo(nueva) < 0) {
			mejor = nueva;
		}
		
		cuentaSoluciones++;
	}
	
	@Override
	public String toString() {
		return String.format("Primera: %s\nMejor: %s\nTotal: %d", primera, mejor, cuentaSoluciones);
	}
}
