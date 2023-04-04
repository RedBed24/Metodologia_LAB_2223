package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

public class CosteEspacioYComida implements Comparator<Vaca> {
	/* la comida cuesta 10 veces más que el espacio, esto hace que estén al mismo nivel
	 * Aumentando este hacemos que la comida se valore más que el espacio, es decir, se tenga más en cuenta
	 * rango = [0, infinito)
	 * 0 hace que no se tenga en cuenta la comida
	 * un valor muy alto hace (prácticamente) que no se tenga en cuenta el espacio */
	final static double RELACIÓN_COSTE_COMIDA_ESPACIO = 10;

	@Override
	public int compare(final Vaca o1, final Vaca o2) {
		final double precio1 = o1.getOcupaEspacio() + o1.getConsumoComida() * RELACIÓN_COSTE_COMIDA_ESPACIO;
		final double precio2 = o2.getOcupaEspacio() + o2.getConsumoComida() * RELACIÓN_COSTE_COMIDA_ESPACIO;
		return Double.compare(precio1, precio2);
	}

}
