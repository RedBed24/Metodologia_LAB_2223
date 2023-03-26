package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

public class RatioProducciónEspacio implements Comparator<Vaca>{

	@Override
	public int compare(final Vaca o1, final Vaca o2) {
		final double ratio1 = o1.getProducciónLeche() / o1.getOcupaEspacio();
		final double ratio2 = o2.getProducciónLeche() / o2.getOcupaEspacio();
		return ratio1 == ratio2 ? 0 :
			ratio1 < ratio2 ? 1 : - 1;
	}

}
