package vaca;

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

	public int getCódigo() { return código; }

	public int getOcupaEspacio() { return ocupaEspacio; }

	public int getConsumoComida() { return consumoComida; }

	public double getProducciónLeche() { return producciónLeche; }
	
	@Override
	public String toString() {
		return String.format("%6d %7d %7d %10.3f", código, ocupaEspacio, consumoComida, producciónLeche);
	}
}