package vaca;

/**
 * <p>
 * Clase dominio que representa a una Vaca
 * </p>
 * @author Samuel Espejo Gil, Noelia Díaz Alejo Alejo 
 */
public class Vaca {
	
	final private int  código;
	final private int  ocupaEspacio;      /* dm² */
	final private int  consumoComida;     /* kg */
	final private double producciónLeche; /* L */

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
