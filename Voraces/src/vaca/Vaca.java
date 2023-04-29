package vaca;

/**
 * <p>
 * Clase dominio que representa a una Vaca.
 * Contiene los atributos que requiere la práctica.
 * Una vaca es inmutable una vez creada.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-04-29
 */
public class Vaca {
	
	final private int  código;
	final private int  ocupaEspacio;      /* dm² */
	final private int  consumoComida;     /* kg */
	final private double producciónLeche; /* L */

	/**
	 * <p>
	 * Crea una vaca con los parámetros dados
	 * </p>
	 * @param código Identificador de la vaca
	 * @param ocupaEspacio Espacio que ocupa en dm²
	 * @param consumoComida Consumo de comida en kg
	 * @param producciónLeche Leche que produce en L
	 */
	public Vaca(final int código, final int ocupaEspacio, final int consumoComida, final double producciónLeche) {
		super();
		this.código = código;
		this.ocupaEspacio = ocupaEspacio;
		this.consumoComida = consumoComida;
		this.producciónLeche = producciónLeche;
	}

	public int getOcupaEspacio() { return ocupaEspacio; }

	public int getConsumoComida() { return consumoComida; }

	public double getProducciónLeche() { return producciónLeche; }
	
	@Override
	public String toString() {
		return String.format("%d", código);
	}
}
