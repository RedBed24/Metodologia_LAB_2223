import vaca.Vaca;

/**
 * <p>
 * Clase para representar una posible solución al problema.
 * Contiene las vacas de la solución y las estadísticas de Espacio y Leche totales.
 * </p>
 * @author Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
 */
public class Solucion implements Comparable<Solucion>, Cloneable {
	private Vaca[] vacas;

	private int consumoEspacio = 0;
	private double producciónLeche = 0;
	

	public Solucion(final int size) {
		super();
		this.vacas = new Vaca[size];
	}
	
	@Override
	public Solucion clone() {
		Solucion copia = new Solucion(vacas.length);
		for (int i = 0; i < vacas.length; i++) {
			copia.añadirVaca(vacas[i], i);
		}
		return copia;
	}
	
	public Vaca getVaca(final int posición) {
		return vacas[posición];
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
