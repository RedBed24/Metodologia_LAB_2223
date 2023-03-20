package Vaca;

public class Vaca {
	
	final private int código;
	
	final private int espacio;
	final private int consumoComida;
	final private int consumoAgua;
	final private double producciónLeche;

	public Vaca(final int código, final int espacio, final int consumoComida, final int consumoAgua, final double producciónLeche) {
		super();
		this.código = código;
		this.espacio = espacio;
		this.consumoComida = consumoComida;
		this.consumoAgua = consumoAgua;
		this.producciónLeche = producciónLeche;
	}

	public int getCódigo() { return código; }

	public int getEspacio() { return espacio; }

	public int getConsumoComida() { return consumoComida; }

	public int getConsumoAgua() { return consumoAgua; }

	public double getProducciónLeche() { return producciónLeche; }
	
	/* buscar cuánto CO2 producen por kg */
}
