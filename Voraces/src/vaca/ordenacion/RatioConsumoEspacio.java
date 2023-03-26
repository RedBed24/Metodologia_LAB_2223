package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

public class RatioConsumoEspacio implements Comparator<Vaca> {

	@Override
	public int compare(final Vaca o1, final Vaca o2) {
		final double ratio1 = o1.getOcupaEspacio() / o1.getConsumoComida();
		final double ratio2 = o2.getOcupaEspacio() / o2.getConsumoComida();
		return ratio1 == ratio2 ? 0 :
			ratio1 < ratio2 ? - 1 : 1;
	}

}
