import vaca.Vaca;

/**
 * <p>
 * Clase para representar una posible solución al problema.
 * Contiene las vacas de la solución y las estadísticas de Espacio y Leche totales.
 * </p>
 * <p>
 * Usa un array que no se contempla que pueda crecer para guardar las vacas.
 * Se reserva memoria en la creación del objeto.
 * </p>
 * <h3>Implements:</h3>
 * <ul>
 * <li>Comparable&ltSolucion&gt: Permite comparar 2 soluciones para ver cuál es mejor</li>
 * <li>Cloneable: Permite clonar la solución dada</li>
 * </ul>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-03-20
 */
public class Solucion implements Comparable<Solucion>, Cloneable {
	// estructura para guardar las vacas
	private Vaca[] vacas;

	// estadísticas de la solución, se mide en las mismas unidades que las estadísticas de las vacas
	private int consumoEspacio = 0;
	private double producciónLeche = 0;
	

	/**
	 * <p>
	 * Constructor para crear una nueva solución.
	 * </p>
	 * @param size Tamaño del array de vacas.
	 */
	public Solucion(final int size) {
		super();
		this.vacas = new Vaca[size];
	}
	
	/**
	 * <p>
	 * Crea una copia de la solución.
	 * De esta forma, los cambios en el array de una solución y la copia no se ven afectados.
	 * <em>No se hace una copia de las vacas.</em>
	 * </p>
	 */
	@Override
	public Solucion clone() {
		Solucion copia = new Solucion(vacas.length);
		for (int i = 0; i < vacas.length; i++) {
			copia.añadirVaca(vacas[i], i);
		}
		return copia;
	}
	
	/**
	 * <p>
	 * Añade una vaca a la solución con la posición indicada y actualiza las estadísticas de la solución.
	 * </p>
	 * @param nueva Vaca a añadir
	 * @param posición Posición dentro del vector donde debemos colocar a la vaca
	 * @return Vaca que ocupaba la posición antes
	 */
	public Vaca añadirVaca(final Vaca nueva, final int posición) {
		final Vaca anterior = vacas[posición];

		if (anterior != null) {
			consumoEspacio -= anterior.getOcupaEspacio();
			producciónLeche -= anterior.getProducciónLeche();
		}

		vacas[posición] = nueva;

		if (nueva != null) {
			consumoEspacio += nueva.getOcupaEspacio();
			producciónLeche += nueva.getProducciónLeche();
		}

		return anterior;
	}

	/**
	 * <p>
	 * Elimina la Vaca de la posición especificada y actualiza las estadísticas de la solución.
	 * </p>
	 * @param posición Posición que indica qué vaca se quitará de la solución
	 * @return Vaca que ocupaba la posición
	 */
	public Vaca quitarVaca(final int posición) {
		final Vaca anterior = vacas[posición];

		if (anterior != null) {
			consumoEspacio -= anterior.getOcupaEspacio();
			producciónLeche -= anterior.getProducciónLeche();
		}

		vacas[posición] = null;
		return anterior;
	}
	
	@Override
	public int compareTo(final Solucion otra) {
		return Double.compare(producciónLeche, otra.getProducciónLeche());
	}
	
	public int getConsumoEspacio() { return consumoEspacio; }

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
		
		return String.format("%s}, Leche: %.3f, Espacio: %d]", str, producciónLeche, consumoEspacio);
	}

}
