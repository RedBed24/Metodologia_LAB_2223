package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

/**
 * <p>
 * Clase que compara 2 vacas, da prioridad a aquellas con menor comida y menor espacio.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-04-29
 */
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
