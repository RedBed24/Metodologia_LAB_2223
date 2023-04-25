import java.util.Stack;

import vaca.Vaca;

public class OrientadoSolucion implements Comparable<OrientadoSolucion>, Cloneable {
	private Stack<Vaca> vacas;
	
	
	private int consumoEspacio;
	private double producciónLeche;
	
	public OrientadoSolucion() {
		super();
		vacas = new Stack<Vaca>();
		consumoEspacio = 0;
		producciónLeche = 0;
	}
	
	public OrientadoSolucion(final Stack<Vaca> vacas, final int consumoEspacio, final double producciónLeche) {
		super();
		this.vacas = vacas;
		this.consumoEspacio = consumoEspacio;
		this.producciónLeche = producciónLeche;
	}

	public int getConsumoEspacio() { return consumoEspacio; }

	public double getProducciónLeche() { return producciónLeche; }

	public boolean add(final Vaca nueva) {
		consumoEspacio += nueva.getOcupaEspacio();
		producciónLeche += nueva.getProducciónLeche();

		return vacas.add(nueva);
	}
	
	public Vaca pop() {
		final Vaca quitada = vacas.pop();

		consumoEspacio -= quitada.getOcupaEspacio();
		producciónLeche -= quitada.getProducciónLeche();

		return quitada;
	}

	@Override
	public int compareTo(OrientadoSolucion o) {
		return Double.compare(producciónLeche, o.getProducciónLeche());
	}
	
	@Override
	public OrientadoSolucion clone() {
		final Stack<Vaca> copy = new Stack<Vaca>();
		for (final Vaca vaca : vacas) {
			copy.add(vaca);
		}
		return new OrientadoSolucion(copy, consumoEspacio, producciónLeche);
	}

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
