import vaca.Vaca;

/**
 * <p>
 * Clase para representar una posible solución al problema.
 * Contiene las vacas de la solución y las estadísticas de Espacio y Leche totales.
 * </p>
 * @author Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
 */
public class Solucion {
	private Vaca[] vacas;

	private int consumoEspacio = 0;
	private int consumoComida = 0;
	private double producciónLeche = 0;

	public Solucion(final int size) {
		super();
		this.vacas = new Vaca[size];
	}
	
	public Vaca getVaca(final int posición) {
		return vacas[posición];
	}

	/**
	 * <p>
	 * Añade una vaca a la solución con la posición indicada y actualiza las estadísticas de la solución.
	 * </p>
	 * @param nueva --> Vaca a añadir
	 * @param posición --> Posición dentro del vector donde debemos colocar a la vaca
	 */
	public void añadirVaca(final Vaca nueva, final int posición) {
		vacas[posición] = nueva;

		if (nueva != null) {
			consumoEspacio += nueva.getOcupaEspacio();
			consumoComida += nueva.getConsumoComida();
			producciónLeche += nueva.getProducciónLeche();
		}
	}
	
	public int getConsumoEspacio() { return consumoEspacio; }

	public int getConsumoComida() { return consumoComida; }

	public double getProducciónLeche() { return producciónLeche; }
	
	public int length() { return vacas.length; }
	
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
