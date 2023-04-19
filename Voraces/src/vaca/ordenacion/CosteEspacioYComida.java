package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

public class CosteEspacioYComida implements Comparator<Vaca> {
	/* la comida cuesta 8.7 veces más que el espacio, esto hace que estén al mismo nivel, que el precio tenga en cuenta por igual uno y otro
	 * Aumentando este hacemos que la comida se valore más que el espacio, es decir, se tenga más en cuenta
	 * rango = [0, infinito)
	 * 0 hace que no se tenga en cuenta la comida
	 * un valor muy alto hace (prácticamente) que no se tenga en cuenta el espacio */
	private double RELACIÓN_COSTE_COMIDA_ESPACIO;
	
	public CosteEspacioYComida(final double RELACIÓN_COSTE_COMIDA_ESPACIO) {
		super();
		this.RELACIÓN_COSTE_COMIDA_ESPACIO = RELACIÓN_COSTE_COMIDA_ESPACIO;
	}

	@Override
	public int compare(final Vaca o1, final Vaca o2) {
		final double precio1 = o1.getOcupaEspacio() + o1.getConsumoComida() * RELACIÓN_COSTE_COMIDA_ESPACIO;
		final double precio2 = o2.getOcupaEspacio() + o2.getConsumoComida() * RELACIÓN_COSTE_COMIDA_ESPACIO;
		return Double.compare(precio1, precio2);
	}
	
	@Override
	public String toString() {
		return String.format("Menor coste en: Espacio + %.3f x Comida", RELACIÓN_COSTE_COMIDA_ESPACIO);
	}

}

