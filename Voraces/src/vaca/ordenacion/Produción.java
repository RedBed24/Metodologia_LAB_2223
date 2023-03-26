package vaca.ordenacion;

import java.util.Comparator;

import vaca.Vaca;

public class Produción implements Comparator<Vaca> {

	@Override
	public int compare(Vaca o1, Vaca o2) {
		final double producción1 = o1.getProducciónLeche();
		final double producción2 = o2.getProducciónLeche();
		return producción1 == producción2 ? 0 :
			producción1 < producción2 ? 1 : - 1;
	}

}
