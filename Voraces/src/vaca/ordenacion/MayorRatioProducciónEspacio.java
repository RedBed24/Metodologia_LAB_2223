package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

/**
 * <p>
 * Clase que compara 2 vacas, da prioridad a aquellas con mayor producción y menor espacio.
 * </p>
 * @author Noelia Díaz-Alejo Alejo, Samuel Espejo Gil 
 * @since 2023-04-29
 */
public class MayorRatioProducciónEspacio implements Comparator<Vaca>{

	@Override
	public int compare(final Vaca o1, final Vaca o2) {
		final double ratio1 = o1.getProducciónLeche() / o1.getOcupaEspacio();
		final double ratio2 = o2.getProducciónLeche() / o2.getOcupaEspacio();
		return - Double.compare(ratio1, ratio2);
	}
	
	@Override
	public String toString() {
		return "Mayor ratio: Producción / Espacio";
	}

}
