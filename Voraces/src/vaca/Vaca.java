package vaca;

public class Vaca implements Comparable<Vaca> {
	
	final private int código;
	final private int consumoComida;
	final private double producciónLeche;

	public Vaca(final int código, final int consumoComida, final double producciónLeche) {
		super();
		this.código = código;
		this.consumoComida = consumoComida;
		this.producciónLeche = producciónLeche;
	}

	public int getCódigo() { return código; }

	public int getConsumoComida() { return consumoComida; }

	public double getProducciónLeche() { return producciónLeche; }
	
	public double getBeneficio() { return producciónLeche / consumoComida; };

	@Override
	public int compareTo(Vaca o) {
		/* la vaca que de más beneficio va primero */
		return getBeneficio() == o.getBeneficio() ? 0 : getBeneficio() > o.getBeneficio() ? -1 : 1;
	}
	
	@Override
	public String toString() {
		return String.format("%6d %7d %10.3f %5.3f", código, consumoComida, producciónLeche, getBeneficio());
	}
}
