package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

public class MenorEspacioYComida implements Comparator<Vaca> {

	@Override
	public int compare(Vaca o1, Vaca o2) {
		final double coste1 = o1.getConsumoComida() * o1.getOcupaEspacio();
		final double coste2 = o2.getConsumoComida() * o2.getOcupaEspacio();
		return - Double.compare(coste1, coste2);
	}
	
	@Override
	public String toString() {
		return "Menor Espacio y Comida (área)";
	}

}
